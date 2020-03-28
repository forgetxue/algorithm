package com.snow.sparaArray;

/**
 * @Description:
 * @author: snow
 * @Date: 2020-03-10
 **/
public class SparaseArray {


    public static void main(String[] args) {
        //1.创建原二维数组
        int[][] rawArray = new int[10][10];
        rawArray[0][1] = 1;
        rawArray[3][3] = 4;
        rawArray[1][2] = 2;
        rawArray[2][4] = 3;
        rawArray[2][9] = 9;
        rawArray[6][7] = 1;
        rawArray[9][8] = 6;
        //2.打印原二维数组
        System.out.println("原二维数组为~~");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.printf("%d" + "\t", rawArray[i][j]);
            }
            System.out.println();
        }

        //2.创建稀疏数组
        /*
         * 1.首先的到数组的行数，初始化稀疏数组
         * 2.得到原二维数组中的有效值个数
         * 3.遍历原二维数组给稀疏数组赋值
         */

        //①
        int row = rawArray.length;
        int col = rawArray[0].length;
        int num = 0;
        System.out.println("得到行数为：" + row);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rawArray[i][j] != 0) num++;
            }
        }

        //②
        int[][] sparseArray = new int[num + 1][3];
        sparseArray[0][0] = row;
        sparseArray[0][1] = col;
        sparseArray[0][2] = num;

        //③
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rawArray[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = rawArray[i][j];
                }
            }
        }

        //打印所得到的稀疏数组
        System.out.println("得到的稀疏数组为：~~");
        System.out.printf("%-4s%-4s%-4s", "row", "col", "val");
        System.out.println();
        for (int i = 0; i < sparseArray.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf("%-4d", sparseArray[i][j]);
            }
            System.out.println();
        }

        //3.还原稀疏数组
        /*
         * 1.读取行数和列数
         * 2.初始化还原数组
         * 3.给还原数组赋值
         */

        int[][] newArray = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            for (int j = 0; j < 3; j++) {
                newArray[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
            }
        }

        System.out.println("得到的还原的数组为：~~");
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[0].length; j++) {
                System.out.printf("%d" + "\t", newArray[i][j]);
            }
            System.out.println();
        }

    }

}

