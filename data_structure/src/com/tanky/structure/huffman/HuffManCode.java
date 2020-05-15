package com.tanky.structure.huffman;


import java.io.*;
import java.util.*;

/**
 * @Description: 写了huffman树的构建的过程以及文件和字符串的编解码
 * @Author: Tanky
 * @CreateDate: 2020/5/13$ 17:27$
 */
public class HuffManCode {


    static Map<Byte, String> hufamanCodeMap = new HashMap<>(32);

    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) {

        //需要压缩的字符串
        String content = "i like like java java do you like java!!!";

        //获取其byte数组
        byte[] bytes = content.getBytes();

        List<TreeNode> treeNodes = buildHuffmanTree(bytes);

        getCodes(treeNodes.get(0));

        System.out.println(hufamanCodeMap);

        byte[] bytes1 = zipCode(bytes);

        System.out.println(Arrays.toString(bytes1));

        String s = unzipCode(bytes1);

        byte[] bytes2 = decodeZip(s);

        String stringBuilder = new String(bytes2);

        System.out.println(stringBuilder);

        System.out.println(content);


        //我们来尝试一下利用哈夫曼编码来压缩一个文件把
//        String srcFile = "E://inspur//1.txt";
//        String dstFile = "E://inspur//1.zip";
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件ok");

        //解压
//        String srcFile = "E://inspur//1.zip";
//        String dstFile = "E://inspur//1.txt";
//        unZipFile(srcFile,dstFile);


    }


    /**
     * 解压文件
     *
     * @param srcFile
     * @param dstFile
     */
    public static void unZipFile(String srcFile, String dstFile) {
        //定义文件输入流
        InputStream is = null;
        //定义一个对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(srcFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);
            //读取byte数组  huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            //读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes1 = decodeFile(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes1);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {

            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                // TODO: handle exception
                System.out.println(e2.getMessage());
            }

        }
    }

    /**
     * 解压文件的封装
     *
     * @param hufamaCodeMap
     * @param bytes
     * @return
     */
    public static byte[] decodeFile(Map hufamaCodeMap, byte[] bytes) {

        hufamanCodeMap = hufamaCodeMap;

        String s = unzipCode(bytes);

        byte[] bytes1 = decodeZip(s);

        return bytes1;

    }

    /**
     * 压缩文件的方法实现-----------
     *
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        //创建文件的输入流
        FileInputStream is = null;
        try {
            //创建文件的输入流
            is = new FileInputStream(srcFile);
            //创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];

            //读取文件
            is.read(b);
            //直接对源文件压缩
            //byte[] huffmanBytes = huffmanZip(b);
            List<TreeNode> treeNodes = buildHuffmanTree(b);
            getCodes(treeNodes.get(0));
            byte[] bytes1 = zipCode(b);
            //创建文件的输出流, 存放压缩文件
            os = new FileOutputStream(dstFile);
            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把 赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(bytes1);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(hufamanCodeMap);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 构建哈夫曼树
     *
     * @param bytes
     * @return
     */
    public static List<TreeNode> buildHuffmanTree(byte[] bytes) {


        Map<Byte, Integer> byteIntegerMap = new HashMap<>(32);

        //统计每个字符出现的次数即权值
        for (byte b : bytes) {
            if (byteIntegerMap.get(b) != null) {
                byteIntegerMap.put(b, byteIntegerMap.get(b) + 1);
            } else {
                byteIntegerMap.put(b, 1);
            }
        }

        System.out.println(byteIntegerMap);

        //构建哈夫曼树
        List<TreeNode> treeNodes = new ArrayList<>();

        for (Map.Entry entry : byteIntegerMap.entrySet()) {
            treeNodes.add(new TreeNode((byte) entry.getKey(), (int) entry.getValue()));
        }

        Collections.sort(treeNodes);

        while (treeNodes.size() > 1) {
            TreeNode treeNode = new TreeNode(treeNodes.get(0).value + treeNodes.get(1).value);
            treeNode.setLeft(treeNodes.get(0));
            treeNode.setRight(treeNodes.get(1));
            treeNode.setData(null);
            treeNodes = treeNodes.subList(2, treeNodes.size());
            treeNodes.add(treeNode);
            Collections.sort(treeNodes);
        }
        return treeNodes;
    }

    //***************************************************************解压有关方法******************************************************

    /**
     * 解压成二进制的串
     *
     * @param bytes
     */
    public static String unzipCode(byte[] bytes) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {

            int temp = bytes[i];
            //如果是正数需要补齐高位 方法是与256进行与运算
            if (i != bytes.length - 1) {
                //不是最后一位
                temp = (temp | 256);
            }

            String substring;
            if (i != bytes.length - 1) {

                String string = Integer.toBinaryString(temp);
                //只截取后八位即可
                substring = string.substring(string.length() - 8);

            } else {
                substring = Integer.toBinaryString(temp);
            }

            stringBuilder.append(substring);
        }

        return stringBuilder.toString();

    }

    /**
     * 通过获取的二进制字符串以及编码表进行解码
     *
     * @param str
     */
    public static byte[] decodeZip(String str) {

        StringBuilder stringBuilder = new StringBuilder();

        List<Byte> list = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {

            stringBuilder.append(str.charAt(i));

            if (hufamanCodeMap.containsValue(stringBuilder.toString())) {

                for (Byte getKey : hufamanCodeMap.keySet()) {

                    if (hufamanCodeMap.get(getKey).equals(stringBuilder.toString())) {
                        list.add(getKey);
                        stringBuilder = new StringBuilder();
                        break;
                    }

                }

            }

        }

        Object[] objects = list.toArray();

        byte[] bytes = new byte[objects.length];

        for (int j = 0; j < objects.length; j++) {

            bytes[j] = (byte) objects[j];

        }

        return bytes;

        //String string = new java.lang.String(bytes);

        //System.out.println(string);


    }


    //*************************************************************压缩有关方法**************************************************

    /**
     * 开始分割 字符串进行真正的压缩，这里是比较麻烦且重要的！！！！！！！！
     *
     * @param bytes
     */
    public static byte[] zipCode(byte[] bytes) {

        //首先拼接起哈夫曼代码，即将content的内容转为0，1代码
        StringBuilder stringBuilder = new StringBuilder();
        for (Byte b : bytes) {
            stringBuilder.append(hufamanCodeMap.get(b));
        }

        System.out.println(stringBuilder.toString());

        //然后八位为一组去分割字符串
        //定义一个length来存放数组的长度，通过将字符串的长度和8进行除法计算出长度
        int length = (stringBuilder.length() + 7) / 8;

        //定义一个length长度的数组用来存放 编码以后的字节数组
        byte[] huffmanByte = new byte[length];
        //用来存储放置的下标
        int index = 0;
        //8个字节为一组所以步长为8
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String string;
            //也就意味着最后一组不足八位
            if (i + 8 >= stringBuilder.length()) {
                string = stringBuilder.substring(i);
            } else {
                string = stringBuilder.substring(i, i + 8);
            }

            //将二进制转为字节
            byte by = (byte) Integer.parseInt(string, 2);

            huffmanByte[index] = by;

            index++;

        }

        return huffmanByte;

    }


    /**
     * 封装了获取编码的方法
     *
     * @param treeNode
     */
    public static void getCodes(TreeNode treeNode) {

        if (treeNode == null) {
            return;
        }

        transCode(treeNode.left, "0", stringBuilder);

        transCode(treeNode.right, "1", stringBuilder);

    }

    /**
     * 转换编码左 0 右 1
     *
     * @param treeNode
     * @param str
     */
    public static void transCode(TreeNode treeNode, String code, StringBuilder str) {

        StringBuilder newStringBuilder = new StringBuilder(str);

        newStringBuilder.append(code);

        if (treeNode != null) {
            //意味者到达了叶子节点
            if (treeNode.data == null) {

                transCode(treeNode.left, "0", newStringBuilder);

                transCode(treeNode.right, "1", newStringBuilder);

            } else {

                hufamanCodeMap.put(treeNode.data, newStringBuilder.toString());

            }
        }

    }


    /**
     * 写一个前序遍历的方法来验证数据
     *
     * @param treeNode
     */
    public static void frontPrint(TreeNode treeNode) {

        System.out.println(treeNode.value + "--------------" + treeNode.data);

        if (treeNode.left != null) {
            frontPrint(treeNode.left);
        }

        if (treeNode.right != null) {
            frontPrint(treeNode.right);
        }

    }


}
