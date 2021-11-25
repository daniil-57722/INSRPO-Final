package ParallelDownloading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Class //ИНСРПО своя аннотация
public class launch {
    private static Logger log;  //Логгирование

    @Main//своя аннотация
    public static void main(String[] args) {

        //все для логгирования
        log = Logger.getLogger(launch.class.getName());
        log.setUseParentHandlers(false);
        //логирование для ИНСРПО
        try {
            //привязка файла для ИНСРПО
            FileHandler fh = new FileHandler("D:\\ParallelDownload\\logging.txt");
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            log.addHandler(fh);
            log.info("Программа запущена");//запись в лог
        } catch (IOException e) {
            e.printStackTrace();
        }

    //продолжение программы
        String[] mas = new String[2];
        String extension = "/image.jpg";
        DownloadThread downloadThread = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("files.txt"));
            log.info("Считывание ссылок");//запись в лог
            for (int i = 0; i < 2; i++) {
                mas = bufferedReader.readLine().split(" ");
                downloadThread = new DownloadThread(mas[0], mas[1], extension, log);
                downloadThread.thread.start();
                extension = "/music.mp3";
            }
        } catch (IOException e) {
            log.info("Ошибка считывания ссылок");//запись в лог
        }


        //ожидание завершения потоков. wait() как то странно ждет

        while (downloadThread.thread.isAlive()) {
        }

        //запуск музыки
        System.out.println("хочешь послушать музыку Д/Н >>");
        Scanner in = new Scanner(System.in);
        String play = in.nextLine();

        if (play.equals("Д") || play.equals("д")) {
            System.out.println("^o^ а теперь музыкааааа ^o^");
            log.info("Сигнал на запуск музыки");//запись в лог
            //тут сам запуск музыки. в этот метод улетает путь для музыки
            playmusic p = new playmusic();

            p.play(mas[1], log);
        }
    }
}


