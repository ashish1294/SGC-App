package com.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
public class Fileutils {
 
    public static void close(InputStream stream) {
        if(stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
    public static void close(OutputStream stream) {
        if(stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}