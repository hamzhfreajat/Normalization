package com.progressoft.tools;

import java.nio.file.Path;
import java.nio.file.Paths;

public class app {

    public static void main(String[] args) {
//        [SOURCE_PATH] [DEST_PATH] [COLUMN_TO_NORMALIZE] [NORMALIZATION_METHOD]

        if (args.length > 0){
            if (args.length >= 4 ){
                NormalizerImpl normalizer = new NormalizerImpl() ;

                switch (args[3]){
                    case "z_score" :
                    {
                        Path SOURCE_PATH = Paths.get(args[0]);
                        Path DEST_PATH = Paths.get(args[1]);
                        normalizer.zscore(SOURCE_PATH , DEST_PATH , args[4]);
                        break;
                    }
                    case "minMaxScaling" :
                    {
                        Path SOURCE_PATH = Paths.get(args[0]);
                        Path DEST_PATH = Paths.get(args[1]);
                        normalizer.minMaxScaling(SOURCE_PATH , DEST_PATH , args[4]);
                        break;
                    }

                    default:
                        System.out.println("Choose z_score or minMaxScaling");
                }
            }else {
                System.out.println("you should provide [SOURCE_PATH] [DEST_PATH] [COLUMN_TO_NORMALIZE] [NORMALIZATION_METHOD]");
            }

        }

    }

}
