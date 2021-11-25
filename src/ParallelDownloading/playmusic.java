package ParallelDownloading;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
@Class//ИНСРПО
public class playmusic {
@Magic//ИНСРПО
    void play(String path , Logger log){


    try (FileInputStream inputStream = new FileInputStream(path+"/music.mp3")) {
        try {
            Player player = new Player(inputStream);
            player.play();
            log.info("Музыка воспроизводится");//запись в лог
        } catch (JavaLayerException e) {
            log.log(Level.WARNING, "Проблемы с воспроизведением файла", e);//запись в лог
            e.printStackTrace();
        }
    } catch (IOException e) {
        log.log(Level.WARNING, "Файл музыки не найден", e);//запись в лог
        e.printStackTrace();
        }
    }
}
