# Mini Project Practice: Console Based ATM

Harap ikuti instruksi berikut **sebelum** Anda mulai mengerjakan proyek ini:

1. Clone project ini.
2. Buat branch baru dengan menggunakan nama Anda sebagai nama branch.
3. Edit formulir di bawah ini sesuai dengan identitas Anda, lakukan commit dengan pesan "init."
4. Push perubahan ke repositori yang sama.
5. Baca dengan seksama setiap aspek dan detail project.
6. Sekarang Anda dapat melanjutkan pengerjaan project, **Semoga Beruntung** dalam prosesnya!

---

```plaintext
Nama Lengkap:         { Yana Nugraha }
Asal Kampus/Sekolah:  { Universitas Pasundan }
```

---

## Heads Up

> The keywords "MUST", "MUST NOT", "REQUIRED", "SHALL", "SHALL
> NOT", "SHOULD", "SHOULD NOT", "RECOMMENDED",  "MAY", and
> "OPTIONAL" in this document are to be interpreted as described in
> [RFC 2119](https://datatracker.ietf.org/doc/html/rfc2119).

## Development Requirements

- JDK 11 _(Using OpenJDK or Zulu is RECOMMENDED)_
- Maven 3.8+

## Instruksi Pelaksanaan

- Anda **HARUS** memastikan bahwa Anda telah memenuhi persyaratan sebelum melanjutkan
- Anda **DISARANKAN** untuk membaca seluruh source code untuk lebih memahami struktur dan alur dari template ini
- Anda **WAJIB** mengimplementasikan fitur-fitur yang tercantum dalam Feature Checklists dan **HARUS** mengikuti aturan yang ditentukan di bagian "Batasan", dalam jangka waktu 4 jam
- Semua spesifikasi project ini **WAJIB** diimplementasikan
- Anda **BOLEH** mulai mengembangkan aplikasi pada file `com.tujuhsembilan.App`, di fungsi `start`
- Anda **BOLEH** mengubah semua file sumber dalam template ini, tanpa pengecualian, sesuai dengan kebutuhan Anda; misalnya: untuk memperbaiki bug yang muncul, sesuai dengan gaya pengkodean Anda, untuk menyederhanakan program, dll.
- Dalam hal kesulitan, Anda **BOLEH** bertanya kepada orang yang bertanggung jawab dalam memantau tes ini
- Anda juga **BOLEH** mencari dukungan teknis di internet (browsing)
- Anda **TIDAK DIPERBOLEHKAN** untuk melakukan diskusi dalam hal membahas project ini kepada rekan kerja Anda
- **DISARANKAN** bagi Anda untuk mengcommit progress Anda dalam skala kecil; misalnya: Anda mengcommit setiap kali menyelesaikan satu fitur
- Melakukan push pekerjaan ke repositori setelah setiap commit adalah **OPSIONAL**
- Fungsi dari masing-masing fitur yang dibuat **WAJIB** terintegrasi dengan program utama agar program bisa dijalankan dengan baik
- **Setelah Anda menyelesaikan project ini**, Anda **BOLEH** mengirimkan pendapat Anda tentang project ini, atau bahkan pengalaman pribadi Anda, dalam formulir ulasan di bagian bawah; berilah sentuhan kreatif! dan kemudian Anda dapat melakukan commit dan push sekali lagi :)

## Feature Checklists

- [x] Terdapat 4 jenis ATM dari bank yang berbeda:

  - [x] BRI
  - [x] BNI
  - [x] Mandiri
  - [x] BCA

  masing-masing memiliki akun pelanggan dan stok uang sendiri.

- [x] Setiap kali Nasabah mengakses ATM, mereka harus memasukkan nomor rekening dan pin mereka, sebelum dapat menggunakan fitur-fiturnya.

- [x] Semua Nasabah dapat menggunakan ATM mana pun yang mereka inginkan. Namun, akan dikenakan biaya tambahan untuk penggunaan ATM dari bank berbeda.

