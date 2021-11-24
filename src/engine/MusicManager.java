package engine;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;


/** 
 * 
 * The method that plays music in general.
 * 
 */
public class MusicManager {

    private final static File main_bgm = new File("res\\Main BGM.wav"); // runtime 265000
    private final static File shoot = new File("res\\Shoot.wav"); // runtime 1000
    private final static File come_boss = new File("res\\Begin boss.wav"); // runtime 32000
    private final static File game_bgm = new File("res\\Game BGM.wav"); // runtime 168000
    private final static File ship_exp = new File("res\\Ship Explosion.wav");
    private final static File enemy_exp = new File("res\\Enemy Ship Explosion.wav");
    private final static File get_item = new File("res\\Get Item.wav");

    private static final int MAIN_BGM_LENGTH = 264000;
    private static final int GAME_BGM_LENGTH = 167000;

    public static Cooldown mainCooldown = Core.getCooldown(MAIN_BGM_LENGTH);
    public static Cooldown gameCooldown = Core.getCooldown(GAME_BGM_LENGTH);

    private static Clip main_clip = make_main_Clip();
    private static Clip game_clip = make_game_Clip();

    /** 
     * Method for running shooting sound.
     * 
     */
    public static void run_shoot() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(shoot);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch(Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void run_item() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(get_item);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch(Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void run_exp() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(enemy_exp);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch(Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void run_enemy_exp() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(ship_exp);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch(Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }

    /** 
     * Method for running BGM for main lobby.
     * 
     */
    public static void run_main() {
        if(main_clip.isRunning()) {
            if(mainCooldown.checkFinished()) {
                main_clip.loop(-1);
                main_clip.start();
                mainCooldown.reset();
            }
        }
        else {
            if(game_clip.isRunning()) {
                stop_game();
            }
            main_clip.loop(-1);
            main_clip.start();
            mainCooldown.reset();
        }
    }

    /** 
     * Method for running BGM for game.
     * 
     */
    public static void run_game() {
        if(game_clip.isRunning()) {
            if(gameCooldown.checkFinished()) {
                game_clip.loop(-1);
                game_clip.start();
                gameCooldown.reset();
            }
        }
        else {
            if(main_clip.isRunning()) {
                stop_main();
            }
            game_clip.loop(-1);
            game_clip.start();
            gameCooldown.reset();
        }
    }

    /** 
     * Method for stoping BGM for main lobby.
     * 
     */
    public static void stop_main() {
        if(main_clip.isRunning()) {
            main_clip.stop();
            main_clip.setFramePosition(0);
        }
    }

    /** 
     * Method for stoping BGM for boss appearance.
     * 
     */
    public static void stop_game() {
        if(game_clip.isRunning()) {
            game_clip.stop();
            game_clip.setFramePosition(0);
        }
    }

    /** 
     * Method for running BGM for boss appearance.
     * 
     */
    public static void run_begin_boss() {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(come_boss);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch(Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }


    /**
     * Make Main lobby BGM Clip.
     * 
     * @return Lobby BGM Clip
     */
    private static Clip make_main_Clip() {
        Clip clip;
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(main_bgm);
            clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        } catch(Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Make In-Game BGM Clip.
     * 
     * @return In-Game BGM Clip
     */
    private static Clip make_game_Clip() {
        Clip clip;
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(game_bgm);
            clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        } catch(Exception e) {
            //TODO: handle exception
            e.printStackTrace();
            return null;
        }
    }
}
