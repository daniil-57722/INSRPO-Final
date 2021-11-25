package ParallelDownloading;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;
@Class//ИНСРПО
public class DownloadThread implements Runnable {

    String link, path, extension;
    Thread thread;
    Logger log;

    public DownloadThread(String link, String path, String extension, Logger log) {
        this.link = link;
        this.path = path;
        this.extension = extension;
        this.log = log;
        thread = new Thread (this);
    }

    @Override
    public void run() {

        System.out.println("процесс скачивания "+extension.substring(1)+" файла начался");
        try {
            URL url = new URL(link);
            ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
            FileOutputStream stream = new FileOutputStream(path+extension);
            stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
            stream.close();
            byteChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("процесс скачивания "+extension.substring(1) +" файла завершился");//запись в лог
        log.info("файл находится по пути "+path);

    }
}
