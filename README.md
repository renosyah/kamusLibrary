# Kamus Library for defa skripsi

## Library kosak kata

menyediakan fungsi untuk menyari kata ada artinya dalam bahasa inggris & indonesia
dari data kosak kata di local storage di perangkat android, berikut adalah gambaran
contoh tampilan penggunaanya : 

![GitHub Logo](/image/kosakKata_1.jpg)






## Library Text scanner

menyediakan activity yg menggunakan fungsi camera untuk mencari kata-kata dalam
gambar dan memberikan hasil yg berupa list string, berikut adalah gambaran
contoh tampilan penggunaanya : 


![GitHub Logo](/image/textScanner.jpg)





cara pakai : 

* instalaltion : 

tambahkan ke build.gradle :
```

	allprojects {
    		repositories {
        		maven { url "https://dl.bintray.com/renosyah/maven" }
        		...
       			...
    		}
	}

```


tambahkan ke app.gradle :
```

	dependencies {
    		...	
    		implementation 'com.github.renosyah:LibraryKosakKataHandle:1.0.0'
		implementation 'com.github.renosyah:ScanKataHandle:1.0.0'
	}

```


* untuk query kata : 

```


	QueryKata.newInstance()
                .setContext(this)
                .setFindBy(QueryKata.Indonesia)
                .setKosakKataDicari(new KosakKataModel("","saya"))
                .setOnQueryKataListener(new QueryKata.OnQueryKataListener() {
                    @Override
                    public void onListKataFound(@NonNull ArrayList<KosakKataModel> kosakKataModels) {
                     
                        // kata2 yg didapat
                        
                    }

                    @Override
                    public void onErrorLogs(@NonNull ArrayList<String> errors) {
                        
                        // error logs
                        
                    }
                    
                }).cari();


```

* untuk tambah kosak kata : 

```

	ListKosakKataModel listKosakKataModel = new ListKosakKataModel();
        listKosakKataModel.kosakKataModels.add(new KosakKataModel("Buy","Beli"));
        listKosakKataModel.kosakKataModels.add(new KosakKataModel("sport","olahraga"));
        
        TambahKata.newInstance()
                .setContext(this)
                .setListKosakKataModel(listKosakKataModel)
                .setOnTambahKataListener(new TambahKata.OnTambahKataListener() {
                    @Override
                    public void onBerhasilTambahKata(@NonNull Boolean success) {
                        
                        // pada saat berhasil nambah
                        
                    }

                    @Override
                    public void onErrorLogs(@NonNull ArrayList<String> errors) {

                        // error logs
                        
                    }
                }).tambah();

```


* untuk scanning kata-kata : 

```

	TextScannerInit.newInstance()
                .setContext(this)
                .setOnTextScannerInitListener(new OnTextScannerInitListener() {
                    @Override
                    public void onGetListString(@NonNull ArrayList<String> texts) {
                        
                        // result list string from scanner
                        
                    }

		    @Override
		    public void onScannerFinish() {
			
			// scanner is finish

		    }

                }).scanText();

```




