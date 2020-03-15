package com.selfdev.sstable;

import java.io.*;

/**
 * Created by vovkv on 8/9/2019.
 */
public class SSTable {

    // LRU cache - ?
    public void readFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(""));
            // how to read from the middle of the file?
            // cache?
            // put in cache -> lock cache ->  flush in file
            // get from cache -> get from file
            // merge files
            reader.lines();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

    }
}
