package com.haofan.algorithm.leetcodecn.other;

public class Compress {

    public static int encode(char[] characters) {
        int writeIDX = 0;
        int cLength = 0;

        for(int i = 0; i < characters.length; i++) {
            int count = 1;

            while (i + 1 < characters.length && characters[i] == characters[i+1]) {
                count++;
                i++;
            }

            if (count > 1) {
                String str = String.valueOf(count);

                for(int j = 0; j < str.length(); j++) {
                    characters[writeIDX] = str.charAt(j);
                    writeIDX++;
                }
            }

            characters[writeIDX] = characters[i];
            writeIDX++;
        }

        // "Reset" the rest of the array since we can't shrink it.
        for (int i = writeIDX; i < characters.length; i++) {
            characters[i] = '\u0000';
        }


        return writeIDX;
    }

    public static void main(String args[]) {
        char[] input = {'a', 'a', 'a', 'b'};
        encode(input);
        System.out.println(input);
    }
}
