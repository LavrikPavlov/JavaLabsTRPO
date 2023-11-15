package ru.labs.MyType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArbitraryInteger implements CustomArbitraryInteger {

    private List<Byte> bytes;
    private boolean isNegative;

    public ArbitraryInteger() {
        this.bytes = new ArrayList<>();
        this.isNegative = false;
    }

    public ArbitraryInteger(List<Byte> bytes, boolean isNegative) {
        this.bytes = bytes;
        this.isNegative = isNegative;
    }

    @Override
    public void add(ArbitraryInteger other) {
        List<Byte> result = new ArrayList<>();
        int maxSize = Math.max(size(), other.size());
        byte carry = 0;

        for (int i = 0; i < maxSize || carry != 0; i++) {
            int byteFirst = i < size() ? bytes.get(i) & 0xFF : 0;
            int byteSecond = i < other.size() ? other.getBytes().get(i) & 0xFF : 0;

            int sum = byteFirst + byteSecond + carry;
            carry = (byte) ((byteFirst & 0xFF) + (byteSecond & 0xFF) + carry >>> 8);

            result.add((byte) (sum & 0xFF));
        }

        bytes = result;
        removeLeadingZeros();
    }

    @Override
    public void subtract(ArbitraryInteger other) {
        if (other.isNegative()) {
            other.setNegative(false);
            add(other);
            return;
        }

        if (isNegative()) {
            isNegative = true;
            add(other);
            return;
        }

        List<Byte> result = new ArrayList<>();
        int maxSize = Math.max(size(), other.size());
        int borrow = 0;

        for (int i = 0; i < maxSize; i++) {
            int byteFirst = i < size() ? bytes.get(i) & 0xFF : 0;
            int byteSecond = i < other.size() ? other.getBytes().get(i) & 0xFF : 0;
            int difference = byteFirst - byteSecond - borrow;

            if (difference < 0) {
                difference += 256;  // 2^8
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.add((byte) difference);
        }

        bytes = result;
        removeLeadingZeros();
    }

    public void multiply(ArbitraryInteger other) {
        int size1 = size();
        int size2 = other.size();

        byte[] result = new byte[size1 + size2];

        for (int i = 0; i < size1; i++) {
            byte byte1 = bytes.get(i);
            int carry = 0;

            for (int j = 0; j < size2 || carry != 0; j++) {
                int product = result[i + j] + (byte1 & 0xFF) * (j < size2 ? other.getBytes().get(j) & 0xFF : 0) + carry;
                result[i + j] = (byte) (product & 0xFF);
                carry = product >>> 8;
            }
        }

        bytes = new ArrayList<>();
        for (byte b : result) {
            bytes.add(b);
        }

        removeLeadingZeros();
    }

    @Override
    public void saveToBinaryStream(OutputStream out) {
        try (DataOutputStream dataOut = new DataOutputStream(out)) {
            dataOut.writeBoolean(isNegative);
            dataOut.writeInt(bytes.size());

            for (byte value : bytes) {
                dataOut.writeByte(value);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в работе со значениями \n" + e);
        }
    }

    @Override
    public void loadFromBinaryStream(InputStream in) {
        try (DataInputStream dataIn = new DataInputStream(in)) {
            isNegative = dataIn.readBoolean();
            int size = dataIn.readInt();

            bytes.clear();

            for (int i = 0; i < size; i++) {
                bytes.add(dataIn.readByte());
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в работе со значениями \n" + e);
        }
    }

    @Override
    public void saveToTextFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(String.valueOf(isNegative));
            writer.newLine();

            writer.write(String.valueOf(bytes.size()));
            writer.newLine();

            for (byte value : bytes) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в текстовый файл: " + e.getMessage());
        }
    }

    @Override
    public void loadFromTextFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            isNegative = Boolean.parseBoolean(reader.readLine());
            int size = Integer.parseInt(reader.readLine());

            bytes.clear();
            for (int i = 0; i < size; i++) {
                bytes.add(Byte.parseByte(reader.readLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении из текстового файла: " + e.getMessage());
        }
    }



    @Override
    public void setElement(int index, byte value) {
        indexOutOfArray(index);
        bytes.set(index, value);
    }

    @Override
    public int size() {
        return bytes.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%8s", Integer.toBinaryString(b & 0xFF))
                    .replace(' ', '0')).append(" ");
        }
        return result.toString();
    }

    private boolean indexOutOfArray(int index) {
        if (index >= 0 && index < bytes.size()) {
            return true;
        } else {
            throw new IllegalArgumentException("Выход за пределы массива");
        }
    }

    private void removeLeadingZeros() {
        int i = size() - 1;
        while (i > 0 && bytes.get(i) == 0) {
            bytes.remove(i);
            i--;
        }
    }

    public List<Byte> getBytes() {
        return bytes;
    }

    public boolean isNegative() {
        return isNegative;
    }

    public void setNegative(boolean negative) {
        isNegative = negative;
    }
}