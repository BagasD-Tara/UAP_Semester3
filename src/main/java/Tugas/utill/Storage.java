// java
package Tugas.utill;

import Tugas.Model.Film;
import Tugas.Model.Penonton;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String FILM_FILE = System.getProperty("user.home") + File.separator + ".bioskop_films.dat";
    private static final String PENONTON_FILE = System.getProperty("user.home") + File.separator + ".bioskop_penonton.dat";

    public static void saveFilms(List<Film> films) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILM_FILE))) {
            oos.writeObject(new ArrayList<>(films));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Film> loadFilms() {
        File f = new File(FILM_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Film>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void savePenonton(List<Penonton> penonton) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PENONTON_FILE))) {
            oos.writeObject(new ArrayList<>(penonton));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Penonton> loadPenonton() {
        File f = new File(PENONTON_FILE);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Penonton>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}