- [x] Setiap ATM memiliki nominal mata uang yang sama dalam Rupiah, dan fitur yang sama:

  - [x] Informasi Saldo

    Fitur ini menunjukkan saldo rekening

  - [x] Tarik Uang

    Fitur ini memungkinkan Nasabah untuk menarik uang dari rekening yang terdaftar di Bank tertentu, dengan jumlah penarikan yang dapat disesuaikan.

    Fitur ini akan memberitahu Nasabah untuk mengambil sejumlah nominal mata uang tertentu dengan prioritas nilai dari yang terbesar ke yang terkecil, sesuai dengan jumlah yang akan ditarik, misalnya: 1x dari Nominal C, 3x dari Nominal B, 1x dari Nominal A

    Fitur ini pada akhirnya akan menampilkan sisa saldo rekening

  - [x] Isi Ulang Pulsa Telepon

    Fitur ini memungkinkan Nasabah untuk melakukan isi ulang pulsa untuk nomor telepon yang diinputkan dengan nominal tertentu:

    - [x] Rp10.000,00
    - [x] Rp20.000,00
    - [x] Rp50.000,00
    - [x] Rp100.000,00

    Fitur ini pada akhirnya akan menampilkan nomor telepon yang dituju, jumlah pulsa, dan sisa saldo rekening Nasabah.

  - [x] Token Tagihan Listrik

    Fitur ini memungkinkan Nasabah untuk membeli token tagihan listrik sesuai dengan nomor tagihan yang telah diinputkan, dengan nominal tertentu:

    - [x] Rp50.000,00
    - [x] Rp100.000,00
    - [x] Rp200.000,00
    - [x] Rp500.000,00

    Fitur ini kemudian akan memberitahukan kepada Nasabah mengenai token, nomor target tagihan, dan sisa saldo rekening.

  - [x] Mutasi Rekening (Transfer Dana)

    Fitur ini memungkinkan Nasabah untuk melakukan transfer dana ke nomor rekening tertentu pada Bank yang dipilih

    Fitur ini pada akhirnya akan menampilkan Bank dan rekening tujuan, jumlah yang ditransfer, dan sisa saldo rekening

  Selain itu, untuk BNI dan Mandiri, keduanya akan memiliki fitur ini:

  - [x] Deposit Uang

    Fitur ini memungkinkan Nasabah untuk menyetor uang ke akun rekening mereka

    Fitur ini pada akhirnya akan menampilkan saldo rekening Nasabah

## Constraints

- [x] ATM diasumsikan selalu aktif, dan **TIDAK BOLEH** dimatikan
- [x] ATM **WAJIB** hanya menerima nomor sebagai input
- [x] Jika terjadi kegagalan login, Nasabah **WAJIB** memiliki maksimal 3 kali percobaan ulang, dan upaya login selanjutnya **HARUS** diblokir
- [x] Stok uang di ATM **HARUS** terbatas

  Jumlah standar uang yang disimpan **HARUS** Rp25.000.000,00
- [x] Jumlah maksimum per transaksi **HARUS** Rp2.500.000,00
- [ ] Maksimum transaksi harian per rekening **HARUS** Rp5.000.000,00
- [x] Batasan pengeluaran maksimum hanya berlaku untuk transaksi fisik, yang berarti transaksi virtual seperti isi ulang dan transfer **TIDAK TERPENGARUH**
- [x] Setiap rekening **HARUS** memiliki saldo residu minimal Rp10.000,00
- [x] ATM **HARUS** memiliki nominal mata uang berikut:

  - [x] Rp10.000,00
  - [x] Rp20.000,00
  - [x] Rp50.000,00
  - [x] Rp100.000,00

  Tidak ada jumlah terbatas untuk nominal ini, dan semua nominal diasumsikan selalu tersedia di setiap ATM
- [x] Fitur Penarikan Uang dan Deposit Uang **HANYA** dapat menerima input untuk nilai kelipatan 10.000
- [x] Nomor telepon **HARUS** antara 3 dan 15 digit
- [ ] Biaya transaksi antar bank **HARUS** Rp2.500
- [x] Setiap kesalahan dan/atau input yang tidak valid **HARUS** menampilkan pesan yang benar
- [x] Setiap angka mata uang yang ditampilkan **HARUS** diformat ke string standar Rupiah, dan **HARUS** dieja dalam Bahasa Indonesia; sebagai contoh:

  `Rp10.000,00`\
  `Sepuluh Ribu Rupiah`
- [ ] **(OPSIONAL)** Token tagihan listrik dibuat secara acak atau prosedural, dengan `{RANDOM_UUID}_{First 8 Digit from SHA_256 of (EPOCH_MILLIS)}_{NOMINAL_PEMBELIAN}`; sebagai contoh:

  `aaaa-bb-cc-ddd_2afcb452_50000`\
  jika Anda membeli token seharga Rp50.000,00

## Informasi Tambahan

- Beberapa bagian dari kode-kode ini sengaja dibuat salah, dan mengandung bug; Anda **DIHARUSKAN** untuk memperbaikinya
- **DIREKOMENDASIKAN** untuk menggunakan fitur-fitur dan metode pengkodean Java tingkat lanjut, untuk mendapatkan nilai yang lebih baik; misalnya: OOP, collection stream, tipe data yang tepat, SOLID, modularisme, dll.
- Mempercantik tampilan hasil **TIDAK** mendapatkan poin bonus, jadi Anda **HARUS** fokus pada penerapan fitur sebagai gantinya
- Proyek ini **TIDAK BOLEH** terhubung ke basis data pada saat ini, jadi Anda **HARUS** mengimplementasikan penyimpanan data pada paket `data.repository` yang mendasarinya

---

## Formulir Review

_Silakan masukkan ulasan Anda di sini, hanya setelah Anda menyelesaikan project ini, baik setelah selesai atau karena batas waktu!_

> ```{ Project ini sangat menantang bagi saya, saya senang bisa ikut mengerjakan project ini. Ini sangat keren }```
