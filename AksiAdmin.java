package UAP;

import java.util.*;

public class AksiAdmin extends Aksi {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void tampilanAksi() {
        System.out.println("Aksi Admin:");
        System.out.println("1. Tambah Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Logout");
        System.out.println("4. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        Map<String, Film> films = Film.getFilms();
        for (Film film : films.values()) {
            System.out.println(film.getName() + " - " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
    }

    public void tambahFilm() {
        System.out.print("Nama Film: ");
        String name = scanner.nextLine();
        System.out.print("Deskripsi Film: ");
        String description = scanner.nextLine();
        System.out.print("Harga Tiket: ");
        double price = scanner.nextDouble();
        System.out.print("Stok Tiket: ");
        int stock = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (Film.getFilms().containsKey(name)) {
            System.out.println("Film " + name + " sudah ada.");
        } else {
            Film.addFilm(name, description, price, stock);
            System.out.println("Film " + name + " berhasil ditambahkan.");
        }
    }
}
