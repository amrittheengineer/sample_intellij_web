package com.zoho.task.filesearchengine.demo;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SearchFile {
    public static final String BASE_DIR = "D:/";
    public static final class Types {
        public static final int FILE = 0, DIRECTORY = 1;
    }
    String name, path;
    int type;


    SearchFile(String name, String path){
        this.name = name;
        this.path = path;
    }

    public static void listFilesUsingDirectoryStream(String directory, String query, int depth, int MAX_DEPTH, boolean includeFolders, ServletOutputStream out) throws IOException {

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directory))) {
            for (Path path : stream) {
                SearchFile file = new SearchFile(path.getFileName().toString(), path.toString());
                file.type = Files.isDirectory(path) ? Types.DIRECTORY : Types.FILE;

//                If the name is same
                if (file.compareFileName(query) && (file.type == Types.FILE || includeFolders)){
                    out.print("<li>" + file.name + " <b>~</b><i>" + directory + "</i></li>");
                    out.flush();
                }

                if (file.type == Types.DIRECTORY && depth < MAX_DEPTH) {
                    listFilesUsingDirectoryStream(file.path, query, depth + 1, MAX_DEPTH, includeFolders, out);
                }
            }
        }
    }

    public static void listFilesUsingDirectoryStream(String query, ServletOutputStream out, int MAX_DEPTH, boolean includeFolders) throws IOException {
        listFilesUsingDirectoryStream(BASE_DIR, query, 1, MAX_DEPTH, includeFolders, out);
    }

    public boolean compareFileName(String query){
        return name.contains(query.toLowerCase());
    }
}
