package com.github.lightyear;

import java.io.*;

//реализует логику обработки запросов пользователя
//производит вычисления
public class Model {
    View view = new View();
    String result = null;
    boolean isActionCorrect = false;

    //выполняет обработку запроса из консоли
    String parseConsolRequest(String request) {
        String argLine = request.toLowerCase();//защита от ввода смешанным регистром
        String[] args = argLine.split(" ");//разделяем строку на массив аргументов
        String action = args[0];//хранит тип арифметического действия(add, mul)

        //определяет какое арифметическое действие требуется
        //и вычисляет результат
        switch (action) {
            case ("add"):
                isActionCorrect = true;
                int resAdd = 0;
                for (int i = 1; i < args.length; i++) {
                    if(isNumeric(args[i]))
                        resAdd += Integer.parseInt(args[i]);
                    else
                        view.showEnterCorrectArguments();
                }
                result = String.valueOf(resAdd);
                break;

            case ("mul"):
                isActionCorrect = true;
                int resMul = 1;
                for (int i = 1; i < args.length; i++) {
                    if(isNumeric(args[i]))
                        resMul = Integer.parseInt(args[i])*resMul;
                    else
                        view.showEnterCorrectArguments();
                }
                result = String.valueOf(resMul);
                break;

            default:
                //в случае ввода некорректных аргументов выводит сообщение
                isActionCorrect = false;
                break;
        }

        return result;
    }

    //выполняет обработку запроса из файла
    String parseFileRequest(String[] args) {

        boolean inputFileNameMatch = false;
        boolean outputFileNameMatch = false;
        String inputFileName = null;
        String outputFileName = null;

        if (args.length > 1) {
            inputFileNameMatch = args[0].matches("(\\w*)\\.(txt)");
            outputFileNameMatch = args[1].matches("(\\w*)\\.(txt)");
            inputFileName = args[0];//хранит имя входящего файла
            outputFileName = args[1];//хранит имя исходящего файла
        }

        if(inputFileNameMatch && outputFileNameMatch) {
            try {
                File inputFile = new File(inputFileName);//подключаем входящий фаил
                FileReader fileReader = new FileReader(inputFile);//создаем FileReader для чтения
                BufferedReader bReader = new BufferedReader(fileReader);
                String inputLine = bReader.readLine();//получаем строку из файла

                File outputFile = new File(outputFileName);//подключаем и создаем исходящий файл
                FileWriter fileWriter = new FileWriter(outputFile);//создаем FileWriter для записи
                BufferedWriter bWriter = new BufferedWriter(fileWriter);
                String result = null;

                inputLine = inputLine.toLowerCase();//защита от ввода смешанным регистром
                String[] parts = inputLine.split(" ");//получаем массив аргументов

                switch (parts[0]) {
                    case ("add"):
                        int resAdd = 0;
                        for (int i = 1; i < parts.length; i++) {
                            resAdd += Integer.parseInt(parts[i]);
                        }
                        result = String.valueOf(resAdd);
                        break;

                    case ("mul"):
                        int resMul = 1;
                        for (int i = 1; i < parts.length; i++) {
                            resMul = Integer.parseInt(parts[i]) * resMul;
                        }
                        result = String.valueOf(resMul);
                        break;

                    default:
                        //в случае ввода некорректных аргументов выводим сообщение
                        view.showInputFileHasIncorrectInstructions();
                        break;
                }
                bWriter.write(result + "\n");
                bWriter.flush();//выгружаем в поток
                bWriter.close();//закрываем исходящий поток
                bReader.close();//закрываем входящий поток

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else
            view.showEnterCorrectArguments();
        return result;
    }

    //проверяет является ли аргумент числом
    public static boolean isNumeric(String str) {
        return str.matches("[0-9]");
    }

    boolean getIsActionCorrect(){
        return isActionCorrect;
    }
}