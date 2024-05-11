package com.telyu.nourimate.data.local

import com.telyu.nourimate.utils.Converters
import java.util.Date

class FakeFoodData {
    data class Recipe(
        val recipeId: Int,
        val name: String,
        val calories: Double,
        val carbs: Double,
        val fat: Double,
        val protein: Double,
        val ingredients: String,
        val cookingSteps: String,
        val recipePictures: String,
        val mealType: Int,
    )

    data class Recommendation(
        val recommendationId: Int,
        val date: Date,
        val isSelected: Int,
        val recipeId: Int,
    )

    val recipes = listOf(
        Recipe(
            recipeId = 1,
            name = "Roti Pisang Oat",
            calories = 193.0,
            carbs = 29.8,
            fat = 6.9,
            protein = 4.3,
            ingredients = "2 besar telur\n" +
                    "2 besar pisang\n" +
                    "1 tsp baking powder (sodium aluminum sulfate\n" +
                    "double acting)\n" +
                    "6 sdm honey\n" +
                    "170 g quick cooking oatmeal\n" +
                    "54 g roasted almond\n" +
                    "100 g choco chips",
            cookingSteps = "Panaskan oven terlebih dahulu pada suhu 170°C.\n" +
                    "Hancurkan pisang\n" +
                    "campur dengan telur dan madu.\n" +
                    "Giling oatmeal dan campur dengan baking powder.\n" +
                    "Aduk bahan basah dan kering hingga rata\n" +
                    "tambahkan coklat keping tapi sisakan sedikit untuk topping.\n" +
                    "Tuang ke dalam loyang dan taburi dengan sisa choco chips dan almond. Panggang selama 40 menit atau sampai matang.",
            recipePictures = "roti_pisang_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 2,
            name = "Donat",
            calories = 106.0,
            carbs = 19.3,
            fat = 1.7,
            protein = 3.7,
            ingredients = "3 gram ragi\n" +
                    "40 gram kuning telur\n" +
                    "30 gram gula pasir\n" +
                    "130 ml susu uht full cream\n" +
                    "15 g susu\n" +
                    "250 g tepung terigu cakra kembar",
            cookingSteps = "Campur semua bahan kering.\n" +
                    "Tambahkan semua bahan basah dan uleni hingga halus.\n" +
                    "Diamkan hingga ukurannya dua kali lipat.\n" +
                    "Bentuk adonan dan goreng hingga berwarna cokelat keemasan.",
            recipePictures = "donat",
            mealType = 1
        ),
        Recipe(
            recipeId = 3,
            name = "Muffin Oat Pisang",
            calories = 136.0,
            carbs = 23.6,
            fat = 3.0,
            protein = 4.0,
            ingredients = "1 sedang telur\n" +
                    "1 sdt ekstrak vanila\n" +
                    "154 gram pisang\n" +
                    "5 gram baking soda\n" +
                    "6 gram madu\n" +
                    "100 g quick cooking oatmeal\n" +
                    "50 g squeeze yoghurt",
            cookingSteps = "Masukan semua bahan pada blender.\n" +
                    "Setelah tercampur\n" +
                    "bagi adonan ke dalam cetakan.\n" +
                    "Panggang dengan suhu 175°C selama 25 menit.",
            recipePictures = "muffin_oat_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 4,
            name = "Banana Bread Oats",
            calories = 247.0,
            carbs = 33.0,
            fat = 8.5,
            protein = 8.8,
            ingredients = "2 besar telur\n" +
                    "1 sdt kayu manis\n" +
                    "115 g banana\n" +
                    "120 g instant oatmeal grain\n" +
                    "120 ml almond breeze vanilla\n" +
                    "10 g baking powder\n" +
                    "13 g 1/3 pb original\n" +
                    "20 g fineza chocolate button",
            cookingSteps = "Panaskan oven/airfryer dengan suhu 160°C selama 10 menit.\n" +
                    "Sementara itu\n" +
                    "campurkan semua bahan dalam blender\n" +
                    "kecuali coklat kancing.\n" +
                    "Blender adonan hingga halus. Tuang ke dalam cangkir aluminium dan hiasi tombol cokelat di atasnya.\n" +
                    "Panggang adonan dengan suhu 160°C selama 15 menit.",
            recipePictures = "banana_bread_oats",
            mealType = 1
        ),
        Recipe(
            recipeId = 5,
            name = "Bilah Energi Oatmeal Cokelat",
            calories = 92.0,
            carbs = 13.3,
            fat = 3.6,
            protein = 2.1,
            ingredients = "25 gram biji bunga matahari (yang) dikuliti sangrai kering\n" +
                    "60 gram kurma\n" +
                    "40 gram kismis (tanpa biji)\n" +
                    "20 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "20 g choco chips\n" +
                    "60 g selai kacang\n" +
                    "150 g oatmeal\n" +
                    "100 g madu tj murni",
            cookingSteps = "Sangrai oatmeal sampai kecoklatan. Dinginkan sampai suhu ruang.\n" +
                    "Cincang kurma dengan halus.\n" +
                    "Campur semua bahan dalam satu wadah menggunakan spatula.\n" +
                    "Pindahkan pada wadah\n" +
                    "padatkan\n" +
                    "tutup rapat. Simpan pada frezer minimal 30 menit.\n" +
                    "Potong menjadi batangan.",
            recipePictures = "bilah_energi_oatmeal_cokelat",
            mealType = 1
        ),
        Recipe(
            recipeId = 8,
            name = "Kue Gandum Pisang",
            calories = 103.0,
            carbs = 19.3,
            fat = 1.9,
            protein = 3.2,
            ingredients = "30 gram susu\n" +
                    "1 ekstra besar telur\n" +
                    "175 gram pisang\n" +
                    "1 sdm madu\n" +
                    "50 gram tepung terigu putih (semua keperluan)\n" +
                    "10 g bubuk pengembang\n" +
                    "80 g gulungan gandum",
            cookingSteps = "Kupas dan haluskan pisang. Campur dengan telur\n" +
                    "susu\n" +
                    "dan madu.\n" +
                    "Tambahkan bahan kering dan aduk rata.\n" +
                    "Pindahkan ke loyang dan masak dalam oven dengan suhu 180°C selama 30 menit.\n" +
                    "Biarkan dingin\n" +
                    "buka cetakannya\n" +
                    "dan potong-potong.",
            recipePictures = "kue_gandum_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 9,
            name = "Gandum dengan Buah",
            calories = 251.0,
            carbs = 50.2,
            fat = 3.8,
            protein = 4.6,
            ingredients = "1 sdm madu\n" +
                    "1 gelas yogurt stroberi\n" +
                    "3 sdm gandum\n" +
                    "100 g buah naga\n" +
                    "100 g apel",
            cookingSteps = "Masak gandum dengan air sesuai kemasan.\n" +
                    "Pindahkan ke mangkuk lalu tambahkan yogurt.\n" +
                    "Iris buah dan tambahkan ke mangkuk. Gerimis dengan madu.",
            recipePictures = "gandum_dengan_buah",
            mealType = 1
        ),
        Recipe(
            recipeId = 10,
            name = "Sandwich Telur Dadar",
            calories = 306.0,
            carbs = 36.5,
            fat = 11.6,
            protein = 13.6,
            ingredients = "1 daun sedang selada\n" +
                    "1 besar telur dadar\n" +
                    "1 utuh sedang tomat merah\n" +
                    "1 lembar roti tawar gandum",
            cookingSteps = "Panggang roti dalam wajan antilengket selama satu menit di setiap sisi.\n" +
                    "Atur telur dadar dan sayuran di dalam roti.\n" +
                    "Iris dan sajikan.",
            recipePictures = "sandwich_telur_dadar",
            mealType = 1
        ),
        Recipe(
            recipeId = 11,
            name = "Banana Oats Coklat",
            calories = 314.0,
            carbs = 45.5,
            fat = 10.7,
            protein = 12.3,
            ingredients = "61 gram telur\n" +
                    "1 sdt ekstrak vanila\n" +
                    "157 gram pisang\n" +
                    "1 sdt baking soda\n" +
                    "11 gram kakao bubuk (tanpa gula)\n" +
                    "6 g unsalted butter\n" +
                    "49 g oat\n" +
                    "200 ml susu uht low fat\n" +
                    "6 g choco chips",
            cookingSteps = "Haluskan pisang.\n" +
                    "Tambahkan oat\n" +
                    "coklat bubuk\n" +
                    "vanilla\n" +
                    "dan baking soda\n" +
                    "lalu aduk.\n" +
                    "Setelah adonan tercampur rata\n" +
                    "masukkan susu\n" +
                    "telur dan mentega cair.\n" +
                    "Tuang ke dalam cetakan yang dialasi kertas roti dan taburi choco chips.\n" +
                    "Panggang dengan suhu 180°C selama 20 menit.",
            recipePictures = "banana_oats_coklat",
            mealType = 1
        ),
        Recipe(
            recipeId = 12,
            name = "Wafel",
            calories = 231.0,
            carbs = 26.4,
            fat = 11.3,
            protein = 4.8,
            ingredients = "240 ml susu\n" +
                    "1/2 besar telur\n" +
                    "120 gram tepung putih\n" +
                    "1/2 sdt garam\n" +
                    "1/2 sdt ekstrak vanila\n" +
                    "10 gram gula pasir\n" +
                    "60 g mentega tawar\n" +
                    "20 g tepung pati jagung\n" +
                    "2 g bubuk pengembang",
            cookingSteps = "Hangatkan susu tapi tidak mendidih\n" +
                    "tambahkan butter\n" +
                    "aduk hingga butter meleleh\n" +
                    "sisihkan.\n" +
                    "Campur bahan kering jadi satu.\n" +
                    "Tambahkan telur ke dalam susu lalu masukkan ekstrak vanili.\n" +
                    "Campur semuanya sampai halus. Diamkan selama 15 menit.\n" +
                    "Masak dalam waffle iron hingga berwarna keemasan.\n" +
                    "Sajikan dengan buah atau topping yang Anda inginkan.",
            recipePictures = "wafel",
            mealType = 1
        ),
        Recipe(
            recipeId = 15,
            name = "Kue Oat Pisang",
            calories = 153.0,
            carbs = 23.2,
            fat = 5.0,
            protein = 4.0,
            ingredients = "100 g telur\n" +
                    "1 porsi minyak nabati\n" +
                    "250 g oat\n" +
                    "45 ml low fat milk\n" +
                    "30 g choco chips\n" +
                    "200 gram pisang raja",
            cookingSteps = "Haluskan pisang raja.\n" +
                    "Tambahkan telur\n" +
                    "minyak\n" +
                    "dan susu. Campur dengan baik.\n" +
                    "Tambahkan oat giling dan 15g keping cokelat\n" +
                    "lalu aduk rata.\n" +
                    "Tuang adunan ke dalam acuan dan hiaskan baki choco chips. Bakar selama kira-kira 30 minit pada suhu 150°C.\n" +
                    "Biarkan dingin lalu iris.",
            recipePictures = "kue_oat_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 16,
            name = "Kue Pisang Kukus",
            calories = 181.0,
            carbs = 21.9,
            fat = 9.4,
            protein = 4.3,
            ingredients = "170 gram putih telur\n" +
                    "270 gram pisang\n" +
                    "10 gram chia seed\n" +
                    "5 gram kacang almond\n" +
                    "1 sdt\n" +
                    "bongkahan gula merah\n" +
                    "75 g salted butter\n" +
                    "120 g oatmeal rolled oats\n" +
                    "75 g palm sugar\n" +
                    "60 g dark chocolate chips\n" +
                    "27 g susu",
            cookingSteps = "Kocok putih telur dengan 2 gula.\n" +
                    "Masukkan pisang lecek\n" +
                    "oat\n" +
                    "dan biji chia.\n" +
                    "Tambahkan mentega cair dan susu. Campur dengan baik.\n" +
                    "Tempatkan ke dalam cetakan dan hiasi dengan keping cokelat dan almond cincang. Kukus selama 25 menit.",
            recipePictures = "kue_pisang_kukus",
            mealType = 1
        ),
        Recipe(
            recipeId = 19,
            name = "Tahu Dihancurkan dengan Telur Orak Arik",
            calories = 156.0,
            carbs = 6.4,
            fat = 11.3,
            protein = 8.9,
            ingredients = "110 gram telur\n" +
                    "16 gram cabai merah atau rawit\n" +
                    "10 gram bawang putih\n" +
                    "30 gram daun bawang\n" +
                    "2 sdm minyak goreng\n" +
                    "232 gram tahu\n" +
                    "35 gram bawang bombay",
            cookingSteps = "Tuangkan minyak\n" +
                    "kemudian tumis bawang putih\n" +
                    "bawang bombay\n" +
                    "dan rawit.\n" +
                    "Setelah berwarna sedikit kecoklatan\n" +
                    "masukkan telur dan diorak arik.\n" +
                    "Tambahkan tahu dan hancurkan. Tuangkan air secukupnya.\n" +
                    "Bumbui sesuai selera.\n" +
                    "Masukkan bawang daun dan sajikan.",
            recipePictures = "tahu_dihancurkan_dengan_orak_arik",
            mealType = 1
        ),
        Recipe(
            recipeId = 22,
            name = "Nasi Goreng Sayuran",
            calories = 416.0,
            carbs = 70.2,
            fat = 8.9,
            protein = 15.6,
            ingredients = "1 besar telur\n" +
                    "1 mangkok\n" +
                    "masak nasi putih\n" +
                    "100 gram wortel\n" +
                    "100 gram selada kol\n" +
                    "1 sdm sambal goreng\n" +
                    "100 gram sawi hijau",
            cookingSteps = "Dalam wajan antilengket\n" +
                    "kocok telur.\n" +
                    "Tambahkan nasi dan saus sambal. Mengaduk.\n" +
                    "Masukkan sayuran dan aduk rata.\n" +
                    "Bumbui sesuai selera.",
            recipePictures = "nasi_goreng_sayuran",
            mealType = 1
        ),
        Recipe(
            recipeId = 24,
            name = "Roti Pisang Havermut",
            calories = 232.0,
            carbs = 39.6,
            fat = 5.9,
            protein = 7.3,
            ingredients = "50 g telur\n" +
                    "1 sejumput garam\n" +
                    "1/2 sdt ekstrak vanila\n" +
                    "120 gram pisang\n" +
                    "1/2 sdt baking soda\n" +
                    "1 sdm madu\n" +
                    "30 g havermut instan\n" +
                    "20 g cokelat hitam & granola almond",
            cookingSteps = "Hancurkan pisang dan campur dengan telur\n" +
                    "vanila\n" +
                    "dan madu.\n" +
                    "Tambahkan oatmeal\n" +
                    "garam\n" +
                    "dan soda kue.\n" +
                    "Tuang ke dalam cetakan roti dan taburi dengan granola.\n" +
                    "Panggang dalam oven dengan suhu 180°C selama 20 menit atau hingga adonan tidak lengket lagi.\n" +
                    "Biarkan dingin sebelum membuka cetakan dan mengiris.",
            recipePictures = "roti_pisang_havermut",
            mealType = 1
        ),
        Recipe(
            recipeId = 25,
            name = "Goreng Labu dengan Jagung",
            calories = 121.0,
            carbs = 19.1,
            fat = 3.9,
            protein = 3.8,
            ingredients = "1 besar telur\n" +
                    "1 gelas jagung\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "1 sdt\n" +
                    "tumbuk oregano\n" +
                    "1 sejumput lada hitam\n" +
                    "100 gram labu\n" +
                    "1 sdm minyak zaitun extra virgin\n" +
                    "100 g tepung terigu",
            cookingSteps = "Potong dadu labu\n" +
                    "rebus dengan air hingga empuk\n" +
                    "tiriskan.\n" +
                    "Campur dalam blender dengan bahan lain\n" +
                    "kecuali tepung dan minyak.\n" +
                    "Masukkan tepung dan aduk hingga rata.\n" +
                    "Masak dalam wajan dengan minyak sampai berwarna keemasan dan kencang.",
            recipePictures = "goreng_labu_dengan_jagung",
            mealType = 1
        ),
        Recipe(
            recipeId = 26,
            name = "Roti Pisang dan Keju",
            calories = 280.0,
            carbs = 41.5,
            fat = 8.8,
            protein = 0.0,
            ingredients = "100 g pisang\n" +
                    "30 g keju quick leleh\n" +
                    "1 lembar roti tawar gandum",
            cookingSteps = "Iris pisang dan parut keju.\n" +
                    "Susun di atas roti.\n" +
                    "Masak dalam penggorengan udara pada suhu 190°C selama 5 menit.",
            recipePictures = "roti_pisang_dan_keju",
            mealType = 1
        ),
        Recipe(
            recipeId = 27,
            name = "Pancake Protein Oat",
            calories = 86.0,
            carbs = 8.3,
            fat = 1.6,
            protein = 8.6,
            ingredients = "1 besar telur\n" +
                    "1/2 gelas air\n" +
                    "35 g oat gulung biasa\n" +
                    "2 ml pemanis\n" +
                    "1 sendok air dadih coklat\n" +
                    "3 g bubuk pengembang",
            cookingSteps = "Giling oat hingga menjadi bubuk lalu campurkan dengan bahan kering lainnya.\n" +
                    "Campur semuanya sampai halus.\n" +
                    "Masak dalam wajan antilengket sampai keras di kedua sisi.",
            recipePictures = "pancake_protein_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 28,
            name = "Brownies Oatmeal",
            calories = 165.0,
            carbs = 26.6,
            fat = 5.4,
            protein = 4.4,
            ingredients = "1 sedang telur\n" +
                    "2 kecil pisang\n" +
                    "1/2 sdt baking soda\n" +
                    "4 sdt gula pasir\n" +
                    "1 sdm minyak goreng\n" +
                    "44 g instant oatmeal\n" +
                    "9 g cocoa powder\n" +
                    "58 ml susu uht kids cokelat\n" +
                    "4 g baking powder",
            cookingSteps = "Giling oatmeal dalam blender. Menyisihkan.\n" +
                    "Tumbuk pisang.\n" +
                    "Campurkan semua bahan dengan baik.\n" +
                    "Masukkan adunan ke dalam acuan dan kukus selama 15 minit.\n" +
                    "Biarkan ia sejuk\n" +
                    "buang acuan dan potong.",
            recipePictures = "brownies_oatmeal",
            mealType = 1
        ),
        Recipe(
            recipeId = 30,
            name = "Salad Sayur dengan Ayam, Tahu, dan Tempe",
            calories = 195.0,
            carbs = 9.6,
            fat = 9.9,
            protein = 18.8,
            ingredients = "70 gram daging ayam (panggang\n" +
                    "bakar\n" +
                    "dimasak)\n" +
                    "100 gram kubis\n" +
                    "100 gram selada daun hijau\n" +
                    "50 gram tempe\n" +
                    "100 gram tahu\n" +
                    "20 g saus salad pulau seribu",
            cookingSteps = "Potong-potong ayam\n" +
                    "tahu\n" +
                    "dan tempe. Masak dalam wajan antilengket sampai empuk.\n" +
                    "Masukkan semua bahan ke dalam mangkuk.\n" +
                    "Sajikan sampai segar.",
            recipePictures = "salad_sayur_dengan_ayam,_tahu,_dan_tempe",
            mealType = 1
        ),
        Recipe(
            recipeId = 31,
            name = "Bihun dengan Sayuran",
            calories = 242.0,
            carbs = 47.8,
            fat = 3.9,
            protein = 4.3,
            ingredients = "60 gram carrots\n" +
                    "60 gram broccoli (with salt\n" +
                    "drained\n" +
                    "cooked\n" +
                    "boiled)\n" +
                    "55 g bihun rasa ayam bawang",
            cookingSteps = "Masak mie sesuai petunjuk kemasan.\n" +
                    "Rebus sayuran selama 5 menit.\n" +
                    "Campur mie dan sayuran\n" +
                    "bumbui sesuai keinginan\n" +
                    "dan sajikan.",
            recipePictures = "bihun_dengan_sayuran",
            mealType = 1
        ),
        Recipe(
            recipeId = 35,
            name = "Smoothie Buah Naga dan Pisang",
            calories = 280.0,
            carbs = 60.4,
            fat = 3.5,
            protein = 5.0,
            ingredients = "2 sedang pisang\n" +
                    "130 ml yakult\n" +
                    "1 mangkok buah naga\n" +
                    "20 g cinnamon & raisin\n" +
                    "70 g squeeze yogurt peach",
            cookingSteps = "Potong pisang dan buah naga.\n" +
                    "Masukkan kesemuanya ke dalam pengisar dan kisar sehingga sebati.\n" +
                    "Tempatkan dalam mangkuk dan hiasi dengan granola dan buah sesuai keinginan. Nikmati sarapan Anda.",
            recipePictures = "smoothie_buah_naga_dan_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 36,
            name = "Bolu Oatmeal Pisang Cokelat",
            calories = 106.0,
            carbs = 18.7,
            fat = 2.8,
            protein = 3.0,
            ingredients = "50 gram egg\n" +
                    "375 gram banana\n" +
                    "10 g cocoa powder\n" +
                    "150 g whole rolled oats\n" +
                    "5 g coconut sugar\n" +
                    "50 g choco chips\n" +
                    "2.5 g baking powder",
            cookingSteps = "Haluskan pisang.\n" +
                    "Campur semua bahan.\n" +
                    "Panggang selama 30 menit pada suhu 170°C.\n" +
                    "Hasilnya jadi 2 loyang bisa dibagi 12 potong.",
            recipePictures = "bolu_oatmeal_pisang_cokelat",
            mealType = 1
        ),
        Recipe(
            recipeId = 39,
            name = "Oat Panekuk",
            calories = 54.0,
            carbs = 7.1,
            fat = 1.9,
            protein = 2.4,
            ingredients = "1 besar telur\n" +
                    "1/4 cangkir instant oatmeal\n" +
                    "80 ml chocolate drink",
            cookingSteps = "Haluskan oatmeal hinggah tekstur menjadi tepung.\n" +
                    "Tambahkan sebutir telur dan aduk rata.\n" +
                    "Tambahkan chocolatos dan aduk.\n" +
                    "Masak di atas pan anti lengket hingga matang kedua sisinya.",
            recipePictures = "oat_panekuk",
            mealType = 1
        ),
        Recipe(
            recipeId = 41,
            name = "Tumis Brokoli",
            calories = 79.0,
            carbs = 17.2,
            fat = 0.5,
            protein = 3.5,
            ingredients = "250 gram brokoli\n" +
                    "160 gram wortel\n" +
                    "3 siung bawang putih\n" +
                    "1/2 sdt gula pasir\n" +
                    "1 sejumput garam laut\n" +
                    "2 kecil bawang merah\n" +
                    "2.5 g kaldu jamur",
            cookingSteps = "Rebus wortel dan brokoli\n" +
                    "tujuannya supaya empuk dan matang merata ketika ditumis.\n" +
                    "Tumis bawang merah dan bawang putih yang sudah dicincang pada teflon anti lengket tanpa menggunakan minyak masak.\n" +
                    "Setelah bumbu matang\n" +
                    "masukkan wortel dan brokoli yang sudah direbus\n" +
                    "aduk merata sambil ditambahkan gula\n" +
                    "garam dan kaldu jamur.\n" +
                    "Tumis brokoli siap disajikan.",
            recipePictures = "tumis_brokoli",
            mealType = 1
        ),
        Recipe(
            recipeId = 42,
            name = "Omlete Tahu Telur",
            calories = 232.0,
            carbs = 2.5,
            fat = 18.9,
            protein = 14.3,
            ingredients = "1 besar telur\n" +
                    "1 sdt garam\n" +
                    "100 gram tahu\n" +
                    "1 sendok makan minyak goreng sawit",
            cookingSteps = "Hancurkan tahu dalam mangkuk.\n" +
                    "Kocok telur\n" +
                    "campur dengan tahu dan bumbui dengan garam.\n" +
                    "Masukkan minyak ke dalam wajan.\n" +
                    "Goreng hingga matang.",
            recipePictures = "omlete_tahu_telur",
            mealType = 1
        ),
        Recipe(
            recipeId = 44,
            name = "Muesli Simple",
            calories = 255.0,
            carbs = 42.2,
            fat = 7.3,
            protein = 7.0,
            ingredients = "100 ml susu (susu murni)\n" +
                    "2 gram chia seed\n" +
                    "50 g muesli dried fruit",
            cookingSteps = "Masukan susu dan muesli kedalam mangkok.\n" +
                    "Lalu masukan ke kulkas diamkan semalaman.\n" +
                    "Siap disantap pada pagi hari lalu taburi chia seed diatasnya.\n" +
                    "Bisa ditambahkan dengan buah buahan (opsional).",
            recipePictures = "muesli_simple",
            mealType = 1
        ),
        Recipe(
            recipeId = 45,
            name = "Jus Bokcoy",
            calories = 94.0,
            carbs = 24.6,
            fat = 0.2,
            protein = 1.0,
            ingredients = "1/2 fresh lime juice\n" +
                    "3 sdt sugar\n" +
                    "3 slices pineapple (extra sweet)\n" +
                    "3 daun chinese cabbage (bok-choy\n" +
                    "pak-choi)\n" +
                    "700 ml water",
            cookingSteps = "Cuci buah dan sayur.\n" +
                    "Rendam bokcoy dalam air panas\n" +
                    "sebentar saja.\n" +
                    "Blender semua bahan.",
            recipePictures = "jus_bokcoy",
            mealType = 1
        ),
        Recipe(
            recipeId = 46,
            name = "Jus",
            calories = 35.0,
            carbs = 8.6,
            fat = 0.3,
            protein = 0.9,
            ingredients = "1/4 iris lime\n" +
                    "6 sedang strawberries\n" +
                    "1 medium carrots\n" +
                    "1 kecil utuh tomatoes\n" +
                    "1.2 l air\n" +
                    "1 sachet sweetener classic",
            cookingSteps = "Cuci dan potong buah dan sayur.\n" +
                    "Masukkan ke blender\n" +
                    "campurkan dengan air dan pemanis\n" +
                    "tambahkan air jeruk nipis.\n" +
                    "Blender hingga halus.",
            recipePictures = "jus",
            mealType = 1
        ),
        Recipe(
            recipeId = 51,
            name = "Banana Oat Cookies",
            calories = 120.0,
            carbs = 25.3,
            fat = 2.1,
            protein = 2.6,
            ingredients = "3 kurma\n" +
                    "10 gram kismis\n" +
                    "125 gram pisang\n" +
                    "60 g havermout oatmeal instan\n" +
                    "6 g choco chips",
            cookingSteps = "Lumatkan pisang sampai halus dengan garpu.\n" +
                    "Tambahkan oat dan bahan lainnya\n" +
                    "campur sampai menyatu.\n" +
                    "Ambil sesendok campuran dan letakkan di airfryer / loyang\n" +
                    "tekan ratakan dengan garpu.\n" +
                    "Panggang kue dengan suhu 160°C selama 30 menit.\n" +
                    "Angkat dan dinginkan. Siap disajikan.",
            recipePictures = "banana_oat_cookies",
            mealType = 1
        ),
        Recipe(
            recipeId = 53,
            name = "Healthy Banana Oat Muffin",
            calories = 459.0,
            carbs = 78.7,
            fat = 12.2,
            protein = 14.1,
            ingredients = "57 gram telur\n" +
                    "1 gram garam\n" +
                    "2 gram baking soda\n" +
                    "75 gram oat\n" +
                    "157 g banana\n" +
                    "15 g cocoa powder\n" +
                    "18 g coconut sugar\n" +
                    "2 g kopi bubuk\n" +
                    "2 g baking powder\n" +
                    "2100 g squeeze yoghurt",
            cookingSteps = "Blender oats sampai jadi halus atau merupai tepung. Bisa pakai tepung lain.\n" +
                    "Campur tepung oats\n" +
                    "cocoa powder\n" +
                    "baking soda\n" +
                    "baking powder\n" +
                    "kopi instan\n" +
                    "gula dan garam.\n" +
                    "Haluskan pisang lalu campurkan dengan telur dan yogurt.\n" +
                    "Campur adonan basah dengan adonan kering.\n" +
                    "Masukan adonan muffin ke cup/loyang yang sudah anda sediakan.\n" +
                    "Tambahkan topping pilihan anda sesuai selera.\n" +
                    "Panggang di oven 180°C sekitar 20 - 25 menit.\n" +
                    "Angkat dan dinginkan.",
            recipePictures = "healthy_banana_oat_muffin",
            mealType = 1
        ),
        Recipe(
            recipeId = 55,
            name = "Tahu Telur",
            calories = 203.0,
            carbs = 3.7,
            fat = 15.0,
            protein = 14.7,
            ingredients = "1 besar telur\n" +
                    "1 siung bawang putih dimasak\n" +
                    "1 sdt garam\n" +
                    "10 gram bayam\n" +
                    "2 gram bawang merah\n" +
                    "100 gram tahu\n" +
                    "2 ml minyak goreng",
            cookingSteps = "Hancurkan tahu.\n" +
                    "Iris kecil-kecil bayam.\n" +
                    "Campur tahu\n" +
                    "bayam dengan telur.\n" +
                    "Haluskan bawang merah dan bawang putih beserta garam.\n" +
                    "Tumis bumbu halus.\n" +
                    "Masukkan adonan tahu dan masak hingga matang.",
            recipePictures = "tahu_telur",
            mealType = 1
        ),
        Recipe(
            recipeId = 56,
            name = "Chia Seed Yakult",
            calories = 154.0,
            carbs = 27.6,
            fat = 3.1,
            protein = 3.6,
            ingredients = "10 gram chia seed\n" +
                    "2 botol yakult\n" +
                    "10 gram buah naga",
            cookingSteps = "Campurkan chia seed dengan Yakult.\n" +
                    "Diamkan selama 5-10 menit di lemari es.\n" +
                    "Tambahkan potongan buah naga.",
            recipePictures = "chia_seed_yakult",
            mealType = 1
        ),
        Recipe(
            recipeId = 59,
            name = "Oat Cookies",
            calories = 24.0,
            carbs = 4.7,
            fat = 0.5,
            protein = 0.5,
            ingredients = "4 kurma\n" +
                    "20 gram keju cheddar\n" +
                    "1 sdt ekstrak vanila\n" +
                    "2 kecil pisang\n" +
                    "62 g oatmeal",
            cookingSteps = "Kupas buah pisang masukan dalam mangkok hancurkan hingga halus.\n" +
                    "Potong kurma dan parut keju masukan dalam mangkuk hancuran pisang tadi tambahkan vanilla paste. Tambahkan oatmeal.\n" +
                    "Campur adonan hingga merata cetak adonan pada loyang lalu panggang adoan di oven suhu 180°C selama kurleb 15 menitan.",
            recipePictures = "oat_cookies",
            mealType = 1
        ),
        Recipe(
            recipeId = 60,
            name = "Bakso Ayam Oat",
            calories = 197.0,
            carbs = 15.3,
            fat = 6.2,
            protein = 19.2,
            ingredients = "200 gram dada ayam (kulit tidak dimakan)\n" +
                    "1 besar telur\n" +
                    "1/2 sdm minyak kelapa\n" +
                    "1 sdt garam\n" +
                    "3 siung bawang putih\n" +
                    "5 gram daun bawang\n" +
                    "1 kecil bawang merah\n" +
                    "52.5 g instant oatmeal\n" +
                    "10 g tepung pati jagung\n" +
                    "10 g kaldu jamur\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm saos tiram",
            cookingSteps = "Goreng bawang merah dg minyak kelapa hingga kering.\n" +
                    "Campur semua bahan dalam blender kecuali daun bawang. Tambahkan daun bawang dan bawang goreng.\n" +
                    "Rebus air hingga mendidih\n" +
                    "kecilkan kompor. Masukkan bakso bentuk sesuai selera.\n" +
                    "Didihkan kembali rebus selama 10 menit\n" +
                    "hingga bakso mengambang.\n" +
                    "Sajikan bisa di sup/dibakar.",
            recipePictures = "bakso_ayam_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 65,
            name = "Healthy Oatmeal Bar",
            calories = 94.0,
            carbs = 13.7,
            fat = 3.5,
            protein = 2.8,
            ingredients = "1 sdt ekstrak vanila\n" +
                    "135 g instant oatmeal\n" +
                    "60 g creamy peanut butter\n" +
                    "150 g banana\n" +
                    "20 g choco chips",
            cookingSteps = "Hancurkan pisang dengan garpu sampai lembut.\n" +
                    "Tuang bahan lain kecuali chocochips\n" +
                    "aduk sampai rata.\n" +
                    "Masukkan choco chips\n" +
                    "aduk lagi sampai rata.\n" +
                    "Tuang ke loyang yg sudah di alas baking paper. Panggang api bawah 180° kurang lebih 15-20 menit.",
            recipePictures = "healthy_oatmeal_bar",
            mealType = 1
        ),
        Recipe(
            recipeId = 67,
            name = "Smoothie Buah Naga",
            calories = 138.0,
            carbs = 20.3,
            fat = 5.5,
            protein = 3.4,
            ingredients = "20 gram nanas\n" +
                    "4 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "30 gram buah naga\n" +
                    "30 g pisang single\n" +
                    "15 g oatmeal\n" +
                    "50 ml almond breeze almond milk original\n" +
                    "8 g chia seed",
            cookingSteps = "Bekukan semua buah buahan.\n" +
                    "Blender dengan susu dan oatmeal.\n" +
                    "Taburi dengan topping yang disukai seperti chia\n" +
                    "almond\n" +
                    "dll. dan sajikan.",
            recipePictures = "smoothie_buah_naga",
            mealType = 1
        ),
        Recipe(
            recipeId = 70,
            name = "Pancake Pisang",
            calories = 51.0,
            carbs = 10.9,
            fat = 0.3,
            protein = 1.4,
            ingredients = "140 gram pisang\n" +
                    "1/2 sdt gula pasir\n" +
                    "50 gram tepung terigu putih (semua keperluan)\n" +
                    "80 ml susu skim",
            cookingSteps = "Haluskan pisang\n" +
                    "lalu campur dengan terigu. Aduk rata.\n" +
                    "Tambahkan gula dan susu secara bertahap sambil diaduk hingga rata.\n" +
                    "Panaskan wajan antilengket\n" +
                    "tuang satu sendok sayur adonan.\n" +
                    "Apabila sudah muncul gelembung\n" +
                    "balik adonan pancake. Tunggu hingga kedua sisi matang.\n" +
                    "Sajikan hangat\n" +
                    "boleh ditambah madu atau topping sesuai selera.",
            recipePictures = "pancake_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 73,
            name = "Bolognese Wrap",
            calories = 242.0,
            carbs = 29.1,
            fat = 8.4,
            protein = 12.8,
            ingredients = "100 gram daging sapi giling\n" +
                    "200 gram wortel dimasak\n" +
                    "2 siung bawang putih dimasak\n" +
                    "200 gram mentimun (kupas)\n" +
                    "15 g saus sambal\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm saos tiram\n" +
                    "3 gandum tortilla",
            cookingSteps = "Kupas timun dan wortel lalu iris memanjang. Rebus wortel dalam air yang mendidih selama 1 menit (jangan terlalu layu).\n" +
                    "Cincang 2 siung bawang putih. Tumis hingga harum kemudian masukkan daging cincang\n" +
                    "tambahkan saus tiram\n" +
                    "saus sambal\n" +
                    "garam\n" +
                    "lada. Masak hingga daging matang.\n" +
                    "Letakkan daging cincang\n" +
                    "wortel dan timun\n" +
                    "susun memanjang diatas tortilla kemudian gulung.\n" +
                    "Panggang tortilla hingga berwarna kecoklatan. Siap disajikan.",
            recipePictures = "bolognese_wrap",
            mealType = 1
        ),
        Recipe(
            recipeId = 80,
            name = "Bungkus Salad Ayam",
            calories = 382.0,
            carbs = 27.1,
            fat = 15.0,
            protein = 33.2,
            ingredients = "100 gram dada ayam\n" +
                    "1 sedang tortilla\n" +
                    "50 gram selada\n" +
                    "1 sdm mayones\n" +
                    "30 gram wortel\n" +
                    "5 g kaldu jamur",
            cookingSteps = "Bumbui ayam dengan kaldu jamur lalu panggang di wajan tanpa minyak.\n" +
                    "Atur semuanya di atas tortilla dan gulung.\n" +
                    "Panaskan bungkus dalam wajan antilengket selama 2-3 menit.",
            recipePictures = "bungkus_salad_ayam",
            mealType = 1
        ),
        Recipe(
            recipeId = 92,
            name = "Steam Tofu Udang",
            calories = 77.0,
            carbs = 2.0,
            fat = 3.9,
            protein = 8.8,
            ingredients = "125 gram udang\n" +
                    "2 sedang telur\n" +
                    "2 gram minyak wijen\n" +
                    "2 siung bawang putih\n" +
                    "1 sdt garam laut\n" +
                    "140 g egg tofu\n" +
                    "3 g kaldu jamur\n" +
                    "9 ml saus rasa tiram",
            cookingSteps = "Tumis bawang putih cincang pakai minyak wijen.\n" +
                    "Masukan udang hingga berubah warna.\n" +
                    "Tambahkan air secukupnya untuk membuat sup.\n" +
                    "Masukkan tofu hingga semua matang.\n" +
                    "Masukan garam saus tiram kaldu jamur.\n" +
                    "Masukan Kocokan telur aduk hingga berserabut. Tambahkan daun bawang (opsional).",
            recipePictures = "steam_tofu_udang",
            mealType = 1
        ),
        Recipe(
            recipeId = 95,
            name = "Tuna Bruschetta",
            calories = 129.0,
            carbs = 17.4,
            fat = 2.7,
            protein = 8.7,
            ingredients = "55 gram tuna dalam air (kalengan)\n" +
                    "3 irisan reguler roti multigrain\n" +
                    "30 g corn kernel\n" +
                    "5 g kaldu jamur\n" +
                    "3 g lada putih bubuk\n" +
                    "40 ml squeeze yogurt original\n" +
                    "15 g mozzarella & cheddar",
            cookingSteps = "Campurkan tuna\n" +
                    "yoghurt\n" +
                    "kernel\n" +
                    "kaldu jamur dan merica.\n" +
                    "Oleskan adonan tuna di atas roti multigrain.\n" +
                    "Letakkan potongan keju diatasnya.\n" +
                    "Panggang dengan airfryer selama 15 menit.",
            recipePictures = "tuna_bruschetta",
            mealType = 1
        ),
        Recipe(
            recipeId = 96,
            name = "Oatmeal Goreng",
            calories = 416.0,
            carbs = 50.2,
            fat = 19.2,
            protein = 14.2,
            ingredients = "1 besar telur\n" +
                    "3 gram cabai merah atau rawit\n" +
                    "2 siung bawang putih\n" +
                    "10 gram selada air\n" +
                    "1 sedang wortel bayi\n" +
                    "3 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "35 g instant oatmeal",
            cookingSteps = "Panaskan frypan\n" +
                    "masukan minyak lalu tumis bawang merah\n" +
                    "bawang putih\n" +
                    "sama cabe.\n" +
                    "Setelah layu masukan telur tumis sampai mateng.\n" +
                    "Masukan wortel tumis sampai layu.\n" +
                    "Setelah layu masukkan oatmeal aduk sampai tercampur\n" +
                    "tambahkan air sedikit agar dy bisa tercampur.\n" +
                    "Tambahkan selada air atau sayuran pilihan lainnya.",
            recipePictures = "oatmeal_goreng",
            mealType = 1
        ),
        Recipe(
            recipeId = 102,
            name = "Salad Mentimun dengan Coleslaw",
            calories = 525.0,
            carbs = 88.6,
            fat = 18.1,
            protein = 10.4,
            ingredients = "195 gram selada kol\n" +
                    "135 gram jagung rebus\n" +
                    "155 gram mentimun (dengan kulit)\n" +
                    "142 gram kentang rebus\n" +
                    "35 g mayonnaise roasted sesame",
            cookingSteps = "Cuci semua sayuran dengan air mengalir.\n" +
                    "Potong sayuran sesuai keinginan.\n" +
                    "Rebus air hingga mendidih lalu rebus jagung dan kentang. Biarkan agak dingin.\n" +
                    "Campur semua sayuran dalam mangkuk dan tambahkan sisa bahan.\n" +
                    "Pindahkan ke mangkuk dan sajikan.",
            recipePictures = "salad_mentimun_dengan_coleslaw",
            mealType = 1
        ),
        Recipe(
            recipeId = 105,
            name = "Bubur Ayam Merah",
            calories = 331.0,
            carbs = 36.3,
            fat = 12.8,
            protein = 19.3,
            ingredients = "30 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "1 kecil telur\n" +
                    "100 gram nasi merah\n" +
                    "1 gelas kaldu ayam\n" +
                    "10 gram daun bawang\n" +
                    "10 gram kacang sangrai kering (dengan garam)\n" +
                    "1 sdt gula pasir\n" +
                    "2 sdt fiber creme",
            cookingSteps = "Membuat telur dadar. Gulung dan potong. Sisihkan untuk digunakan nanti.\n" +
                    "Rebus dada ayam bersama bawang putih\n" +
                    "daun salam dan garam secukupnya hingga matang. Tiriskan lalu suwir-suwir dagingnya.\n" +
                    "Hancurkan beras merah dan rebus dengan kaldu ayam\n" +
                    "tambahkan serat creme\n" +
                    "gula dan garam secukupnya.\n" +
                    "Tuang bubur ke dalam mangkuk. Tambahkan ayam suwir\n" +
                    "kacang tanah sangrai\n" +
                    "irisan telur dadar untuk topping dan taburi daun bawang.",
            recipePictures = "bubur_ayam_merah",
            mealType = 1
        ),
        Recipe(
            recipeId = 115,
            name = "Sup Telur Pedas",
            calories = 382.0,
            carbs = 17.0,
            fat = 32.0,
            protein = 10.1,
            ingredients = "2 besar telur rebus\n" +
                    "1 gram kaldu jamur\n" +
                    "3 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "100 gram brokoli\n" +
                    "1 siung bawang putih\n" +
                    "2 gelas air\n" +
                    "2 kecil bawang merah\n" +
                    "5 sdm minyak goreng\n" +
                    "100 gram sawi hijau",
            cookingSteps = "Haluskan cabe dan bawang merah\n" +
                    "bawang putih lalu tumis dengan minyak goreng.\n" +
                    "Lalu masukkan air\n" +
                    "tunggu mendidih dan masukkan telur agar matang\n" +
                    "serta garam dan kaldu jamur.\n" +
                    "Saat telur sudah matang\n" +
                    "masukkan sayuran dan tunggu hingga matang sesuai selera.",
            recipePictures = "sup_telur_pedas",
            mealType = 1
        ),
        Recipe(
            recipeId = 117,
            name = "Bubur Ayam Oatmeal",
            calories = 434.0,
            carbs = 38.3,
            fat = 15.9,
            protein = 35.9,
            ingredients = "100 gram dada ayam\n" +
                    "1 sdt minyak wijen\n" +
                    "1 siung bawang putih\n" +
                    "1 sdt jahe\n" +
                    "1 iris daun bawang\n" +
                    "20 gram sereh\n" +
                    "1 kecil bawang merah\n" +
                    "35 g instant oatmeal",
            cookingSteps = "Tumis baput bawang merah\n" +
                    "bawang putih sereh dan jahe.\n" +
                    "Masukkan ayam\n" +
                    "tumis sebentar.\n" +
                    "Tambahkan air\n" +
                    "masak hingga mendidih.\n" +
                    "Masukkan oatmeal. Bumbui dengan garam\n" +
                    "merica\n" +
                    "kaldu jamur (optional).\n" +
                    "Tambahkan minyak wijen.\n" +
                    "Masak hingga tekstur sesuai yg di inginkan.\n" +
                    "Taburi dengan daun bawang saat disajikan.",
            recipePictures = "bubur_ayam_oatmeal",
            mealType = 1
        ),
        Recipe(
            recipeId = 125,
            name = "Salad Sayur",
            calories = 134.0,
            carbs = 32.7,
            fat = 0.7,
            protein = 3.3,
            ingredients = "20 gram jeruk nipis\n" +
                    "1/4 mangkok\n" +
                    "irisan paprika merah manis\n" +
                    "2 sdm kemangi\n" +
                    "1/2 mangkok\n" +
                    "irisan mangga\n" +
                    "1/2 mangkok\n" +
                    "dicincang kale\n" +
                    "10 gram bayam\n" +
                    "100 gram bengkoang\n" +
                    "25 gram bawang bombay",
            cookingSteps = "Potong dan iris semua bahan sesuai selera.\n" +
                    "Tambahkan perasan jeruk nipis.\n" +
                    "Tambahkan sedikit lada dan garam.\n" +
                    "Aduk rata.",
            recipePictures = "salad_sayur",
            mealType = 1
        ),
        Recipe(
            recipeId = 126,
            name = "Bakwan Sayur Teflon",
            calories = 216.0,
            carbs = 34.6,
            fat = 6.7,
            protein = 4.4,
            ingredients = "30 gram kecambah\n" +
                    "22 gram wortel\n" +
                    "3 gram seledri\n" +
                    "7 gram daun bawang\n" +
                    "55 ml air\n" +
                    "1 sdm extra virgin olive oil\n" +
                    "40 g tepung bakwan",
            cookingSteps = "Potong2 wortel\n" +
                    "seledri n daun bawang.\n" +
                    "Masukan kecambah\n" +
                    "wortel\n" +
                    "air dan tepung bakwan. Aduk rata dan bumbui dengan garam dan merica.\n" +
                    "Beri teflon minyak zaitun. Panaskan minyak (jangan sampai terlalu panas).\n" +
                    "Goreng hingga matang.",
            recipePictures = "bakwan_telur_teflon",
            mealType = 1
        ),
        Recipe(
            recipeId = 127,
            name = "Green Smoothie",
            calories = 229.0,
            carbs = 33.7,
            fat = 7.6,
            protein = 6.9,
            ingredients = "50 gram celery\n" +
                    "10 g pisang single\n" +
                    "35 g oat\n" +
                    "15 g chia seed",
            cookingSteps = "Cuci bersih dab siapkan bahan.\n" +
                    "Blend jadi satu.\n" +
                    "Serving tabur chia seed.",
            recipePictures = "green_smoothie",
            mealType = 1
        ),
        Recipe(
            recipeId = 129,
            name = "Omlet Bayam",
            calories = 95.0,
            carbs = 7.5,
            fat = 4.8,
            protein = 7.0,
            ingredients = "3 sedang telur\n" +
                    "3 gram cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 ikat bayam\n" +
                    "2 kecil bawang merah\n" +
                    "5 g margarin\n" +
                    "2 g royco ayam",
            cookingSteps = "Pecahkan telur\n" +
                    "masukan cincang bawang merah\n" +
                    "bawang putih\n" +
                    "cabai. Kocok.\n" +
                    "Setelah tercampur masukan royco secukupnya.\n" +
                    "Lalu masukan bayam yang sudah di ambil bagian daun nya saja.\n" +
                    "Siapkan pan anti lengket\n" +
                    "masukan margarin sedikit saja.\n" +
                    "Masukan adonan omlet bayam\n" +
                    "lalu masak hingga matang.\n" +
                    "Bolak balik adonan dan pastikan semua matang merata.\n" +
                    "Setelah yakin sudah matang\n" +
                    "angkat dan sajikan.",
            recipePictures = "omelet_bayam",
            mealType = 1
        ),
        Recipe(
            recipeId = 131,
            name = "Pokcoy Kuah Pedas",
            calories = 348.0,
            carbs = 26.2,
            fat = 14.2,
            protein = 29.3,
            ingredients = "100 gram dada ayam\n" +
                    "1 besar telur\n" +
                    "10 gram cabai merah atau rawit\n" +
                    "1/2 sdt minyak wijen\n" +
                    "2 siung bawang putih\n" +
                    "1/2 gelas air\n" +
                    "1/2 sdt gula pasir\n" +
                    "1 sejumput garam laut\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm kecap manis\n" +
                    "100 gram pakcoy\n" +
                    "15 g saus sambal\n" +
                    "1 g royco ayam\n" +
                    "5 ml minyak kelapa\n" +
                    "1 g lada putih bubuk\n" +
                    "50 gram jamur kancing\n" +
                    "150 gram tahu susu",
            cookingSteps = "Rebus air dan sedikit garam\n" +
                    "masukan pakcoy\n" +
                    "tisirkan\n" +
                    "tata dipiring.\n" +
                    "Tumis bawang putih dan bawang merah dengan minyak kelapa sampai sedikit layu. Masukkan butir telur ayam biarkan matang\n" +
                    "dibalik baru di orak arik\n" +
                    "masukkan cabai\n" +
                    "tumis sampai harum.\n" +
                    "Masukkan ayam dan jamur kancing sampai ayam matang (berubah warna). Tambahkan garam\n" +
                    "lada\n" +
                    "royco\n" +
                    "dan gula pasir.\n" +
                    "Masukkan saus sambal\n" +
                    "kecap manis\n" +
                    "minyak wijen\n" +
                    "aduk sampai rata\n" +
                    "tambahkan air sesuai selera kemudian test rasa.\n" +
                    "Terakhir masukkan tahu sebentar tidak usah diaduk-aduk\n" +
                    "tunggu sampai air sedikit berkurang. Matikan api kompor\n" +
                    "siram diatas pokcoy.",
            recipePictures = "pokcoy_kuah_pedas",
            mealType = 1
        ),
        Recipe(
            recipeId = 133,
            name = "Bayam Tumis Tahu",
            calories = 397.0,
            carbs = 11.8,
            fat = 27.1,
            protein = 31.9,
            ingredients = "1 besar telur\n" +
                    "150 gram bayam\n" +
                    "1 sdm minyak goreng\n" +
                    "230 gram tahu\n" +
                    "2 g royco ayam\n" +
                    "5 gram udang rebon\n" +
                    "1 sdm saos tiram",
            cookingSteps = "Tumis udang\n" +
                    "lalu masukkan telur dan di orak-arik dengan minyak goreng.\n" +
                    "Tambahkan tahu yg sudah di hancurkan.\n" +
                    "Sesudah wangi\n" +
                    "masukkan bayam lalu tambahkan saos tiram dan royco.\n" +
                    "Tunggu hingga bayam benar-benar matang.\n" +
                    "Siap di hidangkan.",
            recipePictures = "bayam_tumis_tahu",
            mealType = 1
        ),
        Recipe(
            recipeId = 135,
            name = "Telor Dadar",
            calories = 246.0,
            carbs = 0.0,
            fat = 19.2,
            protein = 11.0,
            ingredients = "1 besar egg\n" +
                    "50 gram chives\n" +
                    "1 sdm\n" +
                    "cincang scallions or spring onions\n" +
                    "25 g sosis ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm canola oil",
            cookingSteps = "Masukkan kucai kedalam teflon yg sudah diminyaki\n" +
                    "masak sampai layu.\n" +
                    "Kocok sosis potong dengan telor\n" +
                    "masukkan bumbu dan daun bawang secukupnya.\n" +
                    "Tuangkan adonan telor ke atas kucai\n" +
                    "masak sampai matang.",
            recipePictures = "telor_dadar",
            mealType = 1
        ),
        Recipe(
            recipeId = 139,
            name = "Nasi Goreng Mentega",
            calories = 344.0,
            carbs = 41.1,
            fat = 14.9,
            protein = 8.2,
            ingredients = "1 besar telur dadar atau telur orak-arik\n" +
                    "1 1/2 mangkok\n" +
                    "dimasak nasi putih\n" +
                    "1 siung bawang putih\n" +
                    "100 g mixed vegetables\n" +
                    "27 g margarine",
            cookingSteps = "Tumis bawang putih cincang dengan margarine.\n" +
                    "Masukkan sayur campur dan diikuti dengan nasi.\n" +
                    "Tambahkan telur\n" +
                    "orak arik\n" +
                    "dan campur semuanya.",
            recipePictures = "nasi_goreng_mentega",
            mealType = 1
        ),
        Recipe(
            recipeId = 141,
            name = "Smoothies Berry",
            calories = 136.0,
            carbs = 26.9,
            fat = 2.1,
            protein = 5.3,
            ingredients = "100 gram mangga\n" +
                    "100 gram stroberi\n" +
                    "1/2 buah naga\n" +
                    "200 ml skimmed milk (200ml)\n" +
                    "20 g granola creations cinnamon & raisin",
            cookingSteps = "ara memasak\n" +
                    "Masukkan semua bahan\n" +
                    "kecuali granola dan blend.\n" +
                    "Tuang ke mangkuk. Tambahkan granola.\n" +
                    "1 anggota telah menambahkan resep ini ke b",
            recipePictures = "smoothies_berry",
            mealType = 1
        ),
        Recipe(
            recipeId = 145,
            name = "Oseng Sawi Telur",
            calories = 113.0,
            carbs = 9.5,
            fat = 5.2,
            protein = 7.9,
            ingredients = "1 besar telur\n" +
                    "1 siung bawang putih\n" +
                    "1 kecil bawang merah\n" +
                    "50 gram sawi hijau",
            cookingSteps = "Oseng bawang merah dan bawang putih.\n" +
                    "Masukkan sawi dan sedikit air.\n" +
                    "Tambahkan telur\n" +
                    "bumbui dengan garam dan merica.",
            recipePictures = "oseng_sawi_telur",
            mealType = 1
        ),
        Recipe(
            recipeId = 149,
            name = "Terong Balado Rebus",
            calories = 302.0,
            carbs = 44.9,
            fat = 11.2,
            protein = 17.6,
            ingredients = "50 gram cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "250 gram terung\n" +
                    "2 siung bawang putih\n" +
                    "400 ml air\n" +
                    "120 gram tempe\n" +
                    "3 sedang bawang merah\n" +
                    "2 g terasi\n" +
                    "1.75 g magic lezat",
            cookingSteps = "Haluskan semua bahan kecuali tempe dan terong.\n" +
                    "Tuang bumbu ke teflon dan tambahkan air secukupnya\n" +
                    "tutup teflon dan rebus hingga mendidih.\n" +
                    "Iris terong melingkar\n" +
                    "susun ke dalam teflon setelah rebusan cabai mendidih\n" +
                    "jangan ditutup kambali.\n" +
                    "Detelah terong setengah matang\n" +
                    "masukkan tempe.\n" +
                    "Masak hingga matang dan air surut\n" +
                    "jangan lupa koreksi rasa.",
            recipePictures = "terong_balado_rebus",
            mealType = 1
        ),
        Recipe(
            recipeId = 152,
            name = "Shakshuka",
            calories = 283.0,
            carbs = 31.1,
            fat = 0.0,
            protein = 18.6,
            ingredients = "2 besar telur\n" +
                    "1 siung bawang putih\n" +
                    "2 besar utuh tomat merah\n" +
                    "1 sdm saus tomat\n" +
                    "100 gram bawang bombay\n" +
                    "50 gram jamur kancing",
            cookingSteps = "Kupas kulit tomat\n" +
                    "lalu di cincang/blender. Cincang bawang putih dan bawang bombay\n" +
                    "dan jamur champignon.\n" +
                    "Tumis bawang bombay dan bawang putih hingga layu\n" +
                    "tambahkan jamur tumis lagi hingga jamur berubah warna\n" +
                    "masukkan pure tomat\n" +
                    "saos tomat dan air. Tambahkan Garam\n" +
                    "lada\n" +
                    "dan bumbu pilihan lainnya.\n" +
                    "Tambahkan telur\n" +
                    "tutup pan hingga telur matang.\n" +
                    "Makanan siap dihidangkan.",
            recipePictures = "shakshuka",
            mealType = 1
        ),
        Recipe(
            recipeId = 153,
            name = "Toast Avocado",
            calories = 488.0,
            carbs = 40.0,
            fat = 37.5,
            protein = 6.8,
            ingredients = "1 irisan reguler roti gandum panggang\n" +
                    "1 alpukat\n" +
                    "5 g susu kental manis\n" +
                    "7.5 g mentega\n" +
                    "10 g palm sugar",
            cookingSteps = "Panggang roti dengan mentega.\n" +
                    "Keruk alpukat letakkan diatas roti.\n" +
                    "Taburi gula palm.\n" +
                    "Tuang susu kental manis.",
            recipePictures = "toast_avocado",
            mealType = 1
        ),
        Recipe(
            recipeId = 154,
            name = "Oat Almond Butter Cookies",
            calories = 46.0,
            carbs = 4.4,
            fat = 2.9,
            protein = 1.4,
            ingredients = "50 gram mentega almond\n" +
                    "50 gram selai kacang\n" +
                    "1 sdt ekstrak vanila\n" +
                    "1 sdt baking soda\n" +
                    "2.6 g stevia sweetener\n" +
                    "15 g original yogurt\n" +
                    "100 g oat\n" +
                    "10 g raw cacao powder\n" +
                    "20 g dark chocolate 70%",
            cookingSteps = "Blender oat menjadi tepung.\n" +
                    "Campur semua bahan kecuali choco chips. Aduk rata.\n" +
                    "Terakhir tambahkan chocochips dan dibentuk.\n" +
                    "Panggang di api 180°C 25menit.",
            recipePictures = "oat_almond_butter_cookies",
            mealType = 1
        ),
        Recipe(
            recipeId = 155,
            name = "Blueband Biskuit Lunak",
            calories = 300.0,
            carbs = 32.5,
            fat = 17.3,
            protein = 3.6,
            ingredients = "1 besar telur\n" +
                    "60 gram gula\n" +
                    "100 gram tepung terigu putih (semua keperluan)\n" +
                    "4 g tepung pati jagung\n" +
                    "125 ml dark compound chocolate\n" +
                    "20 g palm sugar\n" +
                    "100 g cake & cookie\n" +
                    "55 g tepung terigu kunci biru",
            cookingSteps = "Kocok mentega dan gula. Kemudian tambahkan telur dan aduk.\n" +
                    "Tambahkan tepung yang sudah diayak dan bahan lainnya. Campur dengan baik.\n" +
                    "Bentuk biskuit dan sebarkan di atas loyang. Panggang selama 18 menit pada suhu 180°C-190°C.",
            recipePictures = "blueband_biskuit_lunak",
            mealType = 1
        ),
        Recipe(
            recipeId = 161,
            name = "Oatbanana Cookies",
            calories = 385.0,
            carbs = 71.4,
            fat = 6.7,
            protein = 13.7,
            ingredients = "100 gram pisang\n" +
                    "5 gram chia seed\n" +
                    "70 gram oat",
            cookingSteps = "Penyetkan pisang sampai hancur.\n" +
                    "Masukan oatmeal dan chiaseed.\n" +
                    "Aduk lalu cetak dan panggang di suhu 200•C selama 15menit.",
            recipePictures = "oatbanana_cookies",
            mealType = 1
        ),
        Recipe(
            recipeId = 167,
            name = "Tumis Pakcoy",
            calories = 30.0,
            carbs = 6.2,
            fat = 0.6,
            protein = 1.2,
            ingredients = "1 sdt bawang putih bubuk\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "1 gelas air\n" +
                    "1/2 sdt\n" +
                    "dikemas gula merah\n" +
                    "200 gram pakcoy\n" +
                    "0\n" +
                    "5 g bawang merah goreng\n" +
                    "2 g royco ayam\n" +
                    "1\n" +
                    "2 g lada putih bubuk",
            cookingSteps = "Masukkan semua bumbu dan air.\n" +
                    "Setelah mendidih masukkan sayur masak 2menit.\n" +
                    "Sajikan dalam wadah taburi bawang goreng.",
            recipePictures = "tumis_pakcoy",
            mealType = 1
        ),
        Recipe(
            recipeId = 168,
            name = "Banana Oat Cake",
            calories = 322.0,
            carbs = 32.2,
            fat = 16.0,
            protein = 14.4,
            ingredients = "58 gram telur\n" +
                    "69 gram pisang\n" +
                    "20 gram kacang almond\n" +
                    "1 lembar singles\n" +
                    "16 g selai kacang\n" +
                    "2 g classic\n" +
                    "52 ml low fat high calcium rasa coklat (200ml)\n" +
                    "50 g instant whole oats",
            cookingSteps = "Kocok telur menggunakan ballon wishk sampai berbusa\n" +
                    "masukan kopi\n" +
                    "selai\n" +
                    "dan pisang yg sudah dilumatkan terlebih dahulu. Kocok sebentar.\n" +
                    "Masukan oat (me: diblender) dan almond cacah aduk rata. Tuang susu aduk kembali.\n" +
                    "Tuang ke loyang yg sdh dioles minyak\n" +
                    "ratakan\n" +
                    "beri topping keju slice.\n" +
                    "Panggang di oven yg sdh dipanaskan selama 30m.",
            recipePictures = "banana_oat_cake",
            mealType = 1
        ),
        Recipe(
            recipeId = 173,
            name = "Setup Roti Gandum",
            calories = 183.0,
            carbs = 50.0,
            fat = 6.6,
            protein = 10.4,
            ingredients = "30 gram kismis\n" +
                    "1 besar putih telur\n" +
                    "5 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "250 ml susu uht full cream (250ml)\n" +
                    "6 g sweetener diabtx\n" +
                    "350 g roti gandum",
            cookingSteps = "Campur putih telur dengan gula lalu kocok.\n" +
                    "Campurkan 250ml susu UHT/Low Fat lalu aduk.\n" +
                    "Potong roti gandum sesuai selera lalu masukan kedalam adonan basah lalu aduk dengan rata.\n" +
                    "Tuangkan 1/2 adonan kedalam cetakan taburkan kismis sesuai selera.\n" +
                    "Tuangkan sisa adonan ratakan lalu taburkan kismis & kacang Almond sesuai selera.\n" +
                    "Kukus 20 menit atau sampai adonan padat (tusuk dengan tusuk gigi untuk test kepadatan).",
            recipePictures = "setup_roti_gandum",
            mealType = 1
        ),
        Recipe(
            recipeId = 174,
            name = "Oat Goreng Special",
            calories = 812.0,
            carbs = 146.9,
            fat = 16.0,
            protein = 40.9,
            ingredients = "1 besar telur\n" +
                    "14 sedang paprika merah manis\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "13 gram cabai merah atau rawit\n" +
                    "47 gram daging dada ayam (ayam pedaging)\n" +
                    "47 gram brokoli\n" +
                    "19 gram bawang putih\n" +
                    "2 gram daun bawang\n" +
                    "14 gram sawi putih\n" +
                    "6 gram bawang merah\n" +
                    "20 g oat\n" +
                    "6 g saus tiram\n" +
                    "2 g royco ayam\n" +
                    "9 g lada putih bubuk\n" +
                    "13 gram bawang bombay\n" +
                    "2.5 g boncabe\n" +
                    "5 g pilus sapi panggang",
            cookingSteps = "Pertama haluskan bawang putih\n" +
                    "merah\n" +
                    "cabai diberi garam dan merica.\n" +
                    "Bumbu yang sudah dihaluskan ditumis dan bawang bombay lalu masukan telur dan oat+ sayur²nya.\n" +
                    "Diaduk² lalu masukan 1sdt Saori dan bumbu royco secukupnya dan tambahkan bumbu nasi goreng sajiku sejumput aja!\n" +
                    "Ayam dimasak diberi bumbu bawang putih\n" +
                    "merica\n" +
                    "bon cabe dan ohaiong. Lalu di panggang pake telfon sampai matang.\n" +
                    "Sajikan deh! Tambahkan pilus buat topping aja wkwk.",
            recipePictures = "oat_goreng_special",
            mealType = 1
        ),
        Recipe(
            recipeId = 179,
            name = "Omelet Tuna",
            calories = 126.0,
            carbs = 2.2,
            fat = 6.4,
            protein = 14.7,
            ingredients = "30 gram ikan tuna\n" +
                    "1 besar telur\n" +
                    "50 gram bayam dimasak (dari segar)",
            cookingSteps = "Letakkan bayam di teflon. Tutup teflon.\n" +
                    "Letakkan tuna diatas bayam.\n" +
                    "Tuangkan telur diatasnya. Tutup teflon.\n" +
                    "Balikkan dan selesai.",
            recipePictures = "omelet_tuna",
            mealType = 1
        ),
        Recipe(
            recipeId = 181,
            name = "Pizza Oatmeal Teflon",
            calories = 162.0,
            carbs = 21.4,
            fat = 5.7,
            protein = 7.1,
            ingredients = "25 gram keju mozzarella\n" +
                    "40 gram daging kornet\n" +
                    "1 sdt\n" +
                    "tumbuk oregano\n" +
                    "100 g instant oatmeal\n" +
                    "2 sdm saus sambal\n" +
                    "1/4 sedang bawang bombay",
            cookingSteps = "Uleni oatmeal dengan sedikit air.\n" +
                    "Letakkan ke teflon.\n" +
                    "Beri saos lalu beri toping.\n" +
                    "Tambahkan oregano dan parutan mozzarella.\n" +
                    "Tunggu 30 menit dgn ditutup dan api kecil.",
            recipePictures = "pizza_oatmeal_teflon",
            mealType = 1
        ),
        Recipe(
            recipeId = 186,
            name = "Oatmeal Goreng dan Omelet",
            calories = 414.0,
            carbs = 57.8,
            fat = 16.4,
            protein = 16.4,
            ingredients = "1/4 sdm minyak nabati\n" +
                    "1/4 mangkok kaldu jamur\n" +
                    "1 besar telur dadar\n" +
                    "2 sdm cabai merah atau rawit\n" +
                    "1/2 mentimun (dengan kulit)\n" +
                    "2 siung bawang putih\n" +
                    "1 sejumput garam laut\n" +
                    "2 kecil bawang merah\n" +
                    "2 sendok makan instant oatmeal\n" +
                    "1/2 sedang bawang bombay",
            cookingSteps = "Sangrai oatmeal sampai kecoklatan.\n" +
                    "Tumis bawang merah\n" +
                    "beang putih\n" +
                    "cabai\n" +
                    "dan bawang bombay sampai layu dan harum.\n" +
                    "Tuang oatmeal yg sudah di sangrai.\n" +
                    "Tambah air sedikit saja.\n" +
                    "Tambahkan garam+kaldu jamur secukupnya sambil diaduk.\n" +
                    "Hidangkan dengan toping telur dan mentimun.",
            recipePictures = "oatmeal_goreng_dan_omelet",
            mealType = 1
        ),
        Recipe(
            recipeId = 188,
            name = "Gulungan Salad Daging Sapi Asap",
            calories = 180.0,
            carbs = 17.0,
            fat = 6.8,
            protein = 11.7,
            ingredients = "1 sedang rice paper\n" +
                    "20 gram wortel\n" +
                    "20 gram mentimun (kupas)\n" +
                    "30 gram selada air\n" +
                    "50 g smoked beef\n" +
                    "17 g keju slice",
            cookingSteps = "Celupkan rice paper ke air hangat hingga lemas & mudah dibentuk.\n" +
                    "Susun semua bahan di atas rice paper lalu gulung.\n" +
                    "Salad siap disajikan.",
            recipePictures = "gulungan-salad_daging_sapi_asap",
            mealType = 1
        ),
        Recipe(
            recipeId = 191,
            name = "Mangkuk Smoothie Hijau",
            calories = 281.0,
            carbs = 37.9,
            fat = 14.2,
            protein = 5.0,
            ingredients = "50 gram semangka\n" +
                    "30 gram selada\n" +
                    "80 gram apel\n" +
                    "50 gram alpukat\n" +
                    "50 gram blewah melon\n" +
                    "4 gram kacang almond\n" +
                    "20 g gourmet blend dark chocolate & banana granola",
            cookingSteps = "Blender semua bahan kecuali kacang almond dan granola.\n" +
                    "Sajikan ke dalam mangkok.\n" +
                    "Letakkan kacang dan granola sebagai topping.",
            recipePictures = "mangkuk_smoothie_hijau",
            mealType = 1
        ),
        Recipe(
            recipeId = 192,
            name = "Granola Homemade",
            calories = 266.0,
            carbs = 42.4,
            fat = 8.7,
            protein = 6.5,
            ingredients = "75 gram dry roasted unsalted peanuts\n" +
                    "1 1/2 tbsps honey\n" +
                    "2 sdt extra virgin olive oil\n" +
                    "400 g havermout rolled oat\n" +
                    "45 g kurma\n" +
                    "75 g gula aren",
            cookingSteps = "Sangrai rolled oat\n" +
                    "kacang tanah hingga kecoklatan.\n" +
                    "Masukkan bahan2 untuk saus\n" +
                    "olive oil\n" +
                    "gula aren\n" +
                    "madu\n" +
                    "kurma.\n" +
                    "Campur dengan rata siap di sajikan dengan susu atau yogurt.",
            recipePictures = "granola_homemade",
            mealType = 1
        ),
        Recipe(
            recipeId = 194,
            name = "Chocolate Oatmeal Bars",
            calories = 78.0,
            carbs = 12.0,
            fat = 3.3,
            protein = 1.3,
            ingredients = "128 gram coklat manis atau gelap\n" +
                    "1 sdt ekstrak vanila\n" +
                    "125 gram kismis (tanpa biji)\n" +
                    "14 reguler/biasa marshmallow\n" +
                    "5 sdm mentega\n" +
                    "256 g rolled oats",
            cookingSteps = "Dalam panci besar\n" +
                    "lelehkan chocolate chip\n" +
                    "mentega ringan\n" +
                    "dan marshmallow dengan api kecil\n" +
                    "aduk hingga lembut.\n" +
                    "Hapus dari panas; agak dingin. Aduk vanilla. Aduk oat dan sisa bahan.\n" +
                    "Teteskan satu sendok makan bulat ke atas kertas lilin dan bentuk menjadi lingkaran atau batang. Tutup dan dinginkan 2 sampai 3 jam. Diamkan pada suhu kamar sekitar 15 menit sebelum disajikan. Simpan tertutup rapat di lemari es. Menghasilkan sekitar 3 lusin.\n" +
                    "6 anggota telah menambahkan resep ini ke buku m",
            recipePictures = "chocolate_oatmeal_bars",
            mealType = 1
        ),
        Recipe(
            recipeId = 195,
            name = "Bola Energi Oatmeal",
            calories = 138.0,
            carbs = 12.0,
            fat = 8.9,
            protein = 4.8,
            ingredients = "1 sdm madu\n" +
                    "80 g creamy peanut butter\n" +
                    "1/2 gelas kering rolled oats\n" +
                    "25 g dark chocolate 70%",
            cookingSteps = "Campurkan semua bahan dalam mangkuk kecil dan aduk hingga tercampur rata.\n" +
                    "Dinginkan di lemari es selama 30 menit.\n" +
                    "Gunakan sendok atau sendok makan untuk membagi campuran menjadi 6 bola secara merata. Gunakan tangan Anda untuk membentuk bola. Nikmati sekarang dan simpan sisanya untuk nanti dengan menyimpannya dalam wadah tertutup di lemari es hingga 1 minggu.",
            recipePictures = "bola_energi_oatmeal",
            mealType = 1
        ),
        Recipe(
            recipeId = 202,
            name = "Pancake Labu Sederhana",
            calories = 39.0,
            carbs = 6.3,
            fat = 1.3,
            protein = 1.5,
            ingredients = "3 besar kuning telur\n" +
                    "600 gram labu\n" +
                    "5\n" +
                    "2 g stevia sweetener\n" +
                    "30 g tepung tapioka\n" +
                    "30 g segitiga biru",
            cookingSteps = "Kukus Labu Kuning dan haluskan dg garpu sampe hancur.\n" +
                    "Masukkan Tepung Terigu\n" +
                    "Tapioka\n" +
                    "Kuning Telur dan Stevia.\n" +
                    "Panggang tanpa minyak di atas teflon dg api kecil per 1 sdm munjung\n" +
                    "dibalik sampe matang merata.",
            recipePictures = "pancake_labu_sederhana",
            mealType = 1
        ),
        Recipe(
            recipeId = 203,
            name = "Yogurt dan Chia Bowl",
            calories = 620.0,
            carbs = 69.1,
            fat = 32.1,
            protein = 20.4,
            ingredients = "100 gram chia seed\n" +
                    "1 buah naga\n" +
                    "80 g blueberry yoghurt",
            cookingSteps = "Potong dadu buah naga.\n" +
                    "Siram dengan yoghurt.\n" +
                    "Taruh chia seed diatasnya.",
            recipePictures = "yogurt_dan_chia_bowl",
            mealType = 1
        ),
        Recipe(
            recipeId = 209,
            name = "Papaya Yogurt with Almonds",
            calories = 210.0,
            carbs = 42.7,
            fat = 3.2,
            protein = 5.5,
            ingredients = "1 mangkok\n" +
                    "potong dadu pepaya\n" +
                    "5 gram kacang almond\n" +
                    "1 sdm madu\n" +
                    "71 ml yogurt plain",
            cookingSteps = "Potong pepaya menjadi ukuran yang lebih kecil\n" +
                    "kemudian sisihkan.\n" +
                    "Cincang almond menjadi ukuran yang lebih kecil.\n" +
                    "Masukkan yogurt ke dalam mangkok.\n" +
                    "Tambahkan pepaya dan almond yang sudah dipotong.\n" +
                    "Tambahkan juga madu\n" +
                    "jika ingin lebih manis.",
            recipePictures = "papaya_yogurt_with_almonds",
            mealType = 1
        ),
        Recipe(
            recipeId = 212,
            name = "Bubur Manis Oat",
            calories = 187.0,
            carbs = 34.1,
            fat = 4.3,
            protein = 3.2,
            ingredients = "5 gram chia seed\n" +
                    "1 gelas air\n" +
                    "1 sejumput garam laut\n" +
                    "1 sdt fiber creme\n" +
                    "21 g oat\n" +
                    "1 ml sweetener\n" +
                    "15 g gula aren",
            cookingSteps = "Masukan semua bahan dalam wajan.\n" +
                    "Tambahkan daun pandan agar wangi.\n" +
                    "Aduk hingga bubur mengental.\n" +
                    "Bubur siap di ajukan.\n" +
                    "Tambahkan sejumput chia seed untuk hiasan.",
            recipePictures = "bubur_manis_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 213,
            name = "Salad Roll",
            calories = 183.0,
            carbs = 15.0,
            fat = 7.6,
            protein = 15.2,
            ingredients = "1 sedang rice paper\n" +
                    "20 gram wortel\n" +
                    "20 gram mentimun (kupas)\n" +
                    "50 gram selada air\n" +
                    "20 g cheddar cheese\n" +
                    "50 g smoked beef",
            cookingSteps = "Rendam rice paper ke dalam air hangat sebentar.\n" +
                    "Setelah rice paper lemas & mudah dibentuk\n" +
                    "masukkan bahan2\n" +
                    "lalu lipat/gulung.\n" +
                    "Makan!",
            recipePictures = "salad_roll",
            mealType = 1
        ),
        Recipe(
            recipeId = 218,
            name = "Smoothie Coklat Pisang",
            calories = 280.0,
            carbs = 60.4,
            fat = 4.0,
            protein = 5.8,
            ingredients = "200 gram pisang\n" +
                    "5 gram chia seed\n" +
                    "10 g mueslix harvest fruit\n" +
                    "10 g coklat bubuk",
            cookingSteps = "Pisang bekuin.\n" +
                    "Blender sama bubuk coklat.\n" +
                    "Kasih taburan topping.",
            recipePictures = "smoothie_coklat_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 219,
            name = "Snack Oat",
            calories = 26.0,
            carbs = 3.4,
            fat = 1.1,
            protein = 0.7,
            ingredients = "1 besar putih telur\n" +
                    "130 gram pisang\n" +
                    "25 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "100 g quick cooking oatmeal\n" +
                    "1 sdm extra virgin olive oil",
            cookingSteps = "Lumatkan pisang.\n" +
                    "Masukkan putih telur kedalam pisang yg dilumatkan.\n" +
                    "Aduk dan campurkan semua bahan kedalam pisang (oat\n" +
                    "almond\n" +
                    "olive oil).\n" +
                    "Bagi menjadi 30 bagian (10gr).\n" +
                    "Panggang 20 menit (suhu 180).\n" +
                    "Dingingkan\n" +
                    "siap disantap.\n" +
                    "4 anggota telah menambahka",
            recipePictures = "snack_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 220,
            name = "Salad",
            calories = 475.0,
            carbs = 55.7,
            fat = 26.5,
            protein = 10.4,
            ingredients = "jus dari 1 jeruk nipis\n" +
                    "50 gram tomat\n" +
                    "50 gram selada\n" +
                    "30 gram jamur dimasak (lemak ditambahkan dalam masakan)\n" +
                    "50 gram bawang bombay dimasak\n" +
                    "50 gram telur ceplok\n" +
                    "4 sdt\n" +
                    "tumbuk oregano\n" +
                    "1 sdm garam\n" +
                    "50 gram mentimun (kupas)\n" +
                    "2 sdm madu\n" +
                    "1 sdm olive oil\n" +
                    "3 g lada putih bubuk",
            cookingSteps = "Cuci semua bahan untuk salad.\n" +
                    "Kupas dan potong tipis bawang bombay lalu tumis dengan sedikit minyak dan garam secukupnya.\n" +
                    "Potong potong tipis jamur lalu tumis dengan sedikit minyak dan garam secukupnya.\n" +
                    "Kupas mentimun lalu buang bijinya dan potong kecil kecil.\n" +
                    "Buang biji tomat dan potong kecil kecil.\n" +
                    "Keringkan selada dan potong sesuai selera.\n" +
                    "Ceplok telur dengan sedikit minyak dan beri garam secukupnya.\n" +
                    "Untuk dressingnya : Campurkan air jeruk nipis\n" +
                    "madu\n" +
                    "garam\n" +
                    "merica\n" +
                    "oregano\n" +
                    "dan olive oil.",
            recipePictures = "salad",
            mealType = 1
        ),
        Recipe(
            recipeId = 222,
            name = "Salad Selada Nanas Tomat Cerry",
            calories = 233.0,
            carbs = 14.2,
            fat = 19.4,
            protein = 2.2,
            ingredients = "1 iris jeruk nipis\n" +
                    "1/4 sdt bubuk cabai\n" +
                    "50 gram nanas\n" +
                    "50 gram selada daun hijau\n" +
                    "1 sdm minyak zaitun ekstra virgin\n" +
                    "4 tomat anggur\n" +
                    "20 ml saus siram wijen sangrai",
            cookingSteps = "Cuci bersih selada\n" +
                    "tomat\n" +
                    "nanas.\n" +
                    "Rendam dlm air garam selama 15-20 menit.\n" +
                    "Bilas bahan tersebut dengan air bersih\n" +
                    "potong2 sesuai selera.\n" +
                    "Keringkan dengan pengering sayuran.\n" +
                    "Campur rata smua bahan salad dressing (kewpie saus siram wijen sangrai+minyak zaitun+sari kurma+bawang putih bubuk+cabe bubuk (optional)+perasan air jeruk nipis.\n" +
                    "Siram salad dressing ke salad yg telah disajikan\n" +
                    "aduk2\n" +
                    "siap utk disantap.",
            recipePictures = "salad_selada_nanas_tomat_cerry",
            mealType = 1
        ),
        Recipe(
            recipeId = 228,
            name = "Pancake Naga",
            calories = 334.0,
            carbs = 48.6,
            fat = 9.3,
            protein = 17.1,
            ingredients = "1 besar telur\n" +
                    "4 sendok makan instant oatmeal\n" +
                    "1 buah naga\n" +
                    "35 g susu",
            cookingSteps = "Blender semua bahan jadi satu.\n" +
                    "Panggang di atas teflon jadi beberapa bagian.\n" +
                    "Angkat dan kasih toping naga.",
            recipePictures = "pancake_naga",
            mealType = 1
        ),
        Recipe(
            recipeId = 229,
            name = "Bolu Pisang Oat Kukus",
            calories = 46.0,
            carbs = 8.2,
            fat = 1.1,
            protein = 1.5,
            ingredients = "1 sedang telur\n" +
                    "2 sedang pisang\n" +
                    "1 sdt baking soda\n" +
                    "6 sendok makan instant oatmeal\n" +
                    "5 g keju cheddar",
            cookingSteps = "Blender oat agak halus.\n" +
                    "Campur telor dan oat yang sudah d blender beserta baking powder.\n" +
                    "Haluskan pisang.\n" +
                    "Tuang pisang yang sudah halus ke adonan.\n" +
                    "Siapkan loyang dan Kukusan.\n" +
                    "Kukus kurang lebih 30menit.\n" +
                    "Sajikan.",
            recipePictures = "bolu_pisang_oat_kukus",
            mealType = 1
        ),
        Recipe(
            recipeId = 231,
            name = "Tumis Sawi Putih",
            calories = 263.0,
            carbs = 22.5,
            fat = 15.8,
            protein = 10.1,
            ingredients = "1 besar telur\n" +
                    "1 sdt cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "4 siung bawang putih\n" +
                    "200 gram sawi putih\n" +
                    "1 sdt gula pasir\n" +
                    "2 tomat ceri\n" +
                    "1 sdm minyak goreng\n" +
                    "1/4 sdm kecap manis\n" +
                    "2 g royco ayam\n" +
                    "35 gram bawang bombay",
            cookingSteps = "Potong Potong Sawi Putih Kemudian Cuci Bersih.\n" +
                    "Iris tipis2 bawang putih dan bawang bombay\n" +
                    "iris serong cabai merah\n" +
                    "iris tomat menjadi dua bagian.\n" +
                    "Panaskan minyak\n" +
                    "lalu tumis bawang putih dan bawang bombay. Setelah harum masukkan telur dan orak-arik\n" +
                    "tambahkan 250ml air\n" +
                    "setelah mendidih tambahkan kol\n" +
                    "cabai dan tomat\n" +
                    "aduk rata. Tambahkan gula\n" +
                    "garam\n" +
                    "kecap & royco.\n" +
                    "Koreksi rasa\n" +
                    "tunggu sampai matang.",
            recipePictures = "tumis_sawi_putih",
            mealType = 1
        ),
        Recipe(
            recipeId = 236,
            name = "Orange Smoothie",
            calories = 187.0,
            carbs = 47.9,
            fat = 0.8,
            protein = 2.4,
            ingredients = "1 kecil jeruk keprok\n" +
                    "1 sedang apel (tanpa kulit)\n" +
                    "100 gram nanas\n" +
                    "100 gram wortel\n" +
                    "1/2 gelas air",
            cookingSteps = "Kupas semua buah2an\n" +
                    "cuci bersih lalu potong2\n" +
                    "kecuali jeruk\n" +
                    "peras airnya.\n" +
                    "Siapkan blender\n" +
                    "masukan semua buah yg sudah di potong kecil2\n" +
                    "lalu air perasan jeruk\n" +
                    "tambahkan air dan es batu secukupnya.\n" +
                    "Blender semua buah kurleb 2-3menit sampai halus sempurna. Siap dinikmati.",
            recipePictures = "orange_smoothie",
            mealType = 1
        ),
        Recipe(
            recipeId = 240,
            name = "Cookies Oatmeal",
            calories = 35.0,
            carbs = 9.8,
            fat = 2.6,
            protein = 1.9,
            ingredients = "35 gram kenari\n" +
                    "1/2 sdt kayu manis\n" +
                    "135 gram pisang\n" +
                    "135 g oatmeal",
            cookingSteps = "Haluskan pisang.\n" +
                    "Masukkan semua bahan\n" +
                    "aduk sampai tercampur rata.\n" +
                    "Beratnya sekitar 24-25gr per porsi\n" +
                    "hasilnya 13 cookie. Bulatkan lalu ratakan.\n" +
                    "Panggang dengan suhu 175° selama 12-15 menit.",
            recipePictures = "cookies_oatmeal",
            mealType = 1
        ),
        Recipe(
            recipeId = 241,
            name = "Sayur Tumis Sawi Telur",
            calories = 130.0,
            carbs = 13.2,
            fat = 7.2,
            protein = 4.7,
            ingredients = "1 sedang telur\n" +
                    "2 sdt cabai merah atau rawit\n" +
                    "1 kecil wortel\n" +
                    "1 siung bawang putih\n" +
                    "1/2 utuh sedang tomat merah\n" +
                    "80 gram sawi putih\n" +
                    "2 kecil bawang merah\n" +
                    "1 sendok makan minyak goreng",
            cookingSteps = "Iris bawang\n" +
                    "cabai\n" +
                    "tomat\n" +
                    "sawi\n" +
                    "wortel.\n" +
                    "Panaskan minyak.\n" +
                    "Tumis bawang\n" +
                    "cabai.\n" +
                    "Masukkan wortel dan tomat.\n" +
                    "Tumis hingga empuk\n" +
                    "tambahkan bumbu sesuai selera.\n" +
                    "Masukkan sawi.\n" +
                    "Tambahkan sedikit air. Masak sampai matang.",
            recipePictures = "sayur_tumis_sawi_telur",
            mealType = 1
        ),
        Recipe(
            recipeId = 242,
            name = "Bubur Oat",
            calories = 413.0,
            carbs = 0.0,
            fat = 14.2,
            protein = 35.5,
            ingredients = "100 g dada ayam\n" +
                    "100 g bawang bombay dimasak\n" +
                    "100 g wortel\n" +
                    "100 g oatmeal\n" +
                    "100 g sawi hijau",
            cookingSteps = "Tumis bawang bombay sampai harum dengan minyak kelapa.\n" +
                    "Setelah harum\n" +
                    "masukan sedikit air.\n" +
                    "Masukkan ayam\n" +
                    "lalu sawi dn terakhir wortel.\n" +
                    "Setelah topping agak layu\n" +
                    "masukkan oat nya kurang lebih 3-5menit ( bila air diatas habis\n" +
                    "boleh ditambah untuk metebus oatnya. Sedikit saja ya).\n" +
                    "Tambahkan sedikit garam. Koreksi rasa.",
            recipePictures = "bubur_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 249,
            name = "Yellow Smoothies",
            calories = 80.0,
            carbs = 0.0,
            fat = 0.3,
            protein = 0.7,
            ingredients = "100 gram mangga\n" +
                    "150 gram buah pir\n" +
                    "50 gram mentimun (dengan kulit)\n" +
                    "130 gram air",
            cookingSteps = "Masukkan semua bahan ke dalam blender.\n" +
                    "Tambahkan air lalu Blender semua bahan sampai halus.\n" +
                    "Siap disajikan.",
            recipePictures = "yellow_smoothies",
            mealType = 1
        ),
        Recipe(
            recipeId = 251,
            name = "Telur Kukus",
            calories = 230.0,
            carbs = 5.6,
            fat = 12.5,
            protein = 22.6,
            ingredients = "63 gram daging sapi\n" +
                    "1 1/2 besar putih telur\n" +
                    "12 gram buncis\n" +
                    "29 gram wortel\n" +
                    "11 gram daun bawang",
            cookingSteps = "Iris cincang wortel\n" +
                    "daun bawang\n" +
                    "serta buncis. Sisihkan.\n" +
                    "Kocok putih telur dengan pengocok telur hingga berbusa.\n" +
                    "Masukan wortel\n" +
                    "daun bawang\n" +
                    "buncis yang telah diiris cincang serta daging sapi yang dipotong kecil-kecil ke dalam kocokan putih telur. Tambahkan garam\n" +
                    "merica\n" +
                    "dan penyedap. Aduk hingga rata.\n" +
                    "Masukan adonan ke dalam wadah anti panas\n" +
                    "lalu kukus selama 30 menit.\n" +
                    "Setelah 30 menit angkat lalu tiriskan.",
            recipePictures = "telur_kukus",
            mealType = 1
        ),
        Recipe(
            recipeId = 252,
            name = "Bubur Oatmeal Asin",
            calories = 223.0,
            carbs = 27.5,
            fat = 8.0,
            protein = 12.3,
            ingredients = "1 irisan tipis ayam\n" +
                    "1/2 besar telur rebus\n" +
                    "1/4 mangkok jamur dimasak (lemak tidak ditambahkan dalam masakan)\n" +
                    "1/2 sdt garam\n" +
                    "1/2 sdm seledri\n" +
                    "1 sdm\n" +
                    "cincang daun bawang (loncang)\n" +
                    "1/4 sosis ayam\n" +
                    "0\n" +
                    "5 g royco ayam\n" +
                    "35 g instant oatmeal grain\n" +
                    "0\n" +
                    "75 g lada putih bubuk",
            cookingSteps = "Nyalakan kompor.\n" +
                    "Masukkan oatmeal ke teflon dan tambahkan air panas\n" +
                    "aduk jangan sampai terlalu encer.\n" +
                    "Tambahkan garam\n" +
                    "lada\n" +
                    "daun bawang\n" +
                    "dan penyedap rasa\n" +
                    "aduk.\n" +
                    "Hias bubur dengan topping.\n" +
                    "Koreksi rasa\n" +
                    "jika sudah pas\n" +
                    "tuang ke mangkuk.\n" +
                    "Tambahkan air lagi sedikit demi sedikit sambil diaduk sampai tekstur seperti bubur.\n" +
                    "Selamat menikmati.",
            recipePictures = "bubur_oatmeal_asin",
            mealType = 1
        ),
        Recipe(
            recipeId = 258,
            name = "Mini Pancakes",
            calories = 353.0,
            carbs = 62.8,
            fat = 8.0,
            protein = 10.9,
            ingredients = "1 sedang telur\n" +
                    "1/2 buah kiwi\n" +
                    "120 gram pisang\n" +
                    "1 gram biji wijen kering utuh\n" +
                    "1/2 sdm madu\n" +
                    "30 g oats",
            cookingSteps = "Blender oat\n" +
                    "pisang\n" +
                    "telur\n" +
                    "bp sampai halus.\n" +
                    "Panggang adonan sedikit-sedikit (pakai sendok teh) pada teflon anti lengket tanpa menambahkan minyak/margarin. Gunakan api kecil.\n" +
                    "Balik adonan saat timbul gelembung pada pancake.\n" +
                    "Sajikan dengan toping madu\n" +
                    "wijen\n" +
                    "dan buah.",
            recipePictures = "mini_pancakes",
            mealType = 1
        ),
        Recipe(
            recipeId = 266,
            name = "Manggo Sticky Oats",
            calories = 262.0,
            carbs = 48.6,
            fat = 8.3,
            protein = 2.8,
            ingredients = "95 gram mango\n" +
                    "1/2 tsp sugar\n" +
                    "2 sejumput salt\n" +
                    "50 ml water\n" +
                    "100 ml water\n" +
                    "6 sdt fiber creme\n" +
                    "6 g tepung pati jagung\n" +
                    "20 g quick cook oatmeal",
            cookingSteps = "Kupas mangga terlebih dahulu lalu tata kedalam piring.\n" +
                    "Selanjutnya masak oats dengan 200ml air\n" +
                    "tambahkan garam secukupnya lalu masak hingga air menyusut. Taruh kedalam piring.\n" +
                    "Setelah itu buat saus dengan memanaskan 50ml air\n" +
                    "tambahkan 6 sdt fibercrem lalu aduk hingga merata.\n" +
                    "Tambahkan sedikit garam dan 1/2 sdt gula\n" +
                    "aduk kembali lalu tambahkan 1/2 sdm tepung maizena.\n" +
                    "Aduk hingha rata dan mengental.\n" +
                    "Siramkan diatas mangga dan oats yang telah di tata.\n" +
                    "Selesai.",
            recipePictures = "manggo_sticky_oats",
            mealType = 1
        ),
        Recipe(
            recipeId = 273,
            name = "Nasi Goreng Oatmeal",
            calories = 336.0,
            carbs = 53.1,
            fat = 12.3,
            protein = 7.7,
            ingredients = "1 sdm margarin\n" +
                    "1/2 sdt garam\n" +
                    "50 gram buncis\n" +
                    "50 gram wortel\n" +
                    "1/2 daun bawang\n" +
                    "100 ml air\n" +
                    "1 1/2 sdt bawang putih cincang\n" +
                    "4 sendok makan instant oatmeal\n" +
                    "1/2 takaran lada putih bubuk\n" +
                    "1 sdm kecap manis",
            cookingSteps = "Cincang bawang putih dan potong sayuran yang telah dicuci.\n" +
                    "Panaskan teflon lalu masukan margarin hingga mencair.\n" +
                    "Tumis baput cincang hingga harum.\n" +
                    "Masukan air\n" +
                    "disusul dengan sayuran.\n" +
                    "Tumis sayuran hingga setengah matang lalu masukan daun bawang dan oatmeal.\n" +
                    "Terakhir masukan bumbu seperti kecap\n" +
                    "garam\n" +
                    "dan lada.",
            recipePictures = "nasi_goreng_oatmeal",
            mealType = 1
        ),
        Recipe(
            recipeId = 277,
            name = "Ayam Crispy Oatmil",
            calories = 284.0,
            carbs = 14.8,
            fat = 16.1,
            protein = 20.5,
            ingredients = "100 gram dada ayam\n" +
                    "1 besar telur\n" +
                    "1 sdm bubuk cabai\n" +
                    "1 sdt\n" +
                    "tumbuk oregano\n" +
                    "1 sdt garam\n" +
                    "1 sdm olive oil\n" +
                    "2 sdm oat",
            cookingSteps = "Slice dada ayam ketebalan sesuai selera lalu bumbui dada ayam dgn garam dan bubuk cabai (optional).\n" +
                    "Kocok telur.\n" +
                    "Tuang quacker oat ke dalam mangkuk lalu bumbui lg dgn bubuk cabai\n" +
                    "garlic powder\n" +
                    "dan oregano.\n" +
                    "Tuang sedikit olive oil ke teflon klo bisa (anti lengket) yg sudah dipanaskan.\n" +
                    "Lumuri dada ayam dengan telur lalu balurkan ke oat yg sudah dibumbui. Panggang di dalam teflon dgn api sedang.\n" +
                    "Angkat ayam jika sudah kecoklatan.",
            recipePictures = "ayam_crispy_oatmil",
            mealType = 1
        ),
        Recipe(
            recipeId = 279,
            name = "Roti Tawar",
            calories = 134.0,
            carbs = 21.2,
            fat = 3.3,
            protein = 4.2,
            ingredients = "1 besar telur\n" +
                    "3 1/2 gram ragi\n" +
                    "20 gram margarin\n" +
                    "1 sejumput garam\n" +
                    "120 ml air\n" +
                    "20 gram gula bubuk\n" +
                    "180 gram tepung terigu putih (semua keperluan)\n" +
                    "30 g susu bubuk full cream",
            cookingSteps = "Campurkan semua bahan kecuali margarin dan garam.\n" +
                    "Setelah bahan tercampur\n" +
                    "masukan margarin dan aduk hingga semua bahan tercampur dan adonan menjadi kalis.\n" +
                    "Istirahatkan adonan selama 1 jam\n" +
                    "biarkan hingga adonan mengembang 2 kali lipat dari ukuran semula.\n" +
                    "Panggan adonan menggunakan oven selama 30 menit dengan suhu 180°C.\n" +
                    "Setelah roti matang\n" +
                    "oleskan margarin pada roti selagi hangat.\n" +
                    "Roti siap disajikan.",
            recipePictures = "roti_tawar",
            mealType = 1
        ),
        Recipe(
            recipeId = 280,
            name = "Sup Sehat",
            calories = 56.0,
            carbs = 0.0,
            fat = 2.9,
            protein = 4.5,
            ingredients = "20 telur puyuh\n" +
                    "100 gram buncis\n" +
                    "50 gram brokoli\n" +
                    "100 gram wortel\n" +
                    "5 siung bawang putih\n" +
                    "5 gram daun bawang\n" +
                    "30 gram jamur putih (ditumis)\n" +
                    "1 g lada putih bubuk\n" +
                    "3 sosis ayam\n" +
                    "50 gram kaldu jamur",
            cookingSteps = "Potong2 semua bahan sesuai selera.\n" +
                    "Rebus telur puyuh dan kupas.\n" +
                    "Rebus 750mL air.\n" +
                    "Geprek bawang putih dan masukkan k air yang telah mendidih.\n" +
                    "Masukkan wortel\n" +
                    "buncis\n" +
                    "brokoli ke dalam air.\n" +
                    "Biarkan 1/2 lunak dan masukkan sosis\n" +
                    "jamur\n" +
                    "telur puyuh.\n" +
                    "Bumbui dengan kaldu jamur dan lada putih. Koreksi rasa (bisa d tambahkan gula\n" +
                    "garam\n" +
                    "dll sesuai keinginan. Kalori perlu d tambahkn untuk modifikasi).\n" +
                    "Tambahkan irisan bawang daun (saya pakai yang putih) dan siap d hidangkan.",
            recipePictures = "sup sehat",
            mealType = 1
        ),
        Recipe(
            recipeId = 282,
            name = "Salad Ayam",
            calories = 376.0,
            carbs = 41.9,
            fat = 14.0,
            protein = 22.5,
            ingredients = "60 gram chicken breast\n" +
                    "2 mangkok\n" +
                    "parut atau cincang lettuce\n" +
                    "90 gram cooked corn\n" +
                    "1/2 sendok makan light mayonnaise\n" +
                    "50 gram boiled potato",
            cookingSteps = "Bumbui ayam dengan garam merica\n" +
                    "panggang.\n" +
                    "Kukus jagung\n" +
                    "kentang.\n" +
                    "Potong n cuci lettuce.\n" +
                    "Campur smuanya.\n" +
                    "Pakein mayo / salad dressing.",
            recipePictures = "salad_ayam",
            mealType = 1
        ),
        Recipe(
            recipeId = 283,
            name = "Bolu Cup",
            calories = 59.0,
            carbs = 9.9,
            fat = 1.3,
            protein = 1.9,
            ingredients = "220 gram telur\n" +
                    "120 gram gula pasir\n" +
                    "150 gram tepung terigu putih (semua keperluan)\n" +
                    "50 g keju serbaguna",
            cookingSteps = "Kocok telur dan gula hingga mengembang.\n" +
                    "Tambahkan tepung sedikit demi sedikit.\n" +
                    "Masukkan ke cup.\n" +
                    "Taburin keju.\n" +
                    "Panggang dg suhu 200 C selama 30 menit.",
            recipePictures = "bolu_cup",
            mealType = 1
        ),
        Recipe(
            recipeId = 285,
            name = "Kue Muesli",
            calories = 78.0,
            carbs = 13.8,
            fat = 1.8,
            protein = 2.1,
            ingredients = "1 sedang telur\n" +
                    "2 sdm madu\n" +
                    "100 g tropical muesli",
            cookingSteps = "Campur semua bahan.\n" +
                    "Bentuk kue dan sebarkan di atas loyang.\n" +
                    "Panggang dalam oven selama 20 menit dengan suhu 170°C.",
            recipePictures = "kue_muesli",
            mealType = 1
        ),
        Recipe(
            recipeId = 286,
            name = "Sup Baso Ikan",
            calories = 70.0,
            carbs = 7.5,
            fat = 1.3,
            protein = 6.9,
            ingredients = "7 1/2 gram kaldu jamur\n" +
                    "1 sdt garam\n" +
                    "1 gram minyak wijen\n" +
                    "2 gram daun bawang\n" +
                    "20 gram sawi putih\n" +
                    "1 gelas air\n" +
                    "117 gram bakso ikan\n" +
                    "20 gram sawi hijau",
            cookingSteps = "Siapkan bahan\n" +
                    "masak air sampai mendidih.\n" +
                    "Tambhan minyak wijen\n" +
                    "kaldu dan garam\n" +
                    "cicipi rasa.\n" +
                    "Masukan baso\n" +
                    "tekakhir masukan sayuran\n" +
                    "siapkan wadah",
            recipePictures = "sup_baso_ikan",
            mealType = 1
        ),
        Recipe(
            recipeId = 291,
            name = "Oseng Tahu",
            calories = 231.0,
            carbs = 8.8,
            fat = 18.4,
            protein = 10.8,
            ingredients = "43 gram bawang bombay dimasak\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdm minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "51 gram kubis\n" +
                    "9 gram daun bawang\n" +
                    "1 sejumput garam laut\n" +
                    "250 gram tahu\n" +
                    "1 sdm minyak jagung",
            cookingSteps = "Iris bawang bombay\n" +
                    "daun bawang\n" +
                    "kubis sesuai selera.\n" +
                    "Siapkan wajan anti lengket\n" +
                    "beri corn oil lalu masukkan daun bawang tumis hingga harum.\n" +
                    "Setelah itu masukkan bombay\n" +
                    "tumis hingga harum.\n" +
                    "Kecilkan api\n" +
                    "masukkan tahu lalu lancurkan. Oseng hingga air tahu mengering.\n" +
                    "Tambahkan minyak wijen\n" +
                    "saus tiram\n" +
                    "lada\n" +
                    "garam oseng hingga tercampur rata selama 75 detik.",
            recipePictures = "oseng_tahu",
            mealType = 1
        ),
        Recipe(
            recipeId = 293,
            name = "Nasi Goreng",
            calories = 206.0,
            carbs = 31.3,
            fat = 7.5,
            protein = 4.1,
            ingredients = "92 gram kecambah\n" +
                    "1 sdm minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "62 gram kubis\n" +
                    "100 gram wortel\n" +
                    "60 gram nasi merah (butir-sedang\n" +
                    "dimasak)\n" +
                    "60 gram nasi putih (butir-sedang\n" +
                    "dimasak)\n" +
                    "2 sdm saus sambal",
            cookingSteps = "Tulis wortel selama 1 menit\n" +
                    "lalu masukkan gubis tumis kembali.\n" +
                    "Masukkan nasi\n" +
                    "tumis hingga rata lalu masukkan kecambah.\n" +
                    "Tambahkan saus sambal\n" +
                    "saus tiram\n" +
                    "minyak wijen.",
            recipePictures = "nasi_goreng",
            mealType = 1
        ),
        Recipe(
            recipeId = 301,
            name = "Oat Pisang",
            calories = 162.0,
            carbs = 33.0,
            fat = 3.9,
            protein = 2.3,
            ingredients = "2 sedang pisang\n" +
                    "8 g mentega\n" +
                    "100 g oatmeal",
            cookingSteps = "Hancurkan pisang.\n" +
                    "Tambahkan oatmeal dan mentega cair dan aduk rata.\n" +
                    "Bentuk kue dan letakkan di atas loyang. Panggang selama 20 menit pada suhu 180°C.",
            recipePictures = "oat_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 302,
            name = "Brownies Oat",
            calories = 158.0,
            carbs = 18.5,
            fat = 8.7,
            protein = 2.2,
            ingredients = "2 sedang telur\n" +
                    "20 gram cocoa powder (unsweetened)\n" +
                    "130 gram brown sugar\n" +
                    "100 g instant oatmeal\n" +
                    "63 ml minyak kanola\n" +
                    "20 g fiber creme\n" +
                    "10 g tepung pati jagung\n" +
                    "104 ml dark compound chocolate",
            cookingSteps = "Lelehkan dark chocolate dengan minyak. Menyisihkan.\n" +
                    "Campur bahan yang tersisa.\n" +
                    "Terakhir\n" +
                    "tambahkan campuran cokelat leleh. Campur dengan baik.\n" +
                    "Tempatkan adonan ke dalam cetakan dan hiasi sesuai keinginan. Panggang selama 20 menit pada suhu 180°C.",
            recipePictures = "brownies_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 303,
            name = "Muffins Pisang",
            calories = 113.0,
            carbs = 19.5,
            fat = 0.9,
            protein = 7.9,
            ingredients = "1 besar putih telur\n" +
                    "2 gram kayu manis\n" +
                    "1 sdt ekstrak vanila\n" +
                    "1 sedang pisang\n" +
                    "1 gram baking soda\n" +
                    "1 sdm madu\n" +
                    "1 bungkus stevia sweetener\n" +
                    "1 scoop impact whey isolate\n" +
                    "1/2 gelas kering rolled oats",
            cookingSteps = "Pisahkan telur dan kocok putih telurnya.\n" +
                    "Hancurkan pisang lalu tambahkan putih telur\n" +
                    "vanila\n" +
                    "dan madu. Campur dengan baik.\n" +
                    "Aduk sisa bahan dan aduk rata.\n" +
                    "Bagi menjadi cangkir muffin dan hiasi sesuai keinginan. Kukus selama 20 menit.",
            recipePictures = "muffins_pisang",
            mealType = 1
        ),
        Recipe(
            recipeId = 304,
            name = "Kue Chiffon",
            calories = 181.0,
            carbs = 17.3,
            fat = 9.9,
            protein = 5.7,
            ingredients = "80 gram keju cheddar\n" +
                    "6 besar putih telur\n" +
                    "6 besar kuning telur\n" +
                    "70 ml air\n" +
                    "125 gram gula pasir\n" +
                    "100 gram tepung terigu putih (semua keperluan)\n" +
                    "70 ml minyak goreng",
            cookingSteps = "Kocok putih telur dengan 100 gr gula hingga kaku.\n" +
                    "Di wadah lain\n" +
                    "kocok kuning telur dengan 25 gr gula hingga berbusa. Kemudian tambahkan air dan minyak\n" +
                    "aduk rata. Terakhir\n" +
                    "tambahkan tepung dan aduk.\n" +
                    "Campurkan 2 campuran dan tempatkan dalam loyang kue. Hiasi keju cheddar di atasnya.\n" +
                    "Panggang dalam oven dengan suhu 160°C selama 50-55 menit.\n" +
                    "Tunggu dingin baru keluarkan dari loyang.",
            recipePictures = "kue_chiffon",
            mealType = 1
        ),
        Recipe(
            recipeId = 307,
            name = "Banana Oatmeal Pancake",
            calories = 120.0,
            carbs = 18.8,
            fat = 4.1,
            protein = 3.2,
            ingredients = "1 besar putih telur\n" +
                    "2 sdt minyak zaitun\n" +
                    "1 sedang pisang\n" +
                    "50 ml air\n" +
                    "70 g oatmeal",
            cookingSteps = "Masukan pisang\n" +
                    "oatmeal\n" +
                    "dan air ke dalam blender.\n" +
                    "Blender hingga halus\n" +
                    "kemudian pindah ke dalam mangkuk dan masukan putih telur kemudian di kocok dengan menggunakan sendok hingga tercampur rata.\n" +
                    "Masak dengan menggunakan teflon dan beri minyak zaitun\n" +
                    "masak hingga matang.",
            recipePictures = "banana_oatmeal_pancake",
            mealType = 1
        ),
        Recipe(
            recipeId = 308,
            name = "Pancake Oat",
            calories = 203.0,
            carbs = 28.8,
            fat = 1.1,
            protein = 14.7,
            ingredients = "20 g susu rendah lemak\n" +
                    "100 g putih telur\n" +
                    "35 g gandum",
            cookingSteps = "Panaskan pan dengan api sedang\n" +
                    "jika dikira sudah panas kecilkan api.\n" +
                    "Campur rata semua bahan-bahan\n" +
                    "jika mau tambahkan sweetener untuk pengganti gula jika ingin manis jika tidak juga gapapa.\n" +
                    "Jika sudah tercampur rata semua bahan\n" +
                    "tuang adonan ke pan yang sudah dipanaskan ingat dengan api kecil\n" +
                    "tunggu sampai matang. Pancake oat siap di hidangkan. Bisa di tambahkan topping tapi yang sehat.",
            recipePictures = "pancake_oat",
            mealType = 1
        ),
        Recipe(
            recipeId = 310,
            name = "Tumis Oatmeal Dengan Sayur",
            calories = 273.0,
            carbs = 59.9,
            fat = 0.5,
            protein = 7.1,
            ingredients = "1 sejumput lada\n" +
                    "1 sdt garam\n" +
                    "1 mangkok\n" +
                    "dicincang wortel\n" +
                    "15 gram bawang daun\n" +
                    "1 mangkok bayam\n" +
                    "14 g bawang merah\n" +
                    "3 g bawang putih\n" +
                    "53 g oatmeal",
            cookingSteps = "Sangrai oatmeal\n" +
                    "kurang lebih 5 menit\n" +
                    "sampai tercium bau dan tekstur nya lebih garing. Sisihkan.\n" +
                    "Tumis Bawang merah\n" +
                    "bawang putih\n" +
                    "bawang daun sampai harum\n" +
                    "masukkan garam dan lada putih\n" +
                    "tambah air 1/4 cangkir. masak kurang lebih 5 menit.\n" +
                    "Pada saat sayur matang\n" +
                    "masukkan oatmeal yang sudah disangrai tadi. Kalau kental masukkan air secukupnya sampai air terserap semuanya. Hidangkan.",
            recipePictures = "tumis_oatmeal_dengan_sayur",
            mealType = 1
        ),
        Recipe(
            recipeId = 311,
            name = "Sup Labu Energi",
            calories = 249.0,
            carbs = 52.3,
            fat = 0.8,
            protein = 14.3,
            ingredients = "1 gelas susu tanpa lemak\n" +
                    "500 gram labu kuning\n" +
                    "10 gram tepung",
            cookingSteps = "Potong labu hingga berbentuk bulan sabit seperti semangka potong\n" +
                    "kulit dan biji jangan dulu dikupas. Kukus hingga setengah matang +/- 10 menit. Tujuan agar labu cukup empuk untuk diblender.\n" +
                    "Keluarkan labu dari kukusan biarkan sebentar agar dingin. Lalu kupas kulit dan buang bijinya. Potong dadu dan blender (bisa ditambah air secukupnya).\n" +
                    "Labu yang sudah diblender\n" +
                    "lalu tuang ke panci. Lalu masak dengan api kecil-sedang. Perlahan tambahkan susu (kekentalan tergantung selera). Terus tambahkan 1 sdm tepung maizena untuk mengentalkan sup. Penyajian bisa dimakan selagi hangat atau dingin (simpan di kulkas dulu).",
            recipePictures = "sup_labu_energi",
            mealType = 1
        ),
        Recipe(
            recipeId = 313,
            name = "Sup Pembakar Lemak",
            calories = 48.0,
            carbs = 9.7,
            fat = 0.8,
            protein = 2.3,
            ingredients = "2 mangkok kembang kol\n" +
                    "1 sedang paprika\n" +
                    "3 utuh sedang tomat\n" +
                    "2 ons bawang bombay",
            cookingSteps = "Potong semua bahan.\n" +
                    "Rebus air sampai mendidih.\n" +
                    "Masukan dan tunggu 5 - 10 menit atau sampai masak.",
            recipePictures = "sup_pembakar_lemak",
            mealType = 1
        ),
        Recipe(
            recipeId = 7,
            name = "Mie Shirataki Goreng",
            calories = 175.0,
            carbs = 19.0,
            fat = 5.9,
            protein = 9.1,
            ingredients = "1 sejumput salt\n" +
                    "1 buah utuh portabella mushrooms\n" +
                    "30 gram cherry tomatoes\n" +
                    "3 ml minyak goreng\n" +
                    "1 sdm kecap manis\n" +
                    "75 g mie kering shirataki\n" +
                    "8 g sambal bawang\n" +
                    "45 g dada ayam\n" +
                    "65 g telur omega",
            cookingSteps = "Panaskan wajan dengan minyak dan tumis irisan dada ayam dan jamur.\n" +
                    "Tambahkan telur orak-arik sebelumnya\n" +
                    "irisan tomat\n" +
                    "dan bahan lainnya\n" +
                    "masak hingga matang.\n" +
                    "Pindahkan ke piring dan sajikan. Menikmati.",
            recipePictures = "mie_shirataki_goreng",
            mealType = 2
        ),
        Recipe(
            recipeId = 17,
            name = "Ayam dengan Jagung dan Kacang",
            calories = 200.0,
            carbs = 18.3,
            fat = 4.5,
            protein = 24.9,
            ingredients = "8 gram cabai merah atau rawit\n" +
                    "69 gram daging dada ayam (ayam pedaging\n" +
                    "dipanggang\n" +
                    "dimasak)\n" +
                    "27 gram buncis\n" +
                    "50 gram jagung manis kuning\n" +
                    "7 gram bawang putih",
            cookingSteps = "Panaskan tempel dalam wajan antilengket lalu tambahkan ayam. Tambahkan air sesuai kebutuhan.\n" +
                    "Masukkan jagung dan buncis. Didihkan selama 5 menit.\n" +
                    "Sajikan di atas piring.",
            recipePictures = "ayam_dengan_jagung_dan_kacang",
            mealType = 2
        ),
        Recipe(
            recipeId = 18,
            name = "Sup Sosis dengan Sayuran",
            calories = 256.0,
            carbs = 21.1,
            fat = 11.3,
            protein = 19.0,
            ingredients = "1 besar telur\n" +
                    "100 gram buncis\n" +
                    "100 gram wortel\n" +
                    "65 g sosis sapi & ayam",
            cookingSteps = "Rebus air secukupnya lalu masukkan irisan sayuran.\n" +
                    "Masukkan irisan sosis dan telur kocok.\n" +
                    "Bumbui sesuai keinginan dan masak selama 5 menit.",
            recipePictures = "sup_sosis_dengan_sayuran",
            mealType = 2
        ),
        Recipe(
            recipeId = 20,
            name = "Sop Tulang Iga Sapi",
            calories = 156.0,
            carbs = 6.8,
            fat = 9.8,
            protein = 10.6,
            ingredients = "100 gram tomat\n" +
                    "100 gram wortel\n" +
                    "100 gram iga sapi (potongan kecil\n" +
                    "diiris hingga 0.3 cm lemak)",
            cookingSteps = "Rebus daging sapi dengan air secukupnya selama 30 menit.\n" +
                    "Tambahkan irisan sayuran dan isi air sesuai kebutuhan.\n" +
                    "Bumbui sesuai selera dan masak hingga empuk.\n" +
                    "Sajikan dalam mangkuk dan hiasi sesuai keinginan.",
            recipePictures = "sop_tulang_iga_sapi",
            mealType = 2
        ),
        Recipe(
            recipeId = 21,
            name = "Ikan Tongkol Asam Pedas",
            calories = 100.0,
            carbs = 18.4,
            fat = 0.6,
            protein = 7.0,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "100 gram asam jawa\n" +
                    "1 siung bawang putih\n" +
                    "1 kecil bawang merah\n" +
                    "100 gram ikan tongkol",
            cookingSteps = "Rebus tuna dalam air hingga empuk. Tiriskan dan potong.\n" +
                    "Dalam wajan yang sudah diolesi sedikit minyak\n" +
                    "tumis bahan lainnya hingga harum.\n" +
                    "Tambahkan tuna dan aduk.\n" +
                    "Didihkan selama 5 menit.",
            recipePictures = "ikan_tongkol_asam_pedas",
            mealType = 2
        ),
        Recipe(
            recipeId = 23,
            name = "Rebusan Tahu dengan Kecambah",
            calories = 61.0,
            carbs = 3.6,
            fat = 3.8,
            protein = 4.4,
            ingredients = "120 gram kecambah\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "1/2 utuh sedang tomat merah\n" +
                    "500 ml air\n" +
                    "157 gram tahu\n" +
                    "7 ml minyak goreng\n" +
                    "7 g saos tiram",
            cookingSteps = "Potong tahu dan sayuran.\n" +
                    "Panaskan minyak dalam wajan dan tumis bawang putih sampai keemasan.\n" +
                    "Tambahkan air dan biarkan mendidih.\n" +
                    "Masukkan sisa bahan dan bumbui. Masak sampai empuk.",
            recipePictures = "rebusan_tahu_dengan_kecambah",
            mealType = 2
        ),
        Recipe(
            recipeId = 29,
            name = "Mangkuk Sushi Salmon",
            calories = 792.0,
            carbs = 7.6,
            fat = 67.4,
            protein = 22.6,
            ingredients = "87 gram ikan salmon panggang\n" +
                    "5 ml kecap\n" +
                    "2 g nori\n" +
                    "180 g wijen panggang mayones\n" +
                    "40 g nasi porang",
            cookingSteps = "Suwir salmon yang sudah matang menggunakan garpu.\n" +
                    "Masukkan nasi ke dalam mangkuk.\n" +
                    "Tambahkan semua bahan dan hiasi dengan nori.",
            recipePictures = "mangkuk_sushi_salmon",
            mealType = 2
        ),
        Recipe(
            recipeId = 32,
            name = "Nasi Pizza dengan Ayam dan Sayuran",
            calories = 234.0,
            carbs = 19.4,
            fat = 11.5,
            protein = 13.3,
            ingredients = "100 gram dada ayam\n" +
                    "1 kecil telur\n" +
                    "200 gram nasi putih\n" +
                    "10 gram wortel dimasak\n" +
                    "10 gram buncis\n" +
                    "2 sdm mentega\n" +
                    "3 g kaldu jamur",
            cookingSteps = "Potong dadu dada ayam dan wortel.\n" +
                    "Rebus dalam air bersama dengan buncis selama 10 menit.\n" +
                    "Giling semuanya dalam blender (kecuali mentega).\n" +
                    "Panaskan wajan dengan mentega dan masak campuran sampai berwarna keemasan.",
            recipePictures = "nasi_pizza_dengan_ayam_dan_sayuran",
            mealType = 2
        ),
        Recipe(
            recipeId = 33,
            name = "Kari Tuna",
            calories = 76.0,
            carbs = 2.2,
            fat = 1.4,
            protein = 13.6,
            ingredients = "400 gram ikan tuna\n" +
                    "6 gram cabai merah atau rawit\n" +
                    "2 gram kunyit\n" +
                    "3 g stevia sweetener\n" +
                    "10 g santan bubuk\n" +
                    "9 g bumbu gulai",
            cookingSteps = "Campur bubuk dan larutkan dengan air secukupnya.\n" +
                    "Masukkan ke dalam panci dan didihkan sambil diaduk.\n" +
                    "Tambahkan tuna dan merica. Masak hingga empuk.",
            recipePictures = "kari_tuna",
            mealType = 2
        ),
        Recipe(
            recipeId = 34,
            name = "Toge Telur",
            calories = 252.0,
            carbs = 26.7,
            fat = 6.8,
            protein = 21.9,
            ingredients = "1 besar egg\n" +
                    "150 gram sprouts\n" +
                    "100 ml water\n" +
                    "50 g bakso daging sapi\n" +
                    "2 sdm saos tiram\n" +
                    "1 bungkus level 30\n" +
                    "1 sdm kecap manis sedaap",
            cookingSteps = "Rebus air\n" +
                    "masukkan toge.\n" +
                    "Potong potong bakso\n" +
                    "kocok telur lalu masukkan ke rebusan air.\n" +
                    "Tambah kecap\n" +
                    "saori dan sedikit bubuk cabai. Rebus hingga matang.",
            recipePictures = "toge_telur",
            mealType = 2
        ),
        Recipe(
            recipeId = 38,
            name = "Ayam Rica Rica",
            calories = 219.0,
            carbs = 5.9,
            fat = 14.4,
            protein = 17.8,
            ingredients = "306 gram ayam\n" +
                    "35 gram cabai merah atau rawit\n" +
                    "15 gram kemangi\n" +
                    "10 gram bawang putih\n" +
                    "3 gram jahe\n" +
                    "9 gram kacang kemiri\n" +
                    "1/2 sdt gula pasir\n" +
                    "1 sdt garam laut\n" +
                    "15 gram bawang merah\n" +
                    "2 g royco ayam\n" +
                    "2 sendok makan minyak goreng sawit\n" +
                    "5 gram lengkuas",
            cookingSteps = "Cuci bersih ayam lumuri garam dan royco diamkan 30 menit lalu kukus hingga matang.\n" +
                    "Haluskan cabai\n" +
                    "bawang merah\n" +
                    "bawang putih\n" +
                    "jahe\n" +
                    "kemiri\n" +
                    "dan lengkuas.\n" +
                    "Panaskan minyak\n" +
                    "tumis bumbu halus masukan gula pasir\n" +
                    "tumis hingga harum tambahkan sedikit air\n" +
                    "masukan ayam masak hingga air susut.\n" +
                    "Terakhir masukan daun kemangi\n" +
                    "masak hingga matang angkat sajikan.",
            recipePictures = "ayam_rica_rica",
            mealType = 2
        ),
        Recipe(
            recipeId = 40,
            name = "Sawi Isi Ayam Udang",
            calories = 131.0,
            carbs = 9.8,
            fat = 4.9,
            protein = 12.5,
            ingredients = "50 gram dada ayam\n" +
                    "5 sedang udang\n" +
                    "40 gram sawi dimasak (dari segar)\n" +
                    "1 sdt garam\n" +
                    "1 sdt minyak wijen\n" +
                    "10 gram wortel\n" +
                    "1 sdt gula pasir\n" +
                    "1.5 g lada putih bubuk\n" +
                    "15 g saus bumbu rasa tiram\n" +
                    "30 ml kecap asin\n" +
                    "7 g sajiku tepung bumbu serbaguna",
            cookingSteps = "Kukus sawi hingga lunak\n" +
                    "diamkan.\n" +
                    "Giling semua bahan (selain sawi) menggunakan chopper.\n" +
                    "Lebarkan lembaran sawi\n" +
                    "beri adonan hasil gilingan\n" +
                    "gulung.\n" +
                    "Kukus selama 20 menit.",
            recipePictures = "sawi_isi_ayam_udang",
            mealType = 2
        ),
        Recipe(
            recipeId = 43,
            name = "Tumis Buncis Tempe",
            calories = 111.0,
            carbs = 9.9,
            fat = 4.9,
            protein = 9.5,
            ingredients = "5 gram kaldu jamur\n" +
                    "225 gram buncis\n" +
                    "18 gram bawang putih\n" +
                    "200 ml air\n" +
                    "223 gram tempe\n" +
                    "39 gram bawang bombay\n" +
                    "10 g saus tiram",
            cookingSteps = "Potong-potong buncis dan tempe. Cincang bawang putih dan bawang bombay.\n" +
                    "Didihkan air\n" +
                    "tambahkan bawang bombay\n" +
                    "bawang putih\n" +
                    "saus tiram dan kaldu jamur.\n" +
                    "Masukkan tempe dan buncis. Masak sampai buncis empuk.\n" +
                    "Siap disajikan.",
            recipePictures = "tumis_buncis_tempe",
            mealType = 2
        ),
        Recipe(
            recipeId = 47,
            name = "Sandwich",
            calories = 467.0,
            carbs = 38.1,
            fat = 23.8,
            protein = 20.7,
            ingredients = "1 daun sedang selada\n" +
                    "100 gram telur ceplok\n" +
                    "2 lembar roti tawar gandum\n" +
                    "1 sachet saus sambal\n" +
                    "1 slice keju slice",
            cookingSteps = "Siap kan bahan.\n" +
                    "Panggang roti di pan tanpa minyak hingga mencoklat rata.\n" +
                    "Ceplok telur\n" +
                    "tambahkan garam sejumput\n" +
                    "kocok telur dan goreng.\n" +
                    "Susun roti dengan bahan bahan yang sudah disiapkan.",
            recipePictures = "sandwich",
            mealType = 2
        ),
        Recipe(
            recipeId = 48,
            name = "Tumis Tempe Labu Kacang Panjang",
            calories = 90.0,
            carbs = 7.7,
            fat = 4.0,
            protein = 7.7,
            ingredients = "216 gram kacang panjang hijau\n" +
                    "5 gram kaldu jamur\n" +
                    "2 gram garam\n" +
                    "350 gram labu siam\n" +
                    "21 gram bawang putih\n" +
                    "200 ml air\n" +
                    "366 gram tempe\n" +
                    "1 g lada putih bubuk\n" +
                    "33 gram bawang bombay\n" +
                    "11 g saus tiram",
            cookingSteps = "Potong potong tempe\n" +
                    "labu\n" +
                    "kacang panjang\n" +
                    "dan bawang bombay sesuai selera. Cincang bawang putih. Sisihkan.\n" +
                    "Didihkan air. Tambahkan bawang bombay\n" +
                    "bawang putih\n" +
                    "saus tiram\n" +
                    "kaldu jamur\n" +
                    "garam\n" +
                    "dan lada.\n" +
                    "Masukkan tempe. Aduk rata. Diamkan sebentar sampai bumbu meresap ke tempe.\n" +
                    "Masukkan kacang panjang dan labu siam. Aduk rata\n" +
                    "diamkan sebentar sampai sayuran matang.\n" +
                    "Siap disajikan.",
            recipePictures = "tumis_tempe_labu_kacang_panjang",
            mealType = 2
        ),
        Recipe(
            recipeId = 49,
            name = "Tumis Kacang Panjang dan Tauge",
            calories = 90.0,
            carbs = 6.1,
            fat = 7.3,
            protein = 2.0,
            ingredients = "50 gram kecambah\n" +
                    "100 gram kacang panjang hijau\n" +
                    "1/4 sdm cabai merah atau rawit\n" +
                    "1 sdm minyak sayur canola\n" +
                    "1 siung bawang putih\n" +
                    "1/2 gelas air\n" +
                    "10 gram bawang merah",
            cookingSteps = "Potong semua sayuran yang dibutuhkan.\n" +
                    "Tumis cabai\n" +
                    "bawang merah\n" +
                    "bawang putih dengan minyak canola.\n" +
                    "Masukkan tauge dan kacang pang\n" +
                    "beri air sedikit dan diamkan hingga sedikit layu.",
            recipePictures = "tumis_kacang_panjang_dan_tauge",
            mealType = 2
        ),
        Recipe(
            recipeId = 50,
            name = "Beef Patty",
            calories = 230.0,
            carbs = 6.7,
            fat = 13.7,
            protein = 18.9,
            ingredients = "500 gram daging sapi\n" +
                    "2 sedang telur\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "1 sdt garam\n" +
                    "74 g roti tawar\n" +
                    "6 g lada putih bubuk\n" +
                    "100 gram bawang bombay",
            cookingSteps = "Haluskan daging bersama roti tawar.\n" +
                    "Dalam mangkuk besar\n" +
                    "campurkan daging giling dengan bumbu-bumbu dan bawang bombay yang sudah dicincang halus.\n" +
                    "Masukkan telur dan aduk rata.\n" +
                    "Bagi adonan menjadi 8 bagian\n" +
                    "bentuk bulat pipih.\n" +
                    "Grill di pan anti lengket.",
            recipePictures = "beef_patty",
            mealType = 2
        ),
        Recipe(
            recipeId = 52,
            name = "Sawi Tumis",
            calories = 53.0,
            carbs = 9.1,
            fat = 0.7,
            protein = 3.8,
            ingredients = "12 gram bawang putih\n" +
                    "5 g kaldu jamur\n" +
                    "189 gram sawi hijau",
            cookingSteps = "Potong sawi dan bawang.\n" +
                    "Tumis bawang tanpa minyak\n" +
                    "tbahkan sedikit air.\n" +
                    "Tambahkan kaldu jamur dan tunggu sampai matang.",
            recipePictures = "sawi_tumis",
            mealType = 2
        ),
        Recipe(
            recipeId = 54,
            name = "Tumis Labu Siam",
            calories = 61.0,
            carbs = 4.2,
            fat = 5.1,
            protein = 0.8,
            ingredients = "5 gram cabai merah atau rawit\n" +
                    "329 gram labu siam\n" +
                    "5 gram bawang putih\n" +
                    "5 gram daun bawang\n" +
                    "500 ml air\n" +
                    "27 gram bawang merah\n" +
                    "27 ml minyak goreng",
            cookingSteps = "Tumis bawang merah\n" +
                    "bawang putih\n" +
                    "cabe rawit dan daun bawang dengan minyak goreng.\n" +
                    "Masukkan labu siam yang sudah dipotong-potong.\n" +
                    "Masukkan air.\n" +
                    "Tambahkan bumbu yang disukai (garam dan merica) secukupnya.",
            recipePictures = "tumis_labu_siam",
            mealType = 2
        ),
        Recipe(
            recipeId = 57,
            name = "Dimsum Ayam Udang",
            calories = 50.0,
            carbs = 4.3,
            fat = 1.9,
            protein = 3.7,
            ingredients = "200 gram paha ayam\n" +
                    "100 gram udang\n" +
                    "68 gram telur\n" +
                    "19 gram kaldu jamur\n" +
                    "4 gram minyak wijen\n" +
                    "78 gram wortel\n" +
                    "14 gram bawang putih\n" +
                    "92 gram kulit pangsit\n" +
                    "15 gram gula pasir\n" +
                    "5 gram garam laut\n" +
                    "144 g tepung tapioka\n" +
                    "1 g lada putih bubuk\n" +
                    "6 ml kecap asin",
            cookingSteps = "Cuci bersih udang\n" +
                    "potong kecil-kecil\n" +
                    "sisihkan.\n" +
                    "Masukkan semua bahan ke food proccesor kecuali udang dan kulit pangsit.\n" +
                    "Blender sampai halus.\n" +
                    "Setelah adonan halus tambahkan udang potong kemudian diaduk.\n" +
                    "Masukan ke dalam kulit lumpia\n" +
                    "kukus 20 menit.",
            recipePictures = "dimsum_ayam_udang",
            mealType = 2
        ),
        Recipe(
            recipeId = 61,
            name = "Dada Ayam Suwir",
            calories = 283.0,
            carbs = 22.3,
            fat = 14.3,
            protein = 18.4,
            ingredients = "55 gram dada ayam\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 sdt gula pasir\n" +
                    "2 kecil bawang merah\n" +
                    "2 g royco ayam\n" +
                    "10 ml minyak goreng",
            cookingSteps = "Rebus dada ayam\n" +
                    "setelah matang suwir sesuai selera.\n" +
                    "Tumis cabe rawit\n" +
                    "bawang merah\n" +
                    "bawant putih yg sudah di haluskan sampai wangi dengan minyak.\n" +
                    "Masukkan dada ayam yg sudah di suwir lalu aduk rata.\n" +
                    "Tambahkan kaldu ayam dan gula.\n" +
                    "Siap di hidangkan.",
            recipePictures = "dada_ayam_suwir",
            mealType = 2
        ),
        Recipe(
            recipeId = 62,
            name = "Tumis Jamur Daging Brokoli",
            calories = 79.0,
            carbs = 7.4,
            fat = 2.1,
            protein = 8.4,
            ingredients = "100 gram mushrooms\n" +
                    "1/2 sdt sugar\n" +
                    "1/2 sdt salt\n" +
                    "60 gram broccoli\n" +
                    "20 gram onions\n" +
                    "70 gram beef thin-sliced (cured)\n" +
                    "3 ml minyak goreng\n" +
                    "10 g chili\n" +
                    "50 g saus lada hitam",
            cookingSteps = "Iris tipis jamur dan potong potong brokoli.\n" +
                    "Iris tipis bawang bombai dan cabe.\n" +
                    "Tumis bawang bombai dengan minyak goreng.\n" +
                    "Tambahkan daging\n" +
                    "jamur\n" +
                    "dan brokoli untuk dimasak lalu cabai.\n" +
                    "Tuangkan saus dan garam untuk membumbui.\n" +
                    "Masak hingga matang. Sajikan.",
            recipePictures = "tumis_jamur_daging_brokoli",
            mealType = 2
        ),
        Recipe(
            recipeId = 63,
            name = "Kimchi Jiggae",
            calories = 210.0,
            carbs = 17.0,
            fat = 5.7,
            protein = 26.1,
            ingredients = "50 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "1 besar putih telur\n" +
                    "1 sdm bubuk cabai\n" +
                    "1 siung bawang putih\n" +
                    "15 gram daun bawang\n" +
                    "50 gram kimchi\n" +
                    "50 gram tahu\n" +
                    "50 gram bawang bombay\n" +
                    "50 gram jamur kancing",
            cookingSteps = "Oseng bawang putih dan bawang bombay tanpa minyak sampai layu.\n" +
                    "Masukin air kurleb 300ml.\n" +
                    "Masukin ayam dan tahu setelah ayam matang masukan kimchi dan jamur...\n" +
                    "Setelah semua layu masukan putih telur\n" +
                    "bubuk cabai dan daun bawang aduk aduk. Taburi dengan nori dan biji wijen (opsional).",
            recipePictures = "kimchi_jiggae",
            mealType = 2
        ),
        Recipe(
            recipeId = 64,
            name = "Bakso Kentang Fettuccine",
            calories = 343.0,
            carbs = 60.4,
            fat = 7.7,
            protein = 11.6,
            ingredients = "10 gram cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "50 gram kentang (daging\n" +
                    "dengan garam\n" +
                    "direbus)\n" +
                    "1 kecil bawang merah\n" +
                    "2 kecil bakso daging sapi\n" +
                    "30 g fettuccine\n" +
                    "45 g bolognese sauce",
            cookingSteps = "Rebus kentang dan fettuccine selama 5-10 menit.\n" +
                    "Tumis bakso\n" +
                    "bawang putih\n" +
                    "bawang merah\n" +
                    "cabai yg sudah di potong.\n" +
                    "Masukan bolognese sauce dan tumis hingga harum bersama bakso.",
            recipePictures = "bakso_kentang_fettuccine",
            mealType = 2
        ),
        Recipe(
            recipeId = 66,
            name = "Oseng Cabe Tahu Tempe",
            calories = 330.0,
            carbs = 40.3,
            fat = 18.8,
            protein = 14.3,
            ingredients = "200 gram cabai merah atau rawit\n" +
                    "10 buah tahu goreng\n" +
                    "8 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "10 buah tempe goreng",
            cookingSteps = "Goreng tahu dan tempe dengan minyak\n" +
                    "sampai setengah matang\n" +
                    "kemudian angkat tiriskan.\n" +
                    "Tumis bawang merah dengan sisa minyak goreng tadi\n" +
                    "masak sampai setengah makan.\n" +
                    "Masukkan irisan cabe\n" +
                    "masak sampai setengah matang\n" +
                    ".\n" +
                    "Masukkan gorengan tempe dan tahu\n" +
                    "tambahakan garam secukupnya. Siap disajikan.",
            recipePictures = "oseng_cabe_tahu_tempe",
            mealType = 2
        ),
        Recipe(
            recipeId = 68,
            name = "Teriyaki Beef",
            calories = 366.0,
            carbs = 15.1,
            fat = 20.9,
            protein = 26.7,
            ingredients = "1/2 sdt bawang putih bubuk\n" +
                    "1/2 sdt garam\n" +
                    "1 sdt jahe\n" +
                    "250 gram daging sapi top sirloin (diiris hingga 0.3 cm lemak)\n" +
                    "1/2 sdt gula pasir\n" +
                    "1 sdm kecap manis\n" +
                    "15 g soy sauce\n" +
                    "3 g lada putih bubuk\n" +
                    "15 g saus bumbu rasa tiram\n" +
                    "50 gram bawang bombay\n" +
                    "1 sdm minyak wijen",
            cookingSteps = "Marinasi Daging dengan kecap asin\n" +
                    "kecap manis\n" +
                    "minyak wijen\n" +
                    "saos tiram\n" +
                    "garam\n" +
                    "gula\n" +
                    "bawang putih\n" +
                    "lada putih\n" +
                    "jahe. Kurang lebih 15 - 30 menit.\n" +
                    "Masak Daging tanpa minyak. Sampai matang. Setelah itu beri air sedikit\n" +
                    "kemudian jika masukkan bawang Bombay. Masak hingga layu.\n" +
                    "Hidangkan.",
            recipePictures = "teriyaki_beef",
            mealType = 2
        ),
        Recipe(
            recipeId = 69,
            name = "Tumis Sayur dengan Telur",
            calories = 379.0,
            carbs = 38.6,
            fat = 20.9,
            protein = 13.5,
            ingredients = "1 besar telur\n" +
                    "100 gram kecambah\n" +
                    "1/2 sdt garam\n" +
                    "100 gram kubis\n" +
                    "1 mentimun (dengan kulit)\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm kecap manis\n" +
                    "15 ml minyak goreng\n" +
                    "1 sdm saos tiram",
            cookingSteps = "Cuci bersih toge\n" +
                    "kol\n" +
                    "timun. Potong sesuai selera. Kupas dan cincang 1 siung bawang putih.\n" +
                    "Panaskan 1 sdm minyak\n" +
                    "masukan telur kemudian diorak arik\n" +
                    "masukin bawang putih\n" +
                    "tumis sebentar\n" +
                    "masukan sayuran.\n" +
                    "Beri sedikit air\n" +
                    "masukan garam\n" +
                    "saus tiram dan kecap manis. Masak sampai matang. Sajikan.",
            recipePictures = "tumis_sayur_dengan_telur",
            mealType = 2
        ),
        Recipe(
            recipeId = 71,
            name = "Aglio Olio",
            calories = 292.0,
            carbs = 33.5,
            fat = 8.5,
            protein = 19.2,
            ingredients = "1/2 kecil chicken breast (skin not eaten)\n" +
                    "1 medium carrot\n" +
                    "1 mangkok green string beans\n" +
                    "1 sdt\n" +
                    "tumbuk oregano\n" +
                    "1 chicken sausage\n" +
                    "2 sdt minced garlic\n" +
                    "11 ml minyak goreng\n" +
                    "60 g spaghetti\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm saos tiram\n" +
                    "2\n" +
                    "5 g boncabe\n" +
                    "1\n" +
                    "5 g garam",
            cookingSteps = "Rebus spageti sampai al dente.\n" +
                    "Tumis bawang putih\n" +
                    "masukan dada ayam\n" +
                    "sosis\n" +
                    "sayuran\n" +
                    "lalu saus tiram dan bumbu.\n" +
                    "Masukan spageti yg sudah direbus\n" +
                    "lada garam\n" +
                    "oregano\n" +
                    "dan boncabe.\n" +
                    "Siap disajikan.",
            recipePictures = "aglio_olio",
            mealType = 2
        ),
        Recipe(
            recipeId = 72,
            name = "Sawi Tumis Tauge",
            calories = 91.0,
            carbs = 9.4,
            fat = 5.2,
            protein = 4.8,
            ingredients = "200 gram kecambah\n" +
                    "3 sejumput garam\n" +
                    "67 gram sawi\n" +
                    "11 gram bawang putih\n" +
                    "19 gram bawang merah\n" +
                    "2 g msg\n" +
                    "10 ml minyak goreng sawit",
            cookingSteps = "Potong sawi\n" +
                    "cuci bersih kecambah sisihkan.\n" +
                    "Iris duo bawang merah dan bawang putih\n" +
                    "lalu tumis dengan minyak.\n" +
                    "Masukkan sawi tumis dan bumbui hingga matang siap disajikan.",
            recipePictures = "sawi_tumis_tauge",
            mealType = 2
        ),
        Recipe(
            recipeId = 74,
            name = "Bola Ikan Udang Wortel",
            calories = 30.0,
            carbs = 1.1,
            fat = 0.9,
            protein = 4.1,
            ingredients = "100 gram udang\n" +
                    "102 gram telur (utuh)\n" +
                    "77 gram wortel\n" +
                    "1 1/2 sendok kaldu jamur\n" +
                    "5 g lada putih bubuk\n" +
                    "34 gram bawang bombay\n" +
                    "15 ml saus rasa tiram\n" +
                    "300 gram ikan tenggiri",
            cookingSteps = "Kupas udang dan buang tulang di tenggiri.\n" +
                    "Campur semua bahan dalam food processor dan bumbui sesuai keinginan.\n" +
                    "Buat bola-bola (masing-masing sekitar 25 g).\n" +
                    "Masukkan ke dalam kukusan dan masak selama 20 menit.",
            recipePictures = "bola_ikan_udang_wortel",
            mealType = 2
        ),
        Recipe(
            recipeId = 75,
            name = "Rebusan Ayam Saus Tiram",
            calories = 329.0,
            carbs = 21.3,
            fat = 12.8,
            protein = 29.9,
            ingredients = "100 gram dada ayam\n" +
                    "1 sejumput lada hitam\n" +
                    "1 siung bawang putih\n" +
                    "2 gram daun bawang\n" +
                    "1 sejumput garam laut\n" +
                    "1/2 sdm minyak goreng\n" +
                    "1 1/2 sdm kecap manis\n" +
                    "1 sdm saos tiram\n" +
                    "10 gram bawang bombay",
            cookingSteps = "Campurkan saus tiram\n" +
                    "kecap asin\n" +
                    "merica\n" +
                    "dan sedikit air dalam mangkuk.\n" +
                    "Potong ayam menjadi kubus dan rendam dalam saus.\n" +
                    "Tumis bawang putih\n" +
                    "daun bawang\n" +
                    "dan bawang bombay dalam wajan dengan minyak. Aduk hingga harum.\n" +
                    "Masukkan ayam yang sudah dimarinasi dengan saus dan garam. Rebus sampai daging berwarna cokelat muda.",
            recipePictures = "rebusan_ayam_saus_tiram",
            mealType = 2
        ),
        Recipe(
            recipeId = 79,
            name = "Tumis Udang dan Bakso",
            calories = 164.0,
            carbs = 14.2,
            fat = 4.0,
            protein = 17.7,
            ingredients = "80 gram udang\n" +
                    "118 gram wortel\n" +
                    "120 gram kembang kol\n" +
                    "1 siung bawang putih\n" +
                    "30 gram bakso daging sapi\n" +
                    "30 gram bakso ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "2 sdm saos tiram\n" +
                    "5 gram cabai merah besar",
            cookingSteps = "Cuci udang dan kupas. Potong sayuran.\n" +
                    "Tumis bawang putih di wajan yang sudah diolesi sedikit minyak sampai harum lalu tambahkan saus tiram.\n" +
                    "Masukkan udang\n" +
                    "bakso\n" +
                    "dan sayuran.\n" +
                    "Bumbui dan didihkan sampai matang.",
            recipePictures = "tumis_udang_dan_bakso",
            mealType = 2
        ),
        Recipe(
            recipeId = 81,
            name = "Tumis Sayuran dengan Ayam",
            calories = 197.0,
            carbs = 114.2,
            fat = 1.5,
            protein = 6.0,
            ingredients = "1 irisan tipis ayam\n" +
                    "100 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "1 kecil bawang merah\n" +
                    "1 bonggol jagung kecil\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm saos tiram\n" +
                    "100 gram sawi hijau\n" +
                    "20 g tepung maizena",
            cookingSteps = "Potong-potong ayam dan sayuran.\n" +
                    "Tumis bawang putih dan bawang bombay dalam wajan sampai harum.\n" +
                    "Masukkan ayam\n" +
                    "aduk sebentar hingga daging berubah warna menjadi putih.\n" +
                    "Masukkan sisa sayur\n" +
                    "bumbu\n" +
                    "dan tepung maizena yang sudah dilarutkan dalam air.\n" +
                    "Masak hingga matang lalu sajikan.",
            recipePictures = "tumis_sayuran_dengan_ayam",
            mealType = 2
        ),
        Recipe(
            recipeId = 82,
            name = "Ayam Goreng Bumbu Kuning",
            calories = 486.0,
            carbs = 2.5,
            fat = 27.0,
            protein = 54.1,
            ingredients = "800 gram ayam\n" +
                    "300 ml air\n" +
                    "25 g bumbu racik ayam goreng",
            cookingSteps = "Cara memasak\n" +
                    "Bilas dan potong ayam.\n" +
                    "Masukkan air ke dalam panci\n" +
                    "tambahkan bumbu ayam goreng dan didihkan.\n" +
                    "Masukkan potongan ayam\n" +
                    "lalu masak dengan api kecil hingga empuk dan air menyusut.\n" +
                    "Goreng ayam di air fryer tanpa menggunakan minyak hingga matang.",
            recipePictures = "ayam_goreng_bumbu_kuning",
            mealType = 2
        ),
        Recipe(
            recipeId = 84,
            name = "Ayam Teriyaki",
            calories = 204.0,
            carbs = 26.2,
            fat = 2.8,
            protein = 15.9,
            ingredients = "25 gram bawang bombay\n" +
                    "1 sejumput garam\n" +
                    "50 gram daging dada ayam (ayam pedaging\n" +
                    "dipanggang\n" +
                    "dimasak)\n" +
                    "100 ml air\n" +
                    "1 sdm kecap manis\n" +
                    "15 g saus sambal\n" +
                    "1 jumput msg\n" +
                    "10 ml saori saus teriyaki",
            cookingSteps = "Dalam wajan panas dengan sedikit air\n" +
                    "tumis bawang bombay dan cabai yang sudah dicincang.\n" +
                    "Tambahkan air dan potongan ayam ke dalam wajan. Aduk selama beberapa menit.\n" +
                    "Tambahkan semua bahan yang tersisa. Aduk rata dan pindahkan ke piring setelah matang.",
            recipePictures = "ayam_teriyaki",
            mealType = 2
        ),
        Recipe(
            recipeId = 85,
            name = "Sup Daging Selada Air",
            calories = 193.0,
            carbs = 8.9,
            fat = 10.9,
            protein = 17.1,
            ingredients = "200 gram daging sapi\n" +
                    "20 gram cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "15 gram bawang putih\n" +
                    "300 gram selada air\n" +
                    "150 gram jamur tiram\n" +
                    "2 g royco ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "40 gram bawang bombay",
            cookingSteps = "Rebus daging buang airnya\n" +
                    "direbus kembali dengan bawang putih\n" +
                    "bawang bombay dan cabe\n" +
                    "bumbui dengan garam\n" +
                    "merica\n" +
                    "dan royco ayam.\n" +
                    "Masukan jamur\n" +
                    "tunggu beberapa saat.\n" +
                    "Masukan selada air tunggu sampe selada air matang sebelum disajikan.",
            recipePictures = "sup_daging_selada_air",
            mealType = 2
        ),
        Recipe(
            recipeId = 86,
            name = "Brokoli Bawang Putih",
            calories = 190.0,
            carbs = 15.3,
            fat = 11.7,
            protein = 8.0,
            ingredients = "1 sdt garam\n" +
                    "100 gram brokoli\n" +
                    "3 siung bawang putih\n" +
                    "1/2 sdt gula pasir\n" +
                    "5 ml olive oil\n" +
                    "5 g kaldu jamur\n" +
                    "30 g sosis\n" +
                    "6 g saus tiram",
            cookingSteps = "Bersihkan brokoli\n" +
                    "potong lalu rendam dalam larutan garam 5-10menit.\n" +
                    "Cincang bawang putih. Goreng bawang putih menggunakan ollive oil sampe kecoklatan\n" +
                    "sisihkan.\n" +
                    "Tumis sedikit bawang putih.\n" +
                    "Masukan brokoli dan sosis. Tambahkan air kemudian tutup\n" +
                    "tunghu sampai brokoli agak layu.\n" +
                    "Tambahkan saus tiram\n" +
                    "gula pasir\n" +
                    "garam\n" +
                    "dan kaldu jamur.\n" +
                    "Hidangkan brokoli kemudian siramkan bawang putih goreng diatasnya.",
            recipePictures = "brokoli_bawang_putih",
            mealType = 2
        ),
        Recipe(
            recipeId = 88,
            name = "Ayam Suwir Kemangi",
            calories = 314.0,
            carbs = 15.2,
            fat = 14.4,
            protein = 32.9,
            ingredients = "100 gram dada ayam\n" +
                    "1 utuh sedang tomat\n" +
                    "5 gram cabai merah atau rawit\n" +
                    "3 gram kunyit\n" +
                    "40 gram kemangi\n" +
                    "1 siung bawang putih\n" +
                    "28 g bawang merah\n" +
                    "5 ml olive oil",
            cookingSteps = "Rebus ayam dan suwir.\n" +
                    "Tumis bawang merah\n" +
                    "bawang putih dan tomat dengan minyak zaitun.\n" +
                    "Tambahkan sedikit air\n" +
                    "suwiran ayam\n" +
                    "kemangi\n" +
                    "kunyit\n" +
                    "cabai\n" +
                    "garam dan merica.\n" +
                    "Siap disajikan.",
            recipePictures = "ayam_suwir_kemangi",
            mealType = 2
        ),
        Recipe(
            recipeId = 89,
            name = "Ayam Suwir Kecap",
            calories = 492.0,
            carbs = 22.9,
            fat = 23.0,
            protein = 45.5,
            ingredients = "150 gram dada ayam\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 sdm minyak goreng\n" +
                    "1 sdm kecap manis\n" +
                    "10 g kaldu jamur\n" +
                    "30 gram bawang bombay",
            cookingSteps = "Rebus/kukus ayam sampai matang.\n" +
                    "Jika sudah matang\n" +
                    "angkat lalu robek-robek daging ayam menjadi ukuran kecil.\n" +
                    "Cincang bawang bombay. Haluskan cabe rawit.\n" +
                    "Panasan minyak diatas wajan. Tumis bawang bombay dan cabe rawit. Tambahkan kecap\n" +
                    "kaldu jamur dan garam dan merica.\n" +
                    "Masukkan ayam yang telah disuwir.\n" +
                    "Aduk hingga semua bumbu tercampur rata.\n" +
                    "Sajikan.",
            recipePictures = "ayam_suwir_kecap",
            mealType = 2
        ),
        Recipe(
            recipeId = 90,
            name = "Suwir Ayam Creamy",
            calories = 319.0,
            carbs = 9.5,
            fat = 9.9,
            protein = 44.9,
            ingredients = "280 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "20 gram kaldu jamur\n" +
                    "10 gram keju cheddar\n" +
                    "10 gram bawang putih\n" +
                    "10 gram tomat merah\n" +
                    "1 sejumput garam laut\n" +
                    "200 ml non fat milk\n" +
                    "6 g lada putih bubuk\n" +
                    "9 ml minyak goreng tropical",
            cookingSteps = "Rebus dada ayam kemudian suwir.\n" +
                    "Panaskan minyak. Tumis bawang putih hingga kecoklatan.\n" +
                    "Masukan tomat cincang\n" +
                    "aduk sebentar.\n" +
                    "Masukan dada ayam suwir\n" +
                    "aduk rata.\n" +
                    "Tuangkan susu non fat.\n" +
                    "Bumbui dengan lada\n" +
                    "garam\n" +
                    "dan kaldu jamur.\n" +
                    "Masukan keju\n" +
                    "aduk kembali.",
            recipePictures = "suwir_ayam_creamy",
            mealType = 2
        ),
        Recipe(
            recipeId = 91,
            name = "Sup Daging Sapi dengan Kacang Panjang",
            calories = 246.0,
            carbs = 19.9,
            fat = 9.7,
            protein = 20.8,
            ingredients = "100 gram tomat\n" +
                    "200 gram kacang panjang hijau (dari segar)\n" +
                    "3 siung bawang putih\n" +
                    "1 sdt jahe\n" +
                    "1 mangkok daun bawang\n" +
                    "100 gram iga sapi (potongan kecil\n" +
                    "diiris hingga 0.3 cm lemak)\n" +
                    "250 gram daging sapi (95% tanpa lemak / 5% lemak)\n" +
                    "4 sedang bawang merah",
            cookingSteps = "Cuci bersih daging sapi dan iga sapi\n" +
                    "potong kecil-kecil daging dan masak dengan cara 5-30-7 (rebus 5 menit\n" +
                    "matikan api\n" +
                    "diamkan di atas kompor selama 30 menit\n" +
                    "dan lanjutkan proses memasak dengan merebusnya selama 7 menit lagi).\n" +
                    "Haluskan bawang merah\n" +
                    "bawang putih dan jahe.\n" +
                    "Iris kacang panjang\n" +
                    "tomat dan daun bawang.\n" +
                    "Tumis bumbu halus dengan minyak hingga harum\n" +
                    "tambahkan air lalu daging sapi dan iga. Biarkan mendidih.\n" +
                    "Setelah mendidih\n" +
                    "masukkan kacang panjang dan tomat\n" +
                    "bumbui dengan garam dan kaldu jamur secukupnya.\n" +
                    "Jika sudah matang\n" +
                    "matikan api dan masukkan daun bawang yang sudah dipotong-potong.",
            recipePictures = "sup_daging_sapi_dengan_kacang_panjang",
            mealType = 2
        ),
        Recipe(
            recipeId = 93,
            name = "Tumis Brokoli Jamur",
            calories = 282.0,
            carbs = 8.5,
            fat = 17.9,
            protein = 22.3,
            ingredients = "150 gram daging sapi giling\n" +
                    "70 gram brokoli\n" +
                    "13 gram bawang putih\n" +
                    "3 g margarin\n" +
                    "113 gram jamur shimeji\n" +
                    "5 g saos tiram\n" +
                    "5 ml minyak wijen",
            cookingSteps = "Tumis bawang putih dengan mentega sampai wangi.\n" +
                    "Masukkan daging dan jamur\n" +
                    "masak hingga matang.\n" +
                    "Masukkan brokoli.\n" +
                    "Masukkan saus tiram dan minyak wijen\n" +
                    "tumis hingga matang.",
            recipePictures = "tumis_brokoli_jamur",
            mealType = 2
        ),
        Recipe(
            recipeId = 94,
            name = "Sop",
            calories = 353.0,
            carbs = 26.5,
            fat = 14.9,
            protein = 28.6,
            ingredients = "51 gram brokoli dimasak (dari segar)\n" +
                    "17 gram cabai merah atau rawit\n" +
                    "3 sdt garam\n" +
                    "36 gram kulit ayam\n" +
                    "130 gram wortel\n" +
                    "15 gram bawang putih\n" +
                    "93 gram tomat merah\n" +
                    "2 sdt gula pasir\n" +
                    "15 gram bawang merah\n" +
                    "22 g jamur enoki\n" +
                    "10 g bumbu racik sayur sop\n" +
                    "20 g kaldu jamur\n" +
                    "6 ml minyak goreng\n" +
                    "1 porsi udang kukus atau rebus\n" +
                    "2 1/2 ons\n" +
                    "tanpa tulang dada ayam rebus",
            cookingSteps = "Tumis bawang merah\n" +
                    "bawang putih\n" +
                    "cabai (optional kalau ingin pedas) menggunakan minyak hingga wangi.\n" +
                    "Masukkan air kedalam tumisan tadi\n" +
                    "biarkan sampai air mendidih.\n" +
                    "Masukan ayam\n" +
                    "udang\n" +
                    "dan sayur-sayuran kedalam air.\n" +
                    "Tambahkan garam\n" +
                    "gula\n" +
                    "dan kaldu\n" +
                    "racik sayur sop sesuai selera lalu tunggu hingga matang.",
            recipePictures = "sop",
            mealType = 2
        ),
        Recipe(
            recipeId = 98,
            name = "Chicken Ramen Shirataki",
            calories = 426.0,
            carbs = 60.8,
            fat = 5.0,
            protein = 34.2,
            ingredients = "100 gram dada ayam (kulit tidak dimakan)\n" +
                    "50 gram kecambah\n" +
                    "50 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "40 gram tomat merah\n" +
                    "1 gelas air\n" +
                    "1 kecil bawang merah\n" +
                    "50 g mie kering\n" +
                    "3 g royco kaldu rasa sapi\n" +
                    "1 ml minyak goreng",
            cookingSteps = "Iris ayam hingga tipis tipis.\n" +
                    "Potong-potong tomat dan wortel sesuai keinginan.\n" +
                    "Cincang bawang merah dan bawang putih.\n" +
                    "Panaskan minyak kemudian tumis bawang merah\n" +
                    "bawang putih\n" +
                    "dan tomat.\n" +
                    "Masukkan air dan tunggu hingga hampir mendidih.\n" +
                    "Masukkan ayam dan royco perlahan.\n" +
                    "Ketika ayam sudah matang\n" +
                    "keluarkan ayam dari rebusan.\n" +
                    "Masukkan sayuran dan mie shirataki. Tunggu hingga matang!",
            recipePictures = "chicken_ramen_shirataki",
            mealType = 2
        ),
        Recipe(
            recipeId = 106,
            name = "Pokcoy Ayam Saus Tiram",
            calories = 327.0,
            carbs = 25.4,
            fat = 2.7,
            protein = 47.7,
            ingredients = "1 sejumput lada hitam\n" +
                    "200 gram daging dada ayam (ayam pedaging)\n" +
                    "1 siung bawang putih\n" +
                    "1 sdt jahe\n" +
                    "100 ml air\n" +
                    "150 gram pakcoy\n" +
                    "15 ml kecap manis light\n" +
                    "10 g tepung pati jagung\n" +
                    "15 g saos tiram\n" +
                    "1 bawang bombay",
            cookingSteps = "Larutkan maizena dengan sedikit air.\n" +
                    "Didihkan air dan rebus pokcoy selama 2 menit. Hapus dari panas dan tiriskan.\n" +
                    "Dalam wajan panas\n" +
                    "tumis bawang putih\n" +
                    "jahe\n" +
                    "dan bawang bombay hingga harum.\n" +
                    "Masukkan ayam yang sudah dipotong-potong ke dalam wajan dan masak hingga berubah warna.\n" +
                    "Tambahkan saus tiram\n" +
                    "kecap asin\n" +
                    "merica\n" +
                    "air\n" +
                    "dan tepung maizena yang sudah dilarutkan.\n" +
                    "Aduk\n" +
                    "biarkan bumbu meresap dan angkat dari api.\n" +
                    "Tata pokcoy di piring dan tuangkan di atas ayam yang sudah dimasak.",
            recipePictures = "pokcoy_ayam_saus_tiram",
            mealType = 2
        ),
        Recipe(
            recipeId = 107,
            name = "Chicken Deopbap",
            calories = 428.0,
            carbs = 27.0,
            fat = 25.9,
            protein = 26.5,
            ingredients = "1 kecil chicken thigh\n" +
                    "1 sedang egg\n" +
                    "1 sdm soy sauce\n" +
                    "1 kecil young green onions\n" +
                    "1 1/2 cups cooked young green onion",
            cookingSteps = "Tumis ayam tanpa minyak sampai kecoklatan.\n" +
                    "Masukan bawang bombay\n" +
                    "tumis sampai layu dgn ayam td\n" +
                    "masukan air secukupnya tunggu mendidih.\n" +
                    "Masukan telur yg sudah dikocok lepas\n" +
                    "lalu masukan kecap asin dan daun bawang. Bumbui dengan garam dan merica sesuai selera. Tutup wajan sampai telur matang.",
            recipePictures = "chicken_deopbap",
            mealType = 2
        ),
        Recipe(
            recipeId = 108,
            name = "Kwetiau Ayam",
            calories = 322.0,
            carbs = 49.0,
            fat = 9.4,
            protein = 9.9,
            ingredients = "15 gram dada ayam\n" +
                    "2 gram bawang bombay dimasak\n" +
                    "1/2 sdt garam\n" +
                    "2 ml minyak goreng\n" +
                    "100 gram kwetiau\n" +
                    "1 sendok saus sambal\n" +
                    "2 sdm kecap manis",
            cookingSteps = "Panaskan minyak dalam wajan dan tumis bawang bombay.\n" +
                    "Masukkan ayam yg sudah di potong\n" +
                    "lalu tumis sebentar.\n" +
                    "Tambahkan kwetiau dan bumbunya.\n" +
                    "Campur dengan baik. Angkat dari api setelah selesai. Tambahkan lauk yang diinginkan.",
            recipePictures = "kwetiau_ayam",
            mealType = 2
        ),
        Recipe(
            recipeId = 111,
            name = "Semur Tahu",
            calories = 212.0,
            carbs = 32.8,
            fat = 7.2,
            protein = 5.8,
            ingredients = "2 siung bawang putih\n" +
                    "1 utuh sedang tomat merah\n" +
                    "1 gelas air\n" +
                    "3 kecil bawang merah\n" +
                    "100 gram tahu\n" +
                    "2 sdm kecap manis\n" +
                    "2 sendok saus sambal\n" +
                    "1 takaran royco ayam\n" +
                    "1 sendok makan minyak goreng sawit\n" +
                    "20 gram cabai merah besar",
            cookingSteps = "Dalam wajan panas dengan minyak\n" +
                    "tumis bawang putih\n" +
                    "bawang merah\n" +
                    "dan cabai merah.\n" +
                    "Tambahkan tomat cincang\n" +
                    "air dan kecap.\n" +
                    "Terakhir masukkan tahu dan saus sambal. Campur dengan baik.\n" +
                    "Masak hingga kental dan tambahkan royco. Aduk dan pindahkan ke dalam mangkuk.",
            recipePictures = "semur_tahu",
            mealType = 2
        ),
        Recipe(
            recipeId = 112,
            name = "Tumis Tahu Telur Wortel",
            calories = 252.0,
            carbs = 39.0,
            fat = 5.8,
            protein = 16.9,
            ingredients = "100 gram kacang panjang hijau\n" +
                    "1 besar putih telur\n" +
                    "100 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "1 utuh sedang tomat merah\n" +
                    "2 kecil bawang merah\n" +
                    "100 gram tahu",
            cookingSteps = "Tumis bawang putih bawang merah dan bawang putih yang sudah dicincang halus.\n" +
                    "Masukkan tomat yang sudah di iris.\n" +
                    "Tumis sampai layu dan wangi tambahkan\n" +
                    "garam secukupnya.\n" +
                    "Kemuadian masukkan air sedikit lalu masukkan tahu dan putuh telur.\n" +
                    "Setelah itu masukkan kacang dan wortel tumis sampai matang.",
            recipePictures = "tumis_tahu_telur_wortel",
            mealType = 2
        ),
        Recipe(
            recipeId = 114,
            name = "Tahu Goreng Telur",
            calories = 188.0,
            carbs = 6.9,
            fat = 16.7,
            protein = 4.5,
            ingredients = "2 sedang telur\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "5 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "4 sedang bawang merah\n" +
                    "250 gram tahu\n" +
                    "1 takaran royco ayam\n" +
                    "125 ml minyak goreng 2x penyaringan",
            cookingSteps = "Haluskan bawang merah\n" +
                    "bawang putih\n" +
                    "dan cabai. Campur mereka dengan baik.\n" +
                    "Tahu dihaluskan lalu masukkan semua bahan kecuali minyak.\n" +
                    "Panaskan minyak dalam wajan dan goreng sampai berwarna cokelat keemasan.",
            recipePictures = "tahu_goreng_telur",
            mealType = 2
        ),
        Recipe(
            recipeId = 116,
            name = "Cumi Lada Hitam",
            calories = 107.0,
            carbs = 6.5,
            fat = 3.4,
            protein = 12.2,
            ingredients = "300 gram cumi-cumi\n" +
                    "50 ml air\n" +
                    "2 tomat ceri\n" +
                    "1 sendok makan minyak goreng\n" +
                    "100 gram bawang bombay\n" +
                    "1 sendok saus lada hitam\n" +
                    "50 gram cabai merah besar",
            cookingSteps = "Belah bawang bombay menjadi dua dan Iris memanjang setengahnya dan tumis dengan minyak sampai harum.\n" +
                    "Masukan cumi\n" +
                    "cabai\n" +
                    "saos lada hitam dan air.\n" +
                    "Kemudian masukan tomat tumis semuanya hingga masak.",
            recipePictures = "cumi_lada_hitam",
            mealType = 2
        ),
        Recipe(
            recipeId = 118,
            name = "Tumis Tahu dan Tuna",
            calories = 313.0,
            carbs = 0.0,
            fat = 18.1,
            protein = 17.9,
            ingredients = "10 gram cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "2 kecil bawang merah\n" +
                    "57 1/2 gram tahu\n" +
                    "43\n" +
                    "8 gram ikan tongkol\n" +
                    "1 sdm minyak kanola\n" +
                    "1 takaran royco ayam",
            cookingSteps = "Iris semua cabai\n" +
                    "bawang merah dan bawa putih. Lalu tumis dengan minyak kanola.\n" +
                    "Potong dadu tahu dan suwir ikan tongkol. Lalu\n" +
                    "masukan ke bumbu yang sudah di tumis.\n" +
                    "Tumis dan masukan garam serta royco secukupnya. Lalu hidangkan.",
            recipePictures = "tumis_tahu_dan_tuna",
            mealType = 2
        ),
        Recipe(
            recipeId = 119,
            name = "Bulgogi",
            calories = 704.0,
            carbs = 65.6,
            fat = 33.8,
            protein = 35.5,
            ingredients = "250 gram daging sapi\n" +
                    "1 sejumput lada hitam\n" +
                    "3 sdm saus tiram\n" +
                    "10 sdm saus tomat\n" +
                    "70 ml kecap manis\n" +
                    "3 g lada putih bubuk\n" +
                    "20 ml minyak goreng\n" +
                    "100 gram bawang bombay\n" +
                    "15 ml kecap asin",
            cookingSteps = "Iris tipis daging sapi dan bawang bombay. Masukkan ke dalam mangkuk dan tambahkan semua bumbu.\n" +
                    "Marinasi selama 30 menit atau semalaman.\n" +
                    "Dalam wajan yang sudah dipanaskan sebelumnya\n" +
                    "tumis daging sapi yang sudah diasinkan\n" +
                    "sering diaduk atau dibalik sesering yang Anda suka sampai matang.\n" +
                    "Sajikan dengan telur dan beberapa sayuran.",
            recipePictures = "bulgogi",
            mealType = 2
        ),
        Recipe(
            recipeId = 124,
            name = "Spaghetti Gandum dengan Saus Tomat",
            calories = 252.0,
            carbs = 34.0,
            fat = 8.0,
            protein = 12.4,
            ingredients = "1 besar telur\n" +
                    "50 gram spageti gandum\n" +
                    "100 gram saus spageti tanpa daging\n" +
                    "1 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "1 kecil bawang merah\n" +
                    "20 ml susu uht low fat",
            cookingSteps = "Rebus air hingga mendidih beri sedikit garam. Masak spaghetti gandum dan tiriskan dalam saringan.\n" +
                    "Tumis bawang bombay di atas granite teflon\n" +
                    "tambahkan saus spageti tomat dan aduk rata.\n" +
                    "Tambahkan spageti dan aduk. Tambahkan air sedikit demi sedikit.\n" +
                    "Tambahkan telur dan susu rendah lemak dan aduk sampai sedikit mengental.\n" +
                    "Pindahkan ke piring dan sajikan dengan mentimun\n" +
                    "opsional.",
            recipePictures = "spaghetti_gandum_dengan_saus_tomat",
            mealType = 2
        ),
        Recipe(
            recipeId = 128,
            name = "Cumi Hitam",
            calories = 278.0,
            carbs = 16.9,
            fat = 12.4,
            protein = 25.8,
            ingredients = "457 gram cumi-cumi\n" +
                    "21 gram cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "37 gram bawang putih\n" +
                    "47 gram bawang merah\n" +
                    "1 sachet sweetener classic\n" +
                    "3 sdm saos tiram\n" +
                    "30 ml minyak goreng\n" +
                    "51 gram cabai merah besar",
            cookingSteps = "Potong potong cumi sesuai selera\n" +
                    "iris bawang merah\n" +
                    "bawang putih dan cabai.\n" +
                    "Panaskan minyak lalu tumis bawang hingga layu\n" +
                    "masukkan cabai beri garam\n" +
                    "gula dan saus tiram.\n" +
                    "Masukkan cumi aduk aduk hingga rata dan beri air.\n" +
                    "Masak selama 5 menit lalu hidangkan.",
            recipePictures = "cumi_hitam",
            mealType = 2
        ),
        Recipe(
            recipeId = 130,
            name = "Pakcoy dengan Bawang Putih",
            calories = 193.0,
            carbs = 14.2,
            fat = 14.7,
            protein = 1.7,
            ingredients = "1 sdt garam\n" +
                    "1 sdt minyak wijen\n" +
                    "4 siung bawang putih\n" +
                    "1 sdt gula pasir\n" +
                    "1 sdm minyak goreng\n" +
                    "150 gram pakcoy\n" +
                    "3 g lada putih bubuk\n" +
                    "5.8 g saos tiram",
            cookingSteps = "Tumis bawang putih dengan minyak goreng.\n" +
                    "Tambahkan saus tiram\n" +
                    "garam\n" +
                    "merica. Rebus pakcoy.\n" +
                    "Tiriskan pakcoy\n" +
                    "siram dengan kuah.",
            recipePictures = "pakcoy_dengan_bawang_putih",
            mealType = 2
        ),
        Recipe(
            recipeId = 134,
            name = "Chicken Teriyaki",
            calories = 304.0,
            carbs = 32.8,
            fat = 3.7,
            protein = 31.6,
            ingredients = "86 gram chicken breast (skin not eaten)\n" +
                    "1 cup chopped broccoli\n" +
                    "1 siung garlic\n" +
                    "1 sedang onions\n" +
                    "1 kecil scallions or spring onions\n" +
                    "50 gram enoki mushrooms\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdt boncabe level 15\n" +
                    "22 ml saus teriyaki",
            cookingSteps = "Panggang ayam sampai setengah matang\n" +
                    "masukkan bawang merah\n" +
                    "bawang putih\n" +
                    "tumis.\n" +
                    "Masukkan brocoli dan teriyaki saus\n" +
                    "boncabe\n" +
                    "lada dan aduk sampai layu.\n" +
                    "Masukkan jamur enoki dan scallions\n" +
                    "masak.",
            recipePictures = "chicken_teriyaki",
            mealType = 2
        ),
        Recipe(
            recipeId = 137,
            name = "Bihun Jamur",
            calories = 433.0,
            carbs = 55.1,
            fat = 12.2,
            protein = 25.4,
            ingredients = "1 besar egg\n" +
                    "50 gram cooked spinach (from fresh)\n" +
                    "5 gram garlic\n" +
                    "50 gram shiitake mushrooms (stir-fried)\n" +
                    "100 gram vermicelli\n" +
                    "50 gram chicken sausage\n" +
                    "1 sdm kecap manis\n" +
                    "1.5 g kaldu rasa jamur\n" +
                    "1 sdm kecap asin",
            cookingSteps = "Tumis bawang putih.\n" +
                    "Masukkan sosis\n" +
                    "jamur. Angkat.\n" +
                    "Orek telor\n" +
                    "angkat.\n" +
                    "Tumis bayam\n" +
                    "angkat.\n" +
                    "Rebus bihun\n" +
                    "tiriskan.\n" +
                    "Tumis bihun\n" +
                    "masukkan kecap asin\n" +
                    "kecap manis\n" +
                    "dan kaldu jamur Masukkan sosis\n" +
                    "jamur\n" +
                    "telor\n" +
                    "bayam.\n" +
                    "Masukkan sosis\n" +
                    "jamur\n" +
                    "telor\n" +
                    "bayam.\n" +
                    "Bumbui dengan garam dan merica sesuai selera.",
            recipePictures = "bihun_jamur",
            mealType = 2
        ),
        Recipe(
            recipeId = 142,
            name = "Tempe Teriyaki",
            calories = 484.0,
            carbs = 51.7,
            fat = 19.9,
            protein = 18.9,
            ingredients = "1 siung bawang putih\n" +
                    "1/2 iris daun bawang\n" +
                    "10 gram lada pisang\n" +
                    "100 gram tempe\n" +
                    "1 sendok saus sambal\n" +
                    "1 sendok makan minyak goreng\n" +
                    "100 ml saus teriyaki",
            cookingSteps = "Potong tempe lalu panggang tempe di api kecil tanpa minyak.\n" +
                    "Lalu tumis bawang putih dengan minyak\n" +
                    "masukan saos teriyaki dan saos sambal makan dan masukkan sejumput lad.\n" +
                    "Tambahkan air secukupnya lalu masukkan tempe yg sudah dipanggamg tadi\n" +
                    "diamkan sampai bumbu meresap dengan api kecil.\n" +
                    "Jika bumbu sudah meresap dan mengering taruh tempe di atas piring dan taburi daun bawang. Tempe siap di sajikan.",
            recipePictures = "tempe_teriyaki",
            mealType = 2
        ),
        Recipe(
            recipeId = 143,
            name = "Tumis Ayam Tahu Telur",
            calories = 461.0,
            carbs = 4.4,
            fat = 27.1,
            protein = 47.6,
            ingredients = "125 gram dada ayam\n" +
                    "1 besar telur\n" +
                    "2 siung bawang putih\n" +
                    "1 sejumput garam laut\n" +
                    "1 sdm minyak goreng\n" +
                    "50 gram tahu\n" +
                    "50 ml aqua\n" +
                    "1 sdm saos tiram",
            cookingSteps = "Potong-potong dadu ayam dan tahu.\n" +
                    "Geprek bawang putih\n" +
                    "rajang.\n" +
                    "Tumis bawang putih dengan minyak goreng sampai harum.\n" +
                    "Masukkan ayam\n" +
                    "tahu dan air\n" +
                    "masak sampai setengah matang\n" +
                    "masukkan saus tiram.\n" +
                    "Tumis ayam dan tahu sampai matang\n" +
                    "masukkan telur\n" +
                    "tumis sampai telur setengah matang\n" +
                    "tes rasa\n" +
                    "kalau kurang asin bisa tambah garam sedikit\n" +
                    "tumis sampai matang\n" +
                    "sajikan.",
            recipePictures = "tumis_ayam_tahu_telur",
            mealType = 2
        ),
        Recipe(
            recipeId = 144,
            name = "Oseng Tahu Buncis Telur",
            calories = 77.0,
            carbs = 4.7,
            fat = 4.9,
            protein = 4.9,
            ingredients = "1 sedang telur\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "200 gram buncis\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm minyak goreng\n" +
                    "181 gram tahu\n" +
                    "4 g royco ayam\n" +
                    "3 ml saori saus teriyaki",
            cookingSteps = "Panaskan minyak lalu masukkan bawang putih cincang.\n" +
                    "Orak-arik telur\n" +
                    "tambahkan tahu potong dadu dan dan sedikit air.\n" +
                    "Tambahkan saus teriyaki\n" +
                    "royco\n" +
                    "dan cabai dan kacang buncis. Masak sebentar hingga matang.",
            recipePictures = "oseng_tahu_buncis_telur",
            mealType = 2
        ),
        Recipe(
            recipeId = 146,
            name = "Capcay Toge",
            calories = 200.0,
            carbs = 23.4,
            fat = 5.5,
            protein = 20.3,
            ingredients = "50 gram kacang panjang hijau\n" +
                    "1 tongkol/bonggol besar jagung\n" +
                    "1 sejumput garam\n" +
                    "50 gram wortel\n" +
                    "500 ml air\n" +
                    "1 sdt gula pasir\n" +
                    "1 kecil bawang merah\n" +
                    "100 g sayur toge\n" +
                    "3 g royco ayam\n" +
                    "10 ml minyak goreng",
            cookingSteps = "Tumis bawang merah yang sudah diiris-iris dengan minyak panas.\n" +
                    "Lalu masukkan wortel\n" +
                    "toge\n" +
                    "jagung\n" +
                    "dan kacang panjang. Tambahkan air sekitar 500 ml.\n" +
                    "Lalu tambahkan bumbu-bumbu penyebab seperti garam\n" +
                    "gula dan royco (sesuai selera).\n" +
                    "Lalu tunggu air menyusut menjadi setengah.\n" +
                    "Setelah itu siap dihidangkan.",
            recipePictures = "capcay_toge",
            mealType = 2
        ),
        Recipe(
            recipeId = 148,
            name = "Spaghetti",
            calories = 693.0,
            carbs = 71.9,
            fat = 26.4,
            protein = 44.5,
            ingredients = "115 gram udang\n" +
                    "142 gram jamur dimasak (lemak ditambahkan dalam masakan)\n" +
                    "3 gram kaldu jamur\n" +
                    "2 gram cabai merah atau rawit\n" +
                    "100 gram wortel\n" +
                    "3 gram bawang putih\n" +
                    "2 gelas air\n" +
                    "2 sejumput garam laut\n" +
                    "20 g quick melt keju\n" +
                    "15 ml minyak goreng tropical\n" +
                    "60 g spaghetti gluten free\n" +
                    "30 gram bawang bombay",
            cookingSteps = "Siapkan bahan bawang puith\n" +
                    "bombai dan cabe rawit. Cuci bersih lalu di chop.\n" +
                    "Rebus spagheti tambahkan sejumput garam dan sedikit minyak. Rebus spagheti hingga aldente kira-kira 8 menit.\n" +
                    "Bersihkan udang hingga bersih dan tdk amis lagi.\n" +
                    "Iris wortel berbentuk korek api dan bersihkan jamur.\n" +
                    "Tumis semua bahan bawang putih\n" +
                    "bombai dan rawit lalu masukkan udang\n" +
                    "tumis hingga setengah matang lalu masukkan wortel dan setelah itu jamur.\n" +
                    "Tambahkan air rebusan spagheti lalu di tumis dan masukkan spagheti tadi.\n" +
                    "Masak sebentar lalu tambahkan keju yang sudah diparut.\n" +
                    "Siap disajikan.",
            recipePictures = "spaghetti",
            mealType = 2
        ),
        Recipe(
            recipeId = 150,
            name = "Tempe Tahu Orek",
            calories = 5.0,
            carbs = 0.4,
            fat = 0.3,
            protein = 0.2,
            ingredients = "16 gram bawang putih\n" +
                    "132 gram tempe\n" +
                    "166 gram tahu goreng\n" +
                    "25 gram bawang merah\n" +
                    "45 ml minyak goreng\n" +
                    "90 ml kecap manis\n" +
                    "30 g saus sambal",
            cookingSteps = "Potong potong tempe\n" +
                    "dan tahu goreng.\n" +
                    "Iris bawang merah dan bawang putih.\n" +
                    "Panaskan minyak kemudian tumis bawang merah dan bawang putih yang sudah di iris tipis.\n" +
                    "Setelah bawang merah dan bawang putih setengah matang masukkan tempe kemudian goreng hingga tempe setengah matang.\n" +
                    "Masukkan tahu\n" +
                    "kemudian aduk dan masukkan kecap dan sambal masak hingga matang.",
            recipePictures = "tempe_tahu_orek",
            mealType = 2
        ),
        Recipe(
            recipeId = 151,
            name = "Tumis Sawi Tahu",
            calories = 450.0,
            carbs = 13.9,
            fat = 38.6,
            protein = 14.9,
            ingredients = "200 gram greens\n" +
                    "4 sdm vegetable oil\n" +
                    "2 siung garlic\n" +
                    "1 sedang onions\n" +
                    "250 g tahu kuning",
            cookingSteps = "Goreng tahu menang minyak\n" +
                    "sisihkan.\n" +
                    "Tumis bawang bombay dan bawang putih sampai harum\n" +
                    "tambahkan garam dan merica.\n" +
                    "Masukkan sawi hijau yg sudah dipotong\n" +
                    "aduk sampai sawi layu.\n" +
                    "Masukan tahu goreng yang sudah dipotong.",
            recipePictures = "tumis_sawi_tahu",
            mealType = 2
        ),
        Recipe(
            recipeId = 156,
            name = "Udang Tumis Brokoli",
            calories = 334.0,
            carbs = 8.9,
            fat = 5.7,
            protein = 58.8,
            ingredients = "250 sedang udang\n" +
                    "3 sdt bubuk cabai\n" +
                    "1 sdm bawang putih bubuk\n" +
                    "1 sdt garam\n" +
                    "150 gram brokoli\n" +
                    "10 g bawang merah goreng\n" +
                    "2 g royco ayam\n" +
                    "1 sdm kecap manis\n" +
                    "10 gram bawang bombay",
            cookingSteps = "Rebus udang sampai setengah matang dgn air.\n" +
                    "Masukkan brokoli dan bumbu.\n" +
                    "Rebus sehingga cukup air.",
            recipePictures = "udang_tumis_brokoli",
            mealType = 2
        ),
        Recipe(
            recipeId = 157,
            name = "Sup Tahu Wortel",
            calories = 189.0,
            carbs = 17.5,
            fat = 9.4,
            protein = 14.7,
            ingredients = "2 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "50 gram wortel\n" +
                    "30 gram kembang kol\n" +
                    "2 siung bawang putih\n" +
                    "150 gram tahu",
            cookingSteps = "Potong kembang kol\n" +
                    "wortel dan tahu sesuai kreasi.\n" +
                    "Potong bawang putih dan cabai.\n" +
                    "Masak menggunakan api sedang selama 10 menit.",
            recipePictures = "sup_tahu_wortel",
            mealType = 2
        ),
        Recipe(
            recipeId = 164,
            name = "Bihun Rebus",
            calories = 186.0,
            carbs = 31.1,
            fat = 1.8,
            protein = 1.4,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "1 porsi bawang merah\n" +
                    "2\n" +
                    "75 ml minyak jagung\n" +
                    "60 g bihun jagung\n" +
                    "2 g royco ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "40 gram sawi hijau",
            cookingSteps = "Rebus bihun setengah matang dengan sedikit minyak.\n" +
                    "Cuci dan potong bawang merah\n" +
                    "bawang putih\n" +
                    "cabai rawit\n" +
                    "sawi hijau\n" +
                    "dan seledri.\n" +
                    "Oseng bawang merah\n" +
                    "bawang putih\n" +
                    "dan cabai rawit dengan sedikit minyak cco.\n" +
                    "Tambahkan air secukupnya.\n" +
                    "Bila sudah mendidih masukkan sawi hijau\n" +
                    "dan seledri. Setelah 1 menit masukkan bihun yang sudah setengah matang.\n" +
                    "Masukkan bumbu : garam\n" +
                    "lada\n" +
                    "dan royco secukupnya. Hidangkan.",
            recipePictures = "bihun_rebus",
            mealType = 2
        ),
        Recipe(
            recipeId = 166,
            name = "Stim Ikan Ala Chinese",
            calories = 134.0,
            carbs = 8.1,
            fat = 1.3,
            protein = 23.5,
            ingredients = "120 gram ikan\n" +
                    "1/4 hot chili pepper\n" +
                    "1 siung bawang putih\n" +
                    "1/4 mangkok\n" +
                    "dicincang daun bawang (loncang)\n" +
                    "0\n" +
                    "65 g stevia sweetener\n" +
                    "23 g saus tiram\n" +
                    "15 ml kecap asin",
            cookingSteps = "Masak saos ala chinese dengan cara pertama oseng bawang putih dan jahe.\n" +
                    "Tambahkan air secukupnya.\n" +
                    "Tambahkan kecap asin\n" +
                    "Tambahkan saos tiram.\n" +
                    "Tunggu hingga mendidih.\n" +
                    "Jika ikan sudah masak siram dengan saos ala chinese.\n" +
                    "Tunggu hingga mendidih.\n" +
                    "Stim ikan senangin dengan daun bawang\n" +
                    "cabai merah dan cabai hijau di atasnya.",
            recipePictures = "stim_ikan_ala_chinese",
            mealType = 2
        ),
        Recipe(
            recipeId = 169,
            name = "Pan Sarden Pizza",
            calories = 1244.0,
            carbs = 159.8,
            fat = 44.6,
            protein = 54.0,
            ingredients = "2 siung bawang putih dimasak\n" +
                    "25 gram bawang bombay dimasak\n" +
                    "7 gram ragi\n" +
                    "10 gram cabai merah atau rawit\n" +
                    "3/4 sdt garam\n" +
                    "140 ml air\n" +
                    "7 gram gula pasir\n" +
                    "1 sosis ayam\n" +
                    "1 1/2 sdm minyak goreng\n" +
                    "1 sdm madu\n" +
                    "75 g sarden saus tomat\n" +
                    "10 g cheddar\n" +
                    "1 sendok saus sambal\n" +
                    "32 g smoked beef\n" +
                    "60 g keju mozarella\n" +
                    "30 ml saus tomat\n" +
                    "125 g tepung segitiga biru",
            cookingSteps = "Aduk air hangat\n" +
                    "ragi\n" +
                    "dan gula pasir hingga rata lalu diamkan hingga berbuih (2-3 menit).\n" +
                    "Masukkan garam\n" +
                    "madu\n" +
                    "dan 1.5 sdm minyak dan aduk rata.\n" +
                    "Tuang adukan ragi ke 250 gram tepung terigu\n" +
                    "aduk hingga semua meyatu.\n" +
                    "Alasi permukaan meja dengan minyak. Uleni adonan hingga\n" +
                    "tidak perlu sampai kalis.\n" +
                    "Pipihkan adonan dengan rolling pin seukuran pan yang digunakan.\n" +
                    "Untuk topping pizza\n" +
                    "tumis sosis dan smoked beef yang sudah dipotong sesuai selera. Kemudian sisihkan.\n" +
                    "Tumis bawang putih dan bawang bombai yang sudah diiris hingga wangi. Masukkan sarden\n" +
                    "irisan cabai rawit\n" +
                    "saus sambal dan saus tomat. Masak hingga mengental.\n" +
                    "Panaskan teflon baru di api sedang\n" +
                    "olesi dengan minyak lalu panggang satu sisi pizza hingga matang (agak kecoklatan di beberapa bagian).",
            recipePictures = "pan_sarden_pizza",
            mealType = 2
        ),
        Recipe(
            recipeId = 170,
            name = "Bibimbap",
            calories = 438.0,
            carbs = 68.0,
            fat = 11.7,
            protein = 15.4,
            ingredients = "84 gram telur dadar atau telur orak-arik\n" +
                    "128 gram nasi merah\n" +
                    "10 gram kubis cina (bok-choy\n" +
                    "pak-choi)\n" +
                    "53 gram wortel\n" +
                    "2 siung bawang putih\n" +
                    "59 gram bawang merah\n" +
                    "40 g saus topokki hot spicy",
            cookingSteps = "Rebus kubis\n" +
                    "wortel\n" +
                    "bw merah\n" +
                    "bw putih selama 15 menit.\n" +
                    "Tempatkan beras merah kukus dalam mangkuk.\n" +
                    "Susun bahan yg direbus tdi di atas nasi merah.\n" +
                    "Orak arik telur\n" +
                    "lalu letakan di atas.\n" +
                    "Tambahkan saus gocujhang atau saus tteokbokki.\n" +
                    "Campur semua makanan.\n" +
                    "Siap disajikan.",
            recipePictures = "bibimbap",
            mealType = 2
        ),
        Recipe(
            recipeId = 171,
            name = "Sesame Toowoomba Pasta",
            calories = 588.0,
            carbs = 57.9,
            fat = 30.6,
            protein = 11.8,
            ingredients = "1 siung bawang putih dimasak\n" +
                    "20 gram bawang bombay dimasak\n" +
                    "60 g shin ramyun\n" +
                    "200 ml susu uht full cream (250ml)\n" +
                    "3 sdm roasted sesame dressing\n" +
                    "10 g smoked beef",
            cookingSteps = "Rebus Shin Ramyun atau mi instan lainnya hingga setengah matang.\n" +
                    "Tumis bawang putih yang sudah dicincang halus hingga harum.\n" +
                    "Masukkan daging lembu salai (secukup rasa) dan tumis dengan bawang.\n" +
                    "Masukkan 200ml susu UHT ke tumisan.\n" +
                    "Jika susu mulai mendidih masukan bumbu mi instan dan 3 sdm sesame dressing.\n" +
                    "Masukan mie dan tunggu hingga susu agak meresap.\n" +
                    "Jika susu mulai mengental\n" +
                    "matikan api dan Sesame Toowoomba Pasta sudah siap disajikan.",
            recipePictures = "sesame_toowoomba_pasta",
            mealType = 2
        ),
        Recipe(
            recipeId = 172,
            name = "Ayam Saos Mentega",
            calories = 597.0,
            carbs = 71.6,
            fat = 11.7,
            protein = 62.5,
            ingredients = "200 gram daging dada ayam (ayam pedaging\n" +
                    "dipanggang\n" +
                    "dimasak)\n" +
                    "2 sdm madu\n" +
                    "30 ml madu\n" +
                    "5 ml minyak jagung\n" +
                    "25 g saus tiram",
            cookingSteps = "Siapkan 200 gr ayam cuci bersih\n" +
                    "potong sesuai selera.\n" +
                    "Marinasi dengan 2 bawang puth halus\n" +
                    "penyedam\n" +
                    "lada\n" +
                    "garam\n" +
                    "saus tiram 2 sdm\n" +
                    "madu 3 sdm\n" +
                    "tepung maizena 1 sdm.\n" +
                    "Simpan dalam kulkas selama minimal 15 menit.\n" +
                    "Siapkan bumbu tumis nya\n" +
                    "bawang bombay\n" +
                    "minyak tropicana 1sdt\n" +
                    ".\n" +
                    "Tumis bumbu tumis lalu masukan bumbu marinasi.",
            recipePictures = "ayam_saos_mentega",
            mealType = 2
        ),
        Recipe(
            recipeId = 175,
            name = "Mapo Tofu",
            calories = 261.0,
            carbs = 12.6,
            fat = 16.3,
            protein = 18.2,
            ingredients = "1 sdm soy sauce\n" +
                    "2 tbsps olive oil\n" +
                    "4 siung garlic\n" +
                    "1/2 sedang onions\n" +
                    "200 gram tempeh (cooked)\n" +
                    "100 g jamur enoki\n" +
                    "65 g bakso daging sapi\n" +
                    "2 sdm gochujang\n" +
                    "200 g tahu original",
            cookingSteps = "Tumis bawang putih dan bawang bombay.\n" +
                    "Masukan tempe dan bakso.\n" +
                    "Masukan gochujang dan kecap.\n" +
                    "Tambahkan air dan garam\n" +
                    "tunggu sampai mendidih dan reduced.\n" +
                    "Masukan tahu\n" +
                    "masak kurang lebih 2 menit.",
            recipePictures = "mapo_tofu",
            mealType = 2
        ),
        Recipe(
            recipeId = 178,
            name = "Mie Shirataki Soto",
            calories = 307.0,
            carbs = 34.3,
            fat = 12.7,
            protein = 15.3,
            ingredients = "32 gram jeruk nipis\n" +
                    "10 gram paprika merah manis\n" +
                    "22 gram cabai merah atau rawit\n" +
                    "39 gram daging dada ayam (ayam pedaging)\n" +
                    "13 gram bawang putih\n" +
                    "39 gram sawi putih\n" +
                    "8 gram bawang merah\n" +
                    "80 g mie basah matang\n" +
                    "2 g royco ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "15 g bumbu indomie goreng\n" +
                    "15 g kerupuk seblak",
            cookingSteps = "Haluskan bawang putih\n" +
                    "merah\n" +
                    "cabai dan tambahkan garam serta lada.\n" +
                    "Masak ayam dipanggang kasih bumbu bawang putih\n" +
                    "merica dan ohaiong. Lalu masak telur gausah pake minyak pake telfon aja.\n" +
                    "Tambahkan bumbu halus dan tambahkan air jeruk nipis lalu tambahkan sayuran\n" +
                    "shirataki\n" +
                    "dan paprika. Kemudian tambahkan bumbu Soto Indomie secukupnya. Menyajikan.",
            recipePictures = "mie_shirataki_soto",
            mealType = 2
        ),
        Recipe(
            recipeId = 180,
            name = "Japchae Shirataki",
            calories = 56.0,
            carbs = 10.5,
            fat = 1.4,
            protein = 1.6,
            ingredients = "1 sedang paprika merah manis\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdt garam\n" +
                    "40 gram wortel\n" +
                    "1 mangkok\n" +
                    "dicincang daun bawang (loncang)\n" +
                    "1 sdt gula pasir\n" +
                    "1 sdt bawang putih cincang\n" +
                    "1/2 sdm kecap manis\n" +
                    "200 g mie basah matang\n" +
                    "10 g saus tiram\n" +
                    "20 gram jamur kancing\n" +
                    "10 ml pengganti kecap asin\n" +
                    "5\n" +
                    "50 ml minyak goreng",
            cookingSteps = "Rebua mie shirataki sampe matang.\n" +
                    "Tumis jamur dan wortel hingga masak\n" +
                    "tambahkan garam.\n" +
                    "Masukan mie yang sudah matang\n" +
                    "aduk hingga tercampur.\n" +
                    "Masukan saus yang sudah dibuat. Masak hingga saus surut.\n" +
                    "Setelah surut\n" +
                    "tambahkan paprika dan daun bawang. Aduk hingga tercampur rata.\n" +
                    "Mie siap di sajikan.",
            recipePictures = "japchae_shirataki",
            mealType = 2
        ),
        Recipe(
            recipeId = 185,
            name = "Cah Kangkung Ala",
            calories = 260.0,
            carbs = 21.2,
            fat = 16.2,
            protein = 10.0,
            ingredients = "1 besar telur\n" +
                    "5 gram cabai merah atau rawit\n" +
                    "2 siung bawang putih\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "59 gram kangkung",
            cookingSteps = "Panaskan minyak\n" +
                    "masukkan bamer baput.\n" +
                    "Tunggu hingga harum\n" +
                    "masukkan kangkung dan cabe rawit.\n" +
                    "Tambahkan sedikit air.\n" +
                    "Tumis kangkung hingga masak dan tidak layu.",
            recipePictures = "cah_kangkung_ala",
            mealType = 2
        ),
        Recipe(
            recipeId = 187,
            name = "Tahu Gejrot Cirebon",
            calories = 200.0,
            carbs = 25.5,
            fat = 8.9,
            protein = 7.2,
            ingredients = "6 buah tahu goreng\n" +
                    "3 sdt\n" +
                    "bongkahan gula merah\n" +
                    "2 sdm kecap manis\n" +
                    "20 g sambal pedas uleg",
            cookingSteps = "Tahu dipotong2 taruh di atas piring.\n" +
                    "Ulek cabe\n" +
                    "bawang merah\n" +
                    "bawang putih\n" +
                    "garam.\n" +
                    "Didihkan air masukan gula jawa dan kecap sampai mendidih.\n" +
                    "Siram tahu dengan larutan gula dan cabe ulek ke atas piring yg berisi tahu.",
            recipePictures = "tahu_gejrot_cirebon",
            mealType = 2
        ),
        Recipe(
            recipeId = 190,
            name = "Tumis Shau",
            calories = 177.0,
            carbs = 27.1,
            fat = 7.2,
            protein = 3.0,
            ingredients = "30 gram broccoli\n" +
                    "75 gram carrots\n" +
                    "1/2 tbsp sesame oil\n" +
                    "15 gram green snap beans\n" +
                    "1 sdm soy sauce (shoyu)\n" +
                    "1/2 sdm honey\n" +
                    "15 g saus tiram\n" +
                    "10 ml kecap pedas",
            cookingSteps = "Cuci dang potong semua sayuran.\n" +
                    "Tumis bawang putih dengan minyak wijen.\n" +
                    "Masukan semua sayuran dan bumbu lalu tambahkan air.\n" +
                    "Siap disajikan.",
            recipePictures = "tumis_shau",
            mealType = 2
        ),
        Recipe(
            recipeId = 199,
            name = "Rolade Sehat",
            calories = 24.0,
            carbs = 1.4,
            fat = 1.2,
            protein = 1.8,
            ingredients = "100 gram dada ayam\n" +
                    "4 besar telur\n" +
                    "1 sdt minyak wijen\n" +
                    "200 gram wortel\n" +
                    "1/2 sdm minyak goreng\n" +
                    "130 gram tahu\n" +
                    "50 ml susu uht full cream\n" +
                    "40 g rolled oat\n" +
                    "15 ml kecap asin",
            cookingSteps = "Campur semua bahan dengan chopper\n" +
                    "kecuali 3 telur dan susu. Beri penyedap rasa\n" +
                    "campur sampai rata.\n" +
                    "Kocok 3 telur dengan susu\n" +
                    "dadar di teflon anti lengket.\n" +
                    "Lalu di bentuk seperti rolade dan kukus. Sudah siap disajikan.",
            recipePictures = "rolade_sehat",
            mealType = 2
        ),
        Recipe(
            recipeId = 200,
            name = "Sate Mix Ayam dan Jamur",
            calories = 446.0,
            carbs = 55.4,
            fat = 9.6,
            protein = 37.3,
            ingredients = "100 gram dada ayam\n" +
                    "30 gram ketumbar\n" +
                    "5 gram cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "3 siung bawang putih\n" +
                    "100 gram jamur tiram\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm kecap manis\n" +
                    "50 ml air mineral\n" +
                    "100 g saus tiram\n" +
                    "3 g lada putih bubuk",
            cookingSteps = "Rebus jamur tiram\n" +
                    "setelah itu tiriskan dan suwir sesuai dengan ukuran yang diinginkan.\n" +
                    "Belender bumbu untuk dipanggang setelah itu di tumis.\n" +
                    "Marinasi jamur dan dada ayam selama 30 menit.\n" +
                    "Tusuk jamur tiram dan dada ayam yg sudah di marinasikan pada tusuk sate.\n" +
                    "Panggang sate jamur dada ayam di teflon anti lengket atau panggangan anti lengket.",
            recipePictures = "sate_mix_ayam_dan_jamur",
            mealType = 2
        ),
        Recipe(
            recipeId = 205,
            name = "Ayam Lada Hitam",
            calories = 362.0,
            carbs = 20.2,
            fat = 15.8,
            protein = 36.3,
            ingredients = "230 gram chicken breast\n" +
                    "1 gram young green onions\n" +
                    "30 gram paprika\n" +
                    "16 gram onions\n" +
                    "1 sdm minyak goreng\n" +
                    "3 g lada putih bubuk\n" +
                    "60 g saus teriyaki lada hitam",
            cookingSteps = "Panfry ayam.\n" +
                    "Tumis bahan kering.\n" +
                    "Masukkan bumbu.",
            recipePictures = "ayam_lada_hitam",
            mealType = 2
        ),
        Recipe(
            recipeId = 207,
            name = "NasGor Receh",
            calories = 140.0,
            carbs = 22.4,
            fat = 4.1,
            protein = 5.2,
            ingredients = "50 gram nasi putih\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "50 g mie basah matang\n" +
                    "25 g sosis ayam kombinasi\n" +
                    "Cara memasak",
            cookingSteps = "Tumis bawang putih sampai harum.\n" +
                    "Masukkan cabai yang telah di haluskan.\n" +
                    "Tambahkan sosis dan mie shirataki.\n" +
                    "Tambahkan nasi tunggu sampai di rasa sudah cukup matang.\n" +
                    "Jika suka bisa di tambahkan telur ceplok\n" +
                    "telur rebus atau bakso.",
            recipePictures = "nasgor_receh",
            mealType = 2
        ),
        Recipe(
            recipeId = 217,
            name = "Tongseng Bango dan Fiber Chreme",
            calories = 360.0,
            carbs = 34.1,
            fat = 10.7,
            protein = 36.4,
            ingredients = "145 gram dada ayam (kulit tidak dimakan)\n" +
                    "120 gram tomat\n" +
                    "15 gram cabai merah atau rawit\n" +
                    "300 gram kubis\n" +
                    "20 gram daun bawang\n" +
                    "20 gram sereh\n" +
                    "95 gram daging sapi (95% tanpa lemak / 5% lemak)\n" +
                    "1 sdm pengganti kecap manis\n" +
                    "5 sdt fiber creme\n" +
                    "26 g tongseng khas solo",
            cookingSteps = "Tumis daging dan bumbu bango.\n" +
                    "Masukan air fiber chreme dan bumbu lainnya sampai menyusut.\n" +
                    "Masukan kubis dan cabai.\n" +
                    "Matikan api kemudian masukan faun bawang dan potongan tomat.",
            recipePictures = "tongseng_bango_dan_fiber_chreme",
            mealType = 2
        ),
        Recipe(
            recipeId = 227,
            name = "Bakso di Soup",
            calories = 242.0,
            carbs = 10.0,
            fat = 13.6,
            protein = 20.6,
            ingredients = "200 gram daging sapi giling\n" +
                    "100 gram kecambah\n" +
                    "5 gram kaldu jamur\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "1 sejumput lada hitam\n" +
                    "10 gram cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "20 gram daun bawang\n" +
                    "300 gram tahu kukus\n" +
                    "100 gram pakcoy\n" +
                    "50 g tepung tapioka\n" +
                    "40 gram bawang bombay",
            cookingSteps = "Buat adonan bakso dengan cara campurkan tapioka\n" +
                    "daging sapi giling\n" +
                    "bombay dan bubuk bawang putih serta lada garam. Aduk hingga rata. Dan bulatkan dengan tangan satu per satu. Masukan satu per satu ke dalam air mendidih.\n" +
                    "Cincang bawang putih dan daun bawang. Masukan ke dalam kuah. Serta lada garam dan kaldu jamur.\n" +
                    "Rebus sayur pokcoy dan toge dalam panci terpisah.\n" +
                    "Tunggu hingga bakso matang dan mengapung ke atas. Masukan tahu. Tunggu sampai smua matang. Sajikan bersama toge dan pokcoy rebus sera tambahkan cabai rawit jika suka.",
            recipePictures = "capcay",
            mealType = 2
        ),
        Recipe(
            recipeId = 232,
            name = "Cauliflower Nasi Goreng",
            calories = 184.0,
            carbs = 23.7,
            fat = 6.6,
            protein = 11.4,
            ingredients = "1 irisan tebal chicken breast\n" +
                    "1 besar egg\n" +
                    "1 mangkok chinese cabbage\n" +
                    "3 cups cauliflower\n" +
                    "1 sdt olive oil\n" +
                    "1 mangkok\n" +
                    "irisan red onions\n" +
                    "3 sdt minced garlic\n" +
                    "4 1/2 sdt chili\n" +
                    "10 ml kecap inggris\n" +
                    "1 sdm kecap manis\n" +
                    "3 g kaldu rasa jamur",
            cookingSteps = "Sangrai di wajan kembang kol sampai berubah warna dan agak kering\n" +
                    "lalu sisihkan.\n" +
                    "Tumis pakai olive oil bawang merah dan bawang putih sampi berubah warna dan harum lalu masukan cabai\n" +
                    "ayam\n" +
                    "tambahkan air masak sampai ayam matang sempurna lalu masukan telor aduk aduk sampai matang dan tambah kan air sedikit\n" +
                    "kecap\n" +
                    "kecap asin\n" +
                    "kaldu jamur dll masak sampai semua bumbu meresap.\n" +
                    "Masukan kembanh kol\n" +
                    "aduk aduk dengan semua bahan diatas\n" +
                    "tambahkan lagi sedikit air dengan tujuan supaya semua rasa meresap kedalam kembang kol\n" +
                    "masak sampai kembanh kok agak berubah warna dan rasa tekstur sudah mirip seperti nasi goreng.",
            recipePictures = "cauliflower_nasi_goreng",
            mealType = 2
        ),
        Recipe(
            recipeId = 234,
            name = "Chicken Tandoori",
            calories = 218.0,
            carbs = 9.1,
            fat = 12.8,
            protein = 17.4,
            ingredients = "2 kecil chicken thigh\n" +
                    "1 tsp coriander seed\n" +
                    "2 sdt cumin\n" +
                    "1 tbsp fenugreek seed\n" +
                    "1 tsp paprika\n" +
                    "1 sejumput black pepper\n" +
                    "2 sdt turmeric (ground)\n" +
                    "1 sejumput salt\n" +
                    "1 sdt ginger\n" +
                    "1 sdt minced garlic\n" +
                    "50 g greek yogurt",
            cookingSteps = "Bersihkan ayam. Boleh pakai kulit boleh tidak. Bumbui ayam dengan lada dan garam.\n" +
                    "Untuk bumbu marinasi\n" +
                    "pertama-tama masukkan greek yoghurt ke mangkuk\n" +
                    "campur dengan bumbu bubuk ketumbar\n" +
                    "jinten\n" +
                    "kunyit\n" +
                    "paprika\n" +
                    "garam menyesuaikan.\n" +
                    "Haluskan 2 butir bawang putih dengan 1 ruas jahe. Kalau suka pedas bisa tambahkan cabe\n" +
                    "tapi sebisa mungkin gunakan cabe kering yang sudah jadi bubuk. Tambahkan bumbu halus ke mangkuk dan aduk hingga merata bersama dengan yoghurt dan bumbu bubuk lainnya.\n" +
                    "Buat guratan di daging ayam hingga menyentuh tulang.\n" +
                    "Lumurkan bumbu marinasi ke ayam hingga merata. Biarkan dahulu di dalam kulkas setidaknya 6 jam atau diamkan semalaman. Fungsinya untuk melunakkan daging ayam dan agar bumbunya meresap.\n" +
                    "Setelah didiamkan\n" +
                    "keluarkan ayam dari kulkas. Panaskan wajan (boleh pakai minyak 1 sdm).\n" +
                    "Masukkan ayam ke wajan dengan kulit menghadap bawah. Bolak balik ayam hingga matang\n" +
                    "sekitar 7 menit sambil sedikit-sedikit dilumuri lagi dengan sisa bumbu marinasi.",
            recipePictures = "chicken_tandoori",
            mealType = 2
        ),
        Recipe(
            recipeId = 237,
            name = "Sayur Pokcoy Tahu Telur",
            calories = 194.0,
            carbs = 15.4,
            fat = 11.9,
            protein = 8.7,
            ingredients = "1 sdm minyak kelapa\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "1/4 gelas air\n" +
                    "2 sedang bawang merah\n" +
                    "100 gram tahu\n" +
                    "100 gram pakcoy\n" +
                    "3 g lada putih bubuk\n" +
                    "1\n" +
                    "5 g kaldu rasa jamur",
            cookingSteps = "Di atas wajan\n" +
                    "tambahkan minyak kelapa\n" +
                    "tumis bawang merah dan bawang putih hingga kecoklatan.\n" +
                    "Masukkan telur\n" +
                    "diorak arik lalu masukkan tahu\n" +
                    "aduk aduk selama 5 menit lalu masukkan air putih. Diamkan hingga tahu matang.\n" +
                    "Masukkan pakcoy\n" +
                    "garam\n" +
                    "lada bubuk dan kaldu jamur lalu di aduk aduk hingga pakcoy agak layu dan bumbu tercampur rata.\n" +
                    "Siap disajikan.",
            recipePictures = "sayur_pokcoy_tahu_telur",
            mealType = 2
        ),
        Recipe(
            recipeId = 238,
            name = "Bakmi Goreng Shirataki",
            calories = 140.0,
            carbs = 23.8,
            fat = 2.8,
            protein = 8.9,
            ingredients = "25 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "1/4 mangkok\n" +
                    "segar\n" +
                    "potongan batang brokoli dimasak (dari segar)\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "25 g mie kering\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm kecap manis",
            cookingSteps = "Rebus mie\n" +
                    "brokoli\n" +
                    "dada ayam.\n" +
                    "Bawang putih dan cabe cincang.\n" +
                    "Tumis baput cabe lada dengan air.\n" +
                    "Masuk mie brokoli dada ayam.\n" +
                    "Koreksi rasa dg tambahkan himsalt dan kecap.\n" +
                    "2 anggota telah menambahkan resep ini ke buku",
            recipePictures = "bakmi_goreng_shirataki",
            mealType = 2
        ),
        Recipe(
            recipeId = 243,
            name = "Nasi Goreng China",
            calories = 450.0,
            carbs = 67.2,
            fat = 14.3,
            protein = 14.0,
            ingredients = "1 besar telur\n" +
                    "1 sdm kecap asin (kedelai)\n" +
                    "2 mangkok\n" +
                    "dimasak nasi putih\n" +
                    "1 sdm margarin\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdt garam\n" +
                    "1 sdm minyak wijen\n" +
                    "30 gram wortel\n" +
                    "2 siung bawang putih\n" +
                    "3 sedang bawang merah\n" +
                    "20 g sayur toge\n" +
                    "20 g saus tiram\n" +
                    "3 g lada putih bubuk\n" +
                    "100 gram sawi hijau",
            cookingSteps = "Panaskan margarin\n" +
                    "tumis bawang hingga harum kemudian masukan telur dan sayuran.\n" +
                    "Masukan nasi putih\n" +
                    "aduk2 jangan lupa beri kecap asin\n" +
                    "minyak wijen\n" +
                    "saos tiram dan garam(opstional ya kalo kurang asin) dan lada.\n" +
                    "Aduk hingga tercampur rata. Nikmati.",
            recipePictures = "nasi_goreng_china",
            mealType = 2
        ),
        Recipe(
            recipeId = 244,
            name = "Tumis Wortel Buncis",
            calories = 295.0,
            carbs = 53.6,
            fat = 5.8,
            protein = 11.0,
            ingredients = "4 telur puyuh\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "44 gram buncis\n" +
                    "68 gram wortel\n" +
                    "3 siung bawang putih\n" +
                    "50 gram nasi merah (butir-sedang\n" +
                    "dimasak)\n" +
                    "2 sedang bawang merah\n" +
                    "35 g saus tiram",
            cookingSteps = "Cuci lalu iris wortel dan buncis.\n" +
                    "Iris bawang merah\n" +
                    "bawang putih\n" +
                    "serta cabai lalu tumis dg air secukupnya.\n" +
                    "Masukkan wortel dan buncis serta tambahkan sedikit merica bubuk dan saos tiram.\n" +
                    "Pindahkan ke piring dengan nasi.",
            recipePictures = "tumis_wortel_buncis",
            mealType = 2
        ),
        Recipe(
            recipeId = 245,
            name = "Tumis Ayam Jamur Tempe",
            calories = 662.0,
            carbs = 62.9,
            fat = 24.5,
            protein = 56.1,
            ingredients = "1/2 kecil chicken breast\n" +
                    "3 kecil cooked mushrooms (fat added in cooking)\n" +
                    "1 medium onion\n" +
                    "2 green hot chili peppers\n" +
                    "1 cup pieces mushrooms (with salt\n" +
                    "drained\n" +
                    "cooked\n" +
                    "boiled)\n" +
                    "100 gram tempeh (cooked)\n" +
                    "1 small red onion\n" +
                    "6 tbsps chili\n" +
                    "100 g saus tiram",
            cookingSteps = "Tumis bawang dan cabe (tanpa minyak).\n" +
                    "Masukkan ayam dan tempe. Masak sampai matang.\n" +
                    "Masukkan jamur + beri sedikit air.\n" +
                    "Beri saori saus tiram\n" +
                    "dan koreksi rasa.",
            recipePictures = "tumis_ayam_jamur_tempe",
            mealType = 2
        ),
        Recipe(
            recipeId = 246,
            name = "Ayam Suwir Balado",
            calories = 708.0,
            carbs = 112.9,
            fat = 18.2,
            protein = 43.1,
            ingredients = "100 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "1 sdm kecap asin (kedelai)\n" +
                    "100 gram nasi merah\n" +
                    "5 daun sedang selada\n" +
                    "15 gram kaldu jamur\n" +
                    "7 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "20 gram kubis\n" +
                    "20 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "1 utuh sedang tomat merah\n" +
                    "10 gram kacang kemiri\n" +
                    "5 kecil bawang merah\n" +
                    "20 ml gula jawa",
            cookingSteps = "Haluskan bawang merah\n" +
                    "bawang putih\n" +
                    "cabai\n" +
                    "kemiri\n" +
                    "tomat.\n" +
                    "Suwir-suwir ayam rebus dengan garpu / tangan.\n" +
                    "Tumis bumbu halus diatas teflon anti lengket. Sampai harum. Tambahkan garam.\n" +
                    "Masukkan ayam suwir kedalam tumisan bumbu\n" +
                    "tambahkan kaldu jamur\n" +
                    "kecap asin\n" +
                    "gula jawa.\n" +
                    "Aduk hingga rata. Tes rasa. Siap disajikan.",
            recipePictures = "ayam_suwir_balado",
            mealType = 2
        ),
        Recipe(
            recipeId = 250,
            name = "Capcay Ayam Kuah tanpa Minyak",
            calories = 91.0,
            carbs = 5.5,
            fat = 1.6,
            protein = 13.7,
            ingredients = "80 gram dada ayam (kulit tidak dimakan)\n" +
                    "10 gram tomat\n" +
                    "1 sdt garam\n" +
                    "1/2 sdm saus tiram\n" +
                    "40 gram wortel\n" +
                    "40 gram kembang kol\n" +
                    "1 siung bawang putih\n" +
                    "40 gram sawi putih\n" +
                    "10 gram bawang bombay\n" +
                    "40 gram sawi hijau\n" +
                    "1\n" +
                    "5 g kaldu rasa jamur",
            cookingSteps = "Cuci sayuran dan ayam.\n" +
                    "Geprak bawang putih hingga halus.\n" +
                    "Tumis bawang putih dan bombay dan wortel di teflon tanpa minyak.\n" +
                    "Masukan air secukupnya\n" +
                    ".\n" +
                    "Jika sudah mendidih masukan bumbu sesuai selera (garam\n" +
                    "saus tiram\n" +
                    "kaldu jamur).\n" +
                    "Masukan ayam masak hingga matang\n" +
                    ".\n" +
                    "Masukan sayuran.\n" +
                    "Koreksi rasa\n" +
                    "selesai.",
            recipePictures = "capcay_ayam_kuah_tanpa_minyak",
            mealType = 2
        ),
        Recipe(
            recipeId = 253,
            name = "Kekian Udang",
            calories = 46.0,
            carbs = 1.9,
            fat = 1.1,
            protein = 7.2,
            ingredients = "640 gram udang\n" +
                    "1 sedang telur\n" +
                    "123 gram wortel\n" +
                    "21 gram bawang putih\n" +
                    "30 gram daun bawang (loncang)\n" +
                    "60 g tepung tapioka\n" +
                    "75 g kembang tahu",
            cookingSteps = "Haluskan udang.\n" +
                    "Haluskan bawang putih garam dan merica.\n" +
                    "Parut wortel dan iris kecil-kecil daun bawang.\n" +
                    "Masukkan wortel daun bawang telur dan bumbu halus kedalam udang yang telah dihaluskan.\n" +
                    "Aduk sampai rata kemudian masukkan tepung tapioka.\n" +
                    "Taruh di dalam kulit tahu yang telah di rendam air.\n" +
                    "Kukus kurang lebih 10 menit.\n" +
                    "5 anggota telah menambahkan resep",
            recipePictures = "kekian_udang",
            mealType = 2
        ),
        Recipe(
            recipeId = 254,
            name = "Cah Sayur",
            calories = 240.0,
            carbs = 36.4,
            fat = 1.5,
            protein = 20.9,
            ingredients = "1 sdt garam\n" +
                    "71 gram daging dada ayam (ayam pedaging)\n" +
                    "50 gram buncis\n" +
                    "25 gram brokoli\n" +
                    "24 gram wortel\n" +
                    "2 gelas air\n" +
                    "24 gram jagung kecil/tunas jagung\n" +
                    "15 g saus bumbu rasa tiram\n" +
                    "100 gram kentang rebus\n" +
                    "15 gram sawi hijau\n" +
                    "1.5 g kaldu rasa jamur",
            cookingSteps = "Tumis bawput dan cabe.\n" +
                    "Masukan air\n" +
                    "ayam dan kentang.\n" +
                    "Campur semua bahan jadi.\n" +
                    "Tambahkan bumbu perasa.\n" +
                    "Sajikan selagi hangat.",
            recipePictures = "cah_sayur",
            mealType = 2
        ),
        Recipe(
            recipeId = 255,
            name = "Spaghetti Aglio Olio Bandeng tanpa Minyak",
            calories = 462.0,
            carbs = 65.7,
            fat = 9.1,
            protein = 31.1,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "30 gram brokoli\n" +
                    "1 utuh sedang tomat merah\n" +
                    "100 gram ikan bandeng\n" +
                    "1 kecil bawang merah\n" +
                    "60 g spaghetti\n" +
                    "40 gram bawang bombay\n" +
                    "1\n" +
                    "5 g kaldu rasa jamur",
            cookingSteps = "Rebus spaghetti wholemeal selama 10 menit.\n" +
                    "Haluskan tomat dan bawang merah bisa di uleg atau di blender beri sedikit air.\n" +
                    "Panaskan telflon anti lengket\n" +
                    "masukan bawang bombai sangrai sampai harum (tanpa minyak).\n" +
                    "Setelah itu masukan bumbu yg sudah di haluskan.\n" +
                    "Tambahkan bandeng suwir\n" +
                    "irisan cabai\n" +
                    "brokoli\n" +
                    "garam\n" +
                    "dan kaldu jamur.\n" +
                    "Kemudian masukan spaghetti wholemeal yg sudah di rebus dan beri sedikit seledri\n" +
                    "aduk sampai rata.",
            recipePictures = "spaghetti_aglio_olio_bandeng_tanpa_minyak",
            mealType = 2
        ),
        Recipe(
            recipeId = 256,
            name = "Cah Brokoli Ayam",
            calories = 183.0,
            carbs = 4.8,
            fat = 8.6,
            protein = 20.9,
            ingredients = "200 gram dada ayam\n" +
                    "1 sdt garam\n" +
                    "1 sdm saus tiram\n" +
                    "100 gram brokoli\n" +
                    "20 gram wortel\n" +
                    "2 siung bawang putih\n" +
                    "150 ml air\n" +
                    "1 sdm minyak goreng\n" +
                    "3 g lada putih bubuk",
            cookingSteps = "Haluskan bawang putih dan lada.\n" +
                    "Panaskan minyak.\n" +
                    "Tumis bumbu sampai harum.\n" +
                    "Masukkan ayam tumis sampai matang.\n" +
                    "Tambahkan air matang secukupnya.\n" +
                    "Masukkan brokoli.\n" +
                    "Tambahkan garam.\n" +
                    "Pindahkan ke piring saat sudah matang.",
            recipePictures = "cah_brokoli_ayam",
            mealType = 2
        ),
        Recipe(
            recipeId = 257,
            name = "Nasi Goreng Tapi Bohong",
            calories = 291.0,
            carbs = 40.0,
            fat = 12.3,
            protein = 6.8,
            ingredients = "25 gram kecambah\n" +
                    "25 gram buncis\n" +
                    "25 gram wortel\n" +
                    "2 siung bawang putih\n" +
                    "50 ml air\n" +
                    "1/2 sdt garam laut\n" +
                    "1 kecil bawang merah\n" +
                    "35 g quick cooking oatmeal\n" +
                    "15 g saus tiram\n" +
                    "2 g royco ayam\n" +
                    "1 sendok makan minyak goreng",
            cookingSteps = "Sangrai oatmeal sampai berubah warna kecoklatan.\n" +
                    "Tambahkan air sedikit demi sedikit\n" +
                    "sisihkan.\n" +
                    "Tumis bawang bombay dan bawang putih hingga harum\n" +
                    "lalu tambahkan tauge wortel\n" +
                    "tumis hingga layu. Tambahkan garam\n" +
                    "penyedap rasa & saus tiram.\n" +
                    "Masukkan oatmeal yg sudah disangrai tadi.\n" +
                    "Tumis sebentar hingga tercampur rata. Test rasa\n" +
                    "sajikan",
            recipePictures = "nasi_goreng_tapi_bohong",
            mealType = 2
        ),
        Recipe(
            recipeId = 259,
            name = "Steak Tempe",
            calories = 135.0,
            carbs = 29.5,
            fat = 4.0,
            protein = 6.2,
            ingredients = "1 mangkok\n" +
                    "irisan cooked carrots\n" +
                    "2 sdm tomato catsup\n" +
                    "2 sdm salt\n" +
                    "1 mangkok chayote (fruit)\n" +
                    "4 siung garlic\n" +
                    "1 mangkok\n" +
                    "dicincang onions\n" +
                    "30 ml bottled water\n" +
                    "100 gram tempeh (cooked)\n" +
                    "1 sdm saus sambal\n" +
                    "100 g saus tiram\n" +
                    "3 g lada putih bubuk\n" +
                    "10 g tepung maizena",
            cookingSteps = "Potong tempe\n" +
                    "lalu kukus hingga matang.\n" +
                    "Haluskan bawang putih hingga halus lalu tambahkan tempe kukus lalu haluskan. Tambahkan tepung terigu\n" +
                    "garam dan merica\n" +
                    "aduk hingga merata\n" +
                    "lalu bentuk adonan bulat pipih.\n" +
                    "Panaskan pan\n" +
                    "tambahkan margarin dan minyak zaitun\n" +
                    "lalu grill tempe sampai matang\n" +
                    "jgn sering2 dibolak balik agar tdk hancur\n" +
                    "masak jg pinggirannya.\n" +
                    "Saus: lelehkan margarin\n" +
                    "lalu tumis bawang bombay sampai layu kmudian masukkan bawang putih tumis sampai harum\n" +
                    "lalu masukkan semua bahan saus dan lada\n" +
                    "lalu tambahkan air\n" +
                    "cek rasa\n" +
                    "terakhir masukkan larutan maizena\n" +
                    "aduk2 hingga mengental\n" +
                    "sajikan.",
            recipePictures = "steak_tempe",
            mealType = 2
        ),
        Recipe(
            recipeId = 260,
            name = "Shirataki Tomato Soup",
            calories = 163.0,
            carbs = 21.7,
            fat = 6.4,
            protein = 6.3,
            ingredients = "2 chicken nugget\n" +
                    "1 sedang telur\n" +
                    "1 mangkok\n" +
                    "segar sawi dimasak (dari segar)\n" +
                    "200 gram tomat\n" +
                    "1 sdt garam\n" +
                    "1 sdm saus teriyaki\n" +
                    "50 gram buncis\n" +
                    "50 gram wortel\n" +
                    "5 siung bawang putih\n" +
                    "5 iris daun bawang\n" +
                    "3 gelas air\n" +
                    "1/2 sdm minyak goreng\n" +
                    "1 sdm kecap manis\n" +
                    "200 g mie basah matang\n" +
                    "2 g royco ayam",
            cookingSteps = "Panaskan minyak ½ sendok masukkan nugget yg dipotong dadu.\n" +
                    "Masukkan batang sawi\n" +
                    "wortel dan buncis tambahkan air 250ml.\n" +
                    "Sisihkan tumisan ke pinggir wajan lalu masukkan telur\n" +
                    "beri garam dan orak arik bersama sayur.\n" +
                    "Masukkan irisan tomat\n" +
                    "sawi\n" +
                    "tambahkan air 500ml masukkan mie shirataki.\n" +
                    "Tambahkan royko/kaldu ayam\n" +
                    "garam\n" +
                    "irisan cabe\n" +
                    "saus teriyaki dan kecap.\n" +
                    "Diamkan hingga mendidih.\n" +
                    "Ready to serving.",
            recipePictures = "shirataki_tomato_soup",
            mealType = 2
        ),
        Recipe(
            recipeId = 261,
            name = "Capcay",
            calories = 363.0,
            carbs = 47.5,
            fat = 12.4,
            protein = 17.1,
            ingredients = "1 besar telur\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 mangkok\n" +
                    "dicincang sawi\n" +
                    "3 siung bawang putih\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm kecap manis\n" +
                    "50 g sosis sapi\n" +
                    "9 g saus sambal",
            cookingSteps = "Tumis bawang putih\n" +
                    "bawang merah\n" +
                    "dan cabai hingga harum.\n" +
                    "Tuang air secukupnya tunggu sampai mendidih.\n" +
                    "Masukkan sawi\n" +
                    "sosis\n" +
                    "dan telur.\n" +
                    "Tambahkan garam\n" +
                    "lada\n" +
                    "kecap secukupnya.\n" +
                    "Tunggu hingga matang.",
            recipePictures = "capcay",
            mealType = 2
        ),
        Recipe(
            recipeId = 262,
            name = "Sohun Goreng",
            calories = 392.0,
            carbs = 44.6,
            fat = 13.1,
            protein = 27.3,
            ingredients = "100 gram dada ayam\n" +
                    "120 gram tomat\n" +
                    "240 gram kecambah\n" +
                    "80 gram sawi\n" +
                    "110 gram mentimun (dengan kulit)\n" +
                    "18 gram bawang putih\n" +
                    "50 gram sohun\n" +
                    "60 gram tempe\n" +
                    "40 gram bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "10 g bumbu racik nasi goreng",
            cookingSteps = "Rendam sohun dngn air panas 5 menit kemudian tiriskan.\n" +
                    "Iris bawang merah dan bawang putih. Kemudian potong dadu dada ayam dan tempe.\n" +
                    "Tumis bawang merah dan bawang putih sebentar. Kemudian masukkan dada ayam dan tempe.\n" +
                    "Masukkan toge dan sawi aduk2. Jngan lupa bumbu nasi goreng instannya ya Terakhir masukkan sohun nya tumis hingga matang.\n" +
                    "Sajikan dengan irisan mentimun dan tomat.",
            recipePictures = "sohun_goreng",
            mealType = 2
        ),
        Recipe(
            recipeId = 264,
            name = "Tumis Jamur Kacang Panjang",
            calories = 91.0,
            carbs = 19.1,
            fat = 1.8,
            protein = 3.4,
            ingredients = "100 gram kacang panjang hijau\n" +
                    "10 gram kaldu jamur\n" +
                    "5 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm kecap manis\n" +
                    "100 g saus tiram\n" +
                    "50 gram bawang bombay\n" +
                    "100 gram jamur kancing",
            cookingSteps = "Tumis bawang putih\n" +
                    "bawang bombay dan cabai rawit yang sudah di iris tipis.\n" +
                    "Masukan jamur dan kacang panjang yang sudah di potong-potong.\n" +
                    "Tambahkan kaldu jamur\n" +
                    "saos tiram\n" +
                    "garam\n" +
                    "dan kecap sesuai selera.",
            recipePictures = "tumis_jamur_kacang_panjang",
            mealType = 2
        ),
        Recipe(
            recipeId = 265,
            name = "Telur Sutera",
            calories = 195.0,
            carbs = 22.8,
            fat = 6.8,
            protein = 12.8,
            ingredients = "1 besar telur\n" +
                    "50 gram wortel dimasak\n" +
                    "1 besar putih telur\n" +
                    "1 sdm saus tiram\n" +
                    "1 potong jagung rebus\n" +
                    "1 siung bawang putih\n" +
                    "1 gram daun bawang\n" +
                    "10 gram bawang bombay",
            cookingSteps = "Kocok telur dengan irisan daun bawang\n" +
                    "dengan menambahkan 100ml air. Masukkan garam dan merica sesuai selera. Lalu kukus.\n" +
                    "Rebus jagung dan wortel\n" +
                    "tumis bawang bombay dan bawang putih dengan 1 sendok olive oil.\n" +
                    "Masukkan sayuran kedalam tumisan bawang\n" +
                    "lalu tambahkan garam\n" +
                    "merica dan 1 sendok saus tiram.\n" +
                    "Yang terakhir koreksi rasa.\n" +
                    "1 anggota telah menambah",
            recipePictures = "telur_sutera",
            mealType = 2
        ),
        Recipe(
            recipeId = 269,
            name = "Ayam Geprek Low Calori",
            calories = 329.0,
            carbs = 18.1,
            fat = 16.7,
            protein = 26.6,
            ingredients = "137 gram dada ayam\n" +
                    "1 sedang telur\n" +
                    "1 1/2 sdm cabai merah atau rawit\n" +
                    "1 sdm minyak sayur canola\n" +
                    "4 siung bawang putih\n" +
                    "40 g rolled oats",
            cookingSteps = "Iris tipis” dada ayam.\n" +
                    "Baluri telur.\n" +
                    "Blender rolled oat untuk jadi tepung.\n" +
                    "Balurkan ayam dengan tepung rolled oat.\n" +
                    "Goreng ayam pakai minyak canola 1 sdm.\n" +
                    "Uleg cabai dan bawang putih sesuai selera.",
            recipePictures = "ayam_geprek_low_calori",
            mealType = 2
        ),
        Recipe(
            recipeId = 272,
            name = "Oseng Buncis Tempe",
            calories = 212.0,
            carbs = 36.3,
            fat = 6.3,
            protein = 7.9,
            ingredients = "16 gram cabai merah atau rawit\n" +
                    "1/4 sdt garam\n" +
                    "1 sdm saus tiram\n" +
                    "90 gram buncis\n" +
                    "3 siung bawang putih\n" +
                    "3 sedang bawang merah\n" +
                    "62 gram tempe goreng\n" +
                    "1 sdm kecap manis",
            cookingSteps = "Tempe yang sudah digoreng dipotong kotak-kotak\n" +
                    "Buncis potong sesuai selera.\n" +
                    "Tumis bamer\n" +
                    "baput & cabe hingga harum.\n" +
                    "Masukan tempe yang sudah digoreng & buncis.\n" +
                    "Tambahkan kecap\n" +
                    "saus tiram\n" +
                    "garam\n" +
                    "dan air seckupnya. Lalu tunggu hingga matang & air menyusut.",
            recipePictures = "oseng_buncis_tempe",
            mealType = 2
        ),
        Recipe(
            recipeId = 274,
            name = "Nasgor Merah",
            calories = 322.0,
            carbs = 41.1,
            fat = 11.7,
            protein = 15.7,
            ingredients = "78 gram telur\n" +
                    "130 gram nasi merah\n" +
                    "10 gram cabai merah atau rawit\n" +
                    "10 gram bawang putih\n" +
                    "10 gram bawang merah\n" +
                    "1 ml minyak goreng\n" +
                    "40 gram sawi hijau",
            cookingSteps = "Potong bawang cabe lalu tumis masukan telur hingga matang masukan sawi hingga layu masukan nasi dan tambahkan garam.\n" +
                    "Angkat nasgor jika sudah matang.\n" +
                    "Sajikan d piring.",
            recipePictures = "nasgor_merah",
            mealType = 2
        ),
        Recipe(
            recipeId = 275,
            name = "Mentai Shirataki",
            calories = 352.0,
            carbs = 24.3,
            fat = 27.8,
            protein = 2.2,
            ingredients = "3 sdm mayonnaise\n" +
                    "100 g mie kering\n" +
                    "18 ml saus sambal\n" +
                    "64 g crab stick\n" +
                    "3 tbsps sesame oil",
            cookingSteps = "Rebus mie shirataki.\n" +
                    "Campurkan mie shirataki dengan sejumput garam dan minyak wijen.\n" +
                    "Tata di ramekin\n" +
                    "tambahkan crab stick rebus\n" +
                    "dan beri mentai.\n" +
                    "Bakar menggunakan gas torch.",
            recipePictures = "mentai_shirataki",
            mealType = 2
        ),
        Recipe(
            recipeId = 284,
            name = "Pokcoy Siram Jamur Enoki",
            calories = 116.0,
            carbs = 5.7,
            fat = 7.4,
            protein = 8.1,
            ingredients = "80 gram daging babi\n" +
                    "1 sdm margarin\n" +
                    "10 gram kaldu jamur\n" +
                    "1 sejumput lada hitam\n" +
                    "480 gram pakcoy\n" +
                    "80 g jamur enoki\n" +
                    "100 g egg tofu\n" +
                    "1/2 sedang bawang bombay",
            cookingSteps = "Rebus pokcoy d air mendidih yang d tambah garam.\n" +
                    "Lelehkan margarin dan tumus bawang bombay hingga layu.\n" +
                    "Masukkan daging babi dan tumis hingga berubah warna.\n" +
                    "Beri sedikit air bumbui dengan lada hitam dan kaldu jamur aduk rata.\n" +
                    "Masukkan jamur enoki\n" +
                    "aduk rata hingga layu koreksi rasa.\n" +
                    "Tata pokcoy yg sudah d rebus d atas piring. Siram dengan tumisan jamur.",
            recipePictures = "pokcoy_siram_jamur_enoki",
            mealType = 2
        ),
        Recipe(
            recipeId = 287,
            name = "Mie Shirataki",
            calories = 371.0,
            carbs = 0.0,
            fat = 11.4,
            protein = 33.0,
            ingredients = "75 gram udang\n" +
                    "1 sdm saus tiram\n" +
                    "82 gram brokoli\n" +
                    "50 gram kubis\n" +
                    "1 siung bawang putih\n" +
                    "7 gram bawang merah\n" +
                    "1/2 sdm kecap manis\n" +
                    "200 g mie kering shirataki\n" +
                    "94 gram sawi dimasak (dari segar)\n" +
                    "1 telur dadar atau telur orak-arik",
            cookingSteps = "Cuci mie shirataki basah dan tiriskan.\n" +
                    "Rebus mie 3menit\n" +
                    "sambil potong2 sayuran kemudian cuci bersih.\n" +
                    "Siapkan teflon anti lengket\n" +
                    "tumis bawang merah dan bawang putih sampai harum\n" +
                    "masukkan telur lalu orak arik. Masukkan udang kemudian tambahkan sedikit air. Masak hingga udang matang\n" +
                    "kemudian masukkan sayur2an.\n" +
                    "Tambahkan saus tiram dan kecap manis\n" +
                    "masukkan mie shirataki. Aduk rata\n" +
                    "masak sampai semua matang.\n" +
                    "Angkat dan sajikan.",
            recipePictures = "mie_shirataki",
            mealType = 2
        ),
        Recipe(
            recipeId = 290,
            name = "Bakso Tempe",
            calories = 32.0,
            carbs = 4.0,
            fat = 1.2,
            protein = 1.4,
            ingredients = "100 gram telur rebus\n" +
                    "5 gram kaldu jamur\n" +
                    "1 sdt lada hitam\n" +
                    "3 sdt garam\n" +
                    "22 gram bawang putih\n" +
                    "200 g tepung tapioka\n" +
                    "252 gram tempe kukus",
            cookingSteps = "Kukus tempe 15 menit.\n" +
                    "Hancurkan tempe dan masukkan semua bahan\n" +
                    "aduk sampai kalis.\n" +
                    "Bentuk bulat tempe dan rebus 15 menit sampai mengambang.",
            recipePictures = "bakso_tempe",
            mealType = 2
        ),
        Recipe(
            recipeId = 292,
            name = "Makaroni ayam",
            calories = 147.0,
            carbs = 18.8,
            fat = 3.8,
            protein = 8.9,
            ingredients = "100 gram dada ayam\n" +
                    "1 mangkok\n" +
                    "masak makaroni\n" +
                    "1 mangkok saus spageti tanpa daging\n" +
                    "1 siung bawang putih dimasak\n" +
                    "100 gram bawang bombay dimasak\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sejumput garam laut\n" +
                    "1 sdm saus sambal",
            cookingSteps = "Potong bawang putih (1 siung)\n" +
                    "bawang bombay setengah aja.\n" +
                    "Marinasi ayam dengan lada\n" +
                    "bawang putih bubuk dan garam ayamnya di potong dadu ya.\n" +
                    "Tumis bawang putih dan bawang bombay.\n" +
                    "Masukkan ayam yang sudah dimarinasi.\n" +
                    "Beri sedikit air.\n" +
                    "Kemudian masukkan saus bolognese dan saus sambal 1 sendok.\n" +
                    "Kemudian masak hingga air menyusut.\n" +
                    "Tambah sedikit keju quick melt untuk hiasan aja sih hehe.",
            recipePictures = "makaroni_ayam",
            mealType = 2
        ),
        Recipe(
            recipeId = 294,
            name = "Nasi Goreng Spesial (Nasi Merah)",
            calories = 509.0,
            carbs = 42.4,
            fat = 17.6,
            protein = 43.3,
            ingredients = "2 besar telur\n" +
                    "2 sdt garam\n" +
                    "100 gram daging ayam (panggang\n" +
                    "bakar\n" +
                    "dimasak)\n" +
                    "3 siung bawang putih\n" +
                    "100 gram sayur campuran\n" +
                    "100 gram nasi merah (butir-sedang\n" +
                    "dimasak)\n" +
                    "3 g lada putih bubuk",
            cookingSteps = "Haluskan bawang putih. Oseng sebentar\n" +
                    "lalu masukkan sayuran dan daging ayam rebus.\n" +
                    "Pecahkan satu telur\n" +
                    "buat scramble egg. Lalu masukkan nasi merah.\n" +
                    "Tambahkan garam dan lada. Campur sampai rata dan matang.\n" +
                    "Buat satu telur mata sapi. Hidangkan dipiring.",
            recipePictures = "nasi_goreng_spesial_nasi_merah",
            mealType = 2
        ),
        Recipe(
            recipeId = 295,
            name = "Omlate Bayam",
            calories = 179.0,
            carbs = 6.3,
            fat = 13.7,
            protein = 0.0,
            ingredients = "55 gram telur\n" +
                    "30 gram wortel\n" +
                    "28 gram bayam\n" +
                    "1 takaran royco ayam\n" +
                    "9 ml minyak goreng filma\n" +
                    "1 takaran lada putih bubuk",
            cookingSteps = "Rebus bayam\n" +
                    "parut wortel.\n" +
                    "Kocok 1 butir telur\n" +
                    "campurkan dengan parutan wortel\n" +
                    "bayar rebus.\n" +
                    "Tambahkan bumbu penyedap\n" +
                    "lalu bisa tambahkan cabai/saus sambal bila suka pedas.\n" +
                    "Panaskan minyak goreng secukupnya lalu masak kurang lebih selama 6mnt.",
            recipePictures = "omlate_bayam",
            mealType = 2
        ),
        Recipe(
            recipeId = 296,
            name = "Seblak Mie Shirataki",
            calories = 114.0,
            carbs = 11.7,
            fat = 5.4,
            protein = 7.1,
            ingredients = "1 besar telur\n" +
                    "2 gram cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "4 gram bawang putih\n" +
                    "10 gram bawang merah\n" +
                    "2 g kencur\n" +
                    "1 bendak wangi sweetener diabtx\n" +
                    "40 gram pakcoy\n" +
                    "100 g mie basah matang",
            cookingSteps = "Blender bawang merah\n" +
                    "bawang putih\n" +
                    "cengek\n" +
                    "kencur/cikur dan air\n" +
                    "atau bisa di uleg.\n" +
                    "Tumis bumbu (di teflon anti lengket) sampai airnya surut\n" +
                    "(biar gak pait)\n" +
                    "terus jangan tambah minyak.\n" +
                    "Lalu masukan air dan telur\n" +
                    "tunggu sampai matang. Bisa tambah daun jeruk biar wangi.\n" +
                    "Tambah mie shirataki dan pakcoy.\n" +
                    "Masukan gula tropicana dan garam sesuai selera.\n" +
                    "Cicipi\n" +
                    "tunggu sampai airnya agak surut.\n" +
                    "Selesai.",
            recipePictures = "seblak_mie_shirataki",
            mealType = 2
        ),
        Recipe(
            recipeId = 297,
            name = "Kangkung Tumis",
            calories = 450.0,
            carbs = 35.3,
            fat = 32.4,
            protein = 10.9,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 irisan tomat merah\n" +
                    "1 kecil bawang merah\n" +
                    "3 sdm minyak goreng\n" +
                    "300 gram kangkung\n" +
                    "100 g saus tiram\n" +
                    "23 g saus tiram",
            cookingSteps = "Iris kangkung dan bahan lain nya pisahkan.\n" +
                    "Tumis bahan.\n" +
                    "Lalu masukan kangkung\n" +
                    "Saori dan sedikit air.\n" +
                    "Tunggu sampai kangkung masak dan siap disajikan.",
            recipePictures = "kangkung_tumis",
            mealType = 2
        ),
        Recipe(
            recipeId = 298,
            name = "Steam Fish",
            calories = 310.0,
            carbs = 21.3,
            fat = 8.2,
            protein = 38.5,
            ingredients = "2 sdm kecap asin (kedelai)\n" +
                    "8 g jeruk nipis\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "1 sdt minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "1 siung bawang putih\n" +
                    "2 iris diameter 2\n" +
                    "5 cm jahe\n" +
                    "30 gram daun bawang\n" +
                    "20 gram sereh\n" +
                    "200 gram ikan bawal\n" +
                    "3 g lada putih bubuk",
            cookingSteps = "Bumbui ikan dengan garam\n" +
                    "lada\n" +
                    "dan jeruk nipis\n" +
                    "diamkan 10 menit.\n" +
                    "Letakkan ikan pada wadah tahan panas\n" +
                    "Kukus bersama irisan jahe\n" +
                    "bawang putih dan sereh selama 10 menit. Kemudian buang airnya ( ikan berair saat dikukus).\n" +
                    "Siap kan saus\n" +
                    "(5 sdm air matang\n" +
                    "minyak wijen\n" +
                    "kecap asin\n" +
                    "saus tiram\n" +
                    "lada. Jika kurang asin tambahkan sedikit garam).\n" +
                    "Setelah 10 menit angkat ikan\n" +
                    "masukkan saus. Kemudian kukus lagi selama 10 menit.\n" +
                    "Tambahkan daun bawang dan cabai merah. Kukus lagi sebentar kemudian angkat.",
            recipePictures = "steam_fish",
            mealType = 2
        ),
        Recipe(
            recipeId = 299,
            name = "Sayur Bayam Bening",
            calories = 55.0,
            carbs = 9.3,
            fat = 1.5,
            protein = 5.0,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 iris\n" +
                    "tipis bawang\n" +
                    "150 gram bayam",
            cookingSteps = "Bersihkan sayur.\n" +
                    "Rebus air sampai mendidih.\n" +
                    "Masukkan kembali bayam sampai tidak keras lagi. Menyajikan.",
            recipePictures = "sayur_bayam_bening",
            mealType = 2
        ),
        Recipe(
            recipeId = 300,
            name = "Tumis Tempe Sayur",
            calories = 180.0,
            carbs = 24.4,
            fat = 5.7,
            protein = 12.0,
            ingredients = "1 sdm kecap asin (kedelai)\n" +
                    "100 gram buncis\n" +
                    "100 gram kubis\n" +
                    "100 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "100 gram tempe\n" +
                    "1 sdm kecap manis\n" +
                    "2 g royco ayam",
            cookingSteps = "Iris bawang putih\n" +
                    "wortel\n" +
                    "buncis\n" +
                    "kubis dan potong tempe sesuai selera.\n" +
                    "Tumis bawang putih dengan sedikit air sampai layu dan harum.\n" +
                    "Masukan wortel\n" +
                    "buncis dan tempe. Lalu masukan kecap asin 1 sdm\n" +
                    "kecap manis 1 sdm dan royco rasa ayam secukupnya.\n" +
                    "Masukan air secukupnya sedikit demi sedikit agar sayuran dan tempe matang. Aduk rata. Tunggu sampai air menyusut\n" +
                    "jika dirasa kurang matang tambahkan lagi air dan biarkan menyusut.\n" +
                    "Terakhir masukan kol\n" +
                    "aduk dan tunggu sampai kol matang.\n" +
                    "Siap disajikan.",
            recipePictures = "tumis_tempe_sayur",
            mealType = 2
        ),
        Recipe(
            recipeId = 309,
            name = "Tumis Brokoli Udang",
            calories = 329.0,
            carbs = 14.3,
            fat = 16.5,
            protein = 32.9,
            ingredients = "100 gram udang\n" +
                    "2 mangkok\n" +
                    "dicincang brokoli\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm minyak zaitun",
            cookingSteps = "Tumis bawang putih menggunakan minyak zaitun hingga harum.\n" +
                    "Masukan udang\n" +
                    "masak hingga berubah warna\n" +
                    "beri sedikit air\n" +
                    "masukan garam dan lada.\n" +
                    "Masukan brokoli\n" +
                    "tumis hingga matang.",
            recipePictures = "tumis_brokoli_udang",
            mealType = 2
        ),
        Recipe(
            recipeId = 312,
            name = "Tumis Kacang Panjang",
            calories = 280.0,
            carbs = 8.5,
            fat = 13.3,
            protein = 31.1,
            ingredients = "100 gram dada ayam\n" +
                    "55 g kacang panjang\n" +
                    "25 gram bombay\n" +
                    "1 sdt minyak wijen\n" +
                    "1 siung bawang putih",
            cookingSteps = "Potong kacang panjang\n" +
                    "dada ayam\n" +
                    "bawang bombay sesuai selera.\n" +
                    "Panaskan sebentar minyak\n" +
                    "masukkan bawang bombay dan bawang putih hingga harum lalu masukkan ayam hingga berubah warna\n" +
                    "terakhir masukkan kacang panjang.\n" +
                    "Tambahkan sedikit air\n" +
                    "penyedap/garam secukupnya\n" +
                    "masak hingga matang\n" +
                    "taburkan bawang goreng. Siap dihidangkan.",
            recipePictures = "tumis_kacang_panjang",
            mealType = 2
        ),
        Recipe(
            recipeId = 6,
            name = "Kue Pisang",
            calories = 118.0,
            carbs = 19.3,
            fat = 2.8,
            protein = 6.3,
            ingredients = "1 besar telur\n" +
                    "1 sdt kayu manis\n" +
                    "1/4 sdt garam\n" +
                    "1 ekstra besar pisang\n" +
                    "1 sdt baking soda\n" +
                    "50 g instant oatmeal\n" +
                    "3 g stevia sweetener\n" +
                    "15 g wpro milk tea",
            cookingSteps = "Hancurkan pisang.\n" +
                    "Aduk telur sampai berbusa\n" +
                    "masukkan kedalam pisang.\n" +
                    "Tambahkan sisa bahan dan aduk rata.\n" +
                    "Bagi adonan ke dalam cetakan dan panggang dalam oven dengan suhu 210°C selama 15-20 menit.",
            recipePictures = "kue_pisang",
            mealType = 3
        ),
        Recipe(
            recipeId = 13,
            name = "Tumis Kubis",
            calories = 89.0,
            carbs = 19.2,
            fat = 1.6,
            protein = 3.4,
            ingredients = "400 gram kubis hijau\n" +
                    "30 gram cabai merah atau rawit\n" +
                    "20 gram bawang putih\n" +
                    "1 kecil utuh tomat merah\n" +
                    "20 gram gula merah\n" +
                    "45 gram bawang merah\n" +
                    "3 g kaldu jamur\n" +
                    "1/2 sdm saus tiram\n" +
                    "2 gram udang rebon",
            cookingSteps = "Potong sayuran sesuai ukuran yang diinginkan.\n" +
                    "Tumis bawang merah dan bawang putih dalam wajan yang diolesi sedikit minyak.\n" +
                    "Tambahkan bahan lainnya satu per satu.\n" +
                    "Tuang air secukupnya dan tutup. Masak hingga matang.",
            recipePictures = "tumis_kubis",
            mealType = 3
        ),
        Recipe(
            recipeId = 58,
            name = "Brownies Mocaf",
            calories = 213.0,
            carbs = 25.5,
            fat = 12.1,
            protein = 2.4,
            ingredients = "2 besar telur\n" +
                    "100 gram coklat manis atau gelap\n" +
                    "50 gram mentega\n" +
                    "15 gram kacang almond\n" +
                    "100 gram gula merah\n" +
                    "13 g coklat bubuk\n" +
                    "30 ml extra virgin olive oil\n" +
                    "100 g tepung mocaf",
            cookingSteps = "Lelehkan coklat manis gelap dengan minyak zaitun dan mentega.\n" +
                    "Kocok telur dan gula merah semut dengan whisk.\n" +
                    "Masukkan lelehan coklat tadi\n" +
                    "aduk rata.\n" +
                    "Masukan tepung mocaf aduk rata.\n" +
                    "Masukan lagi coklat bubuk\n" +
                    "aduk rata kembali.\n" +
                    "Panaskan oven 180°C selama 10 menit.Masukan adonan kedalam loyang panggang dan tambahkan almond sebagai topping selama 20-25m.\n" +
                    "Brownis siap dihidangkan.",
            recipePictures = "brownies_mocaf",
            mealType = 3
        ),
        Recipe(
            recipeId = 76,
            name = "Enoki Pedas Manis",
            calories = 134.0,
            carbs = 14.6,
            fat = 0.0,
            protein = 3.6,
            ingredients = "1 sdt bubuk cabai\n" +
                    "1 sdt garam\n" +
                    "1 sdm minyak wijen\n" +
                    "2 siung bawang putih\n" +
                    "2 sdm saus tomat\n" +
                    "1 sdt gula pasir\n" +
                    "2 ml minyak goreng\n" +
                    "200 g jamur enoki\n" +
                    "2 sdm saos tiram",
            cookingSteps = "Potong akar jamur dan cuci.\n" +
                    "Panaskan minyak dalam wajan dan tumis bawang putih hingga harum.\n" +
                    "Tambahkan sisa bahan secara bertahap.\n" +
                    "Masak selama 10 menit atau sampai empuk.",
            recipePictures = "enoki_pedas_manis",
            mealType = 3
        ),
        Recipe(
            recipeId = 77,
            name = "Tumis Kecambah",
            calories = 95.0,
            carbs = 8.1,
            fat = 3.5,
            protein = 8.3,
            ingredients = "62 gram telur\n" +
                    "71 gram tomat\n" +
                    "100 gram kecambah\n" +
                    "33 gram putih telur\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdt garam\n" +
                    "6 gram bawang putih\n" +
                    "25 gram daun bawang\n" +
                    "15 gram bawang merah\n" +
                    "1 sdm sambal tabur",
            cookingSteps = "Cuci dan potong sayuran.\n" +
                    "Masukkan tomat ke dalam wajan panas dan aduk sampai jusnya keluar.\n" +
                    "Tambahkan bawang putih dan bawang bombay. Aduk hingga harum.\n" +
                    "Masukkan sisa bahan satu per satu. Tambahkan air sesuai kebutuhan dan didihkan selama 3 menit.",
            recipePictures = "tumis_kecambah",
            mealType = 3
        ),
        Recipe(
            recipeId = 78,
            name = "Tumis Sayuran",
            calories = 80.0,
            carbs = 18.1,
            fat = 0.3,
            protein = 3.5,
            ingredients = "1 sejumput lada hitam\n" +
                    "140 gram buncis\n" +
                    "100 gram wortel\n" +
                    "120 gram kembang kol\n" +
                    "3 siung bawang putih\n" +
                    "1 sejumput garam laut\n" +
                    "1 1/2 sdm saos tiram\n" +
                    "60 gram bawang bombay",
            cookingSteps = "Potong sayuran menjadi potongan-potongan kecil.\n" +
                    "Tumis bawang putih dan bawang bombay dalam wajan antilengket.\n" +
                    "Perlahan tambahkan sisa bahan. Masak hingga empuk.",
            recipePictures = "tumis_sayuran",
            mealType = 3
        ),
        Recipe(
            recipeId = 87,
            name = "Spring Roll Ayam",
            calories = 119.0,
            carbs = 16.9,
            fat = 3.1,
            protein = 4.1,
            ingredients = "1 sedang rice paper\n" +
                    "10 gram daging dada ayam (ayam pedaging\n" +
                    "dipanggang\n" +
                    "dimasak)\n" +
                    "10 gram wortel\n" +
                    "10 gram mentimun (dengan kulit)\n" +
                    "15 gram bihun (dimasak)\n" +
                    "8 ml roasted sesame dressing",
            cookingSteps = "Rebus bihun\n" +
                    "tiriskan.\n" +
                    "Potong sayur.\n" +
                    "Celup rice paper 2 detik di air.\n" +
                    "Siapkan dan gulung kertas nasi dengan bihun\n" +
                    "ayam dan sayuran di dalamnya. Gunakan saus wijen sebagai saus.",
            recipePictures = "spring_roll_ayam",
            mealType = 3
        ),
        Recipe(
            recipeId = 97,
            name = "Udang Nori",
            calories = 47.0,
            carbs = 1.6,
            fat = 2.6,
            protein = 4.2,
            ingredients = "70 gram udang\n" +
                    "1/2 sdt garam\n" +
                    "2 sdt minyak wijen\n" +
                    "20 gram wortel\n" +
                    "20 gram daun bawang\n" +
                    "1 takaran stevia sweetener\n" +
                    "1 bungkus rumput laut panggang (nori)\n" +
                    "1 porsi kaldu jamur\n" +
                    "1 takaran lada putih bubuk",
            cookingSteps = "Siapkan bahan\n" +
                    "cincang udang dan bawang daun serta parut wortel dengan parutan keju.\n" +
                    "Masukan semua bahan kedalam wadah beri minyak jijen dan bumbu penyedap( garam\n" +
                    "merica\n" +
                    "kaldu jamur\n" +
                    "pemanis stevia secukupnya ).\n" +
                    "Aduk hingga semua tercampur rata.\n" +
                    "Potong nori segi panjang menjadi beberapa bagian\n" +
                    "disisi lain panaskan teflon dengan olesan sedikit minyak wijen.\n" +
                    "Taruh nori diatas teflon\n" +
                    "lapisi dengan adonan hingga nori tertutup. Tutup teflon\n" +
                    "Panggang dengan api yg sangat kecil.\n" +
                    "Jika sudah hampir matang\n" +
                    "balikkan perlahan.\n" +
                    "Sajikan di piring dengan nasi dan sayuran pilihan.",
            recipePictures = "udang_nori",
            mealType = 3
        ),
        Recipe(
            recipeId = 99,
            name = "Stir Fry Asparagus",
            calories = 22.0,
            carbs = 2.8,
            fat = 1.0,
            protein = 1.4,
            ingredients = "120 gram asparagus\n" +
                    "1 siung bawang putih\n" +
                    "2 ml minyak goreng tropical",
            cookingSteps = "Rebus asparagus hingga agak lunak.\n" +
                    "Tumis bawang putih dengan sedikit minyak.\n" +
                    "Masukkan asparagus yang telah direbus.\n" +
                    "Tumis asparagus dengan bawang putih dan tunggu hingga matang.\n" +
                    "Hidangan siap disantap.",
            recipePictures = "stir_fry_asparagus",
            mealType = 3
        ),
        Recipe(
            recipeId = 101,
            name = "Pizza",
            calories = 144.0,
            carbs = 21.9,
            fat = 3.8,
            protein = 6.2,
            ingredients = "5 gram ragi\n" +
                    "1 sdm margarin\n" +
                    "1 sdm mayones ringan\n" +
                    "20 gram gula pasir\n" +
                    "100 gram bawang bombay\n" +
                    "1/2 cup spaghetti sauce\n" +
                    "150 g tepung segitiga biru\n" +
                    "3 porsi beef frank\n" +
                    "1 porsi sosis bratwurst",
            cookingSteps = "Campur air hangat dengan ragi dan gula.\n" +
                    "Tambahkan tepung terigu dan margarin. Aduk rata dan uleni adonan hingga kalis. Diamkan selama 1 jam hingga mengembang.\n" +
                    "Gulung adonan\n" +
                    "taruh di atas loyang dan tusuk dengan garpu.\n" +
                    "Oleskan saus spageti. Tambahkan irisan sosis\n" +
                    "daging sapi\n" +
                    "mayones dan bawang bombay. Taburi dengan peterseli dan oregano\n" +
                    "jika diinginkan.\n" +
                    "Diamkan selama 15 menit selagi oven dipanaskan terlebih dahulu.\n" +
                    "Panggang dalam oven selama 30 menit dengan suhu 140°C.",
            recipePictures = "pizza",
            mealType = 3
        ),
        Recipe(
            recipeId = 103,
            name = "Oseng Toge Tahu Telur",
            calories = 487.0,
            carbs = 29.1,
            fat = 29.7,
            protein = 29.8,
            ingredients = "118 gram telur\n" +
                    "65 gram kecambah\n" +
                    "5 gram cabai merah atau rawit\n" +
                    "20 gram bawang putih\n" +
                    "3 gram jahe\n" +
                    "1 sdm minyak goreng\n" +
                    "130 gram tahu\n" +
                    "5 g kaldu jamur\n" +
                    "1 sdm kecap manis",
            cookingSteps = "Potong dadu tahu\n" +
                    "masukkan dalam wadah.\n" +
                    "Kocok lepas telur\n" +
                    "tuang kedalam tahu.\n" +
                    "Panaskan minyak\n" +
                    "tumis bawang putih\n" +
                    "cabe rawit dan jahe hingga harum.\n" +
                    "Tuang tahu dan telur\n" +
                    "aduk-aduk hingga telur matang.\n" +
                    "Masukkan toge\n" +
                    "aduk aduk.\n" +
                    "Bumbui dengan kecap manis dan kaldu jamur\n" +
                    "tambahkan air sedikit.\n" +
                    "Masak sampai bumbu meresap.\n" +
                    "Oseng toge tahu telur siap disajikan.",
            recipePictures = "oseng_toge_tahu_telur",
            mealType = 3
        ),
        Recipe(
            recipeId = 104,
            name = "Tumis Pokcoy Saus Tiram",
            calories = 195.0,
            carbs = 22.8,
            fat = 11.9,
            protein = 3.1,
            ingredients = "30 gram cabai merah atau rawit\n" +
                    "100 gram saus tiram\n" +
                    "3 siung bawang putih\n" +
                    "5 kecil bawang merah\n" +
                    "3 sdm minyak goreng\n" +
                    "100 gram pakcoy",
            cookingSteps = "Cuci bersih pokcoy\n" +
                    "potong potong sesuai selesai.\n" +
                    "Kupas bawang merah\n" +
                    "bawang putih\n" +
                    "dan cabai. Cuci bersih\n" +
                    "lalu iris tipis dan memanjang untuk cabai.\n" +
                    "Panaskan minyak dalam wajan\n" +
                    "masukan bawang merah dan bawang putih tunggu hingga harum\n" +
                    "masukan cabai tunggu wangi.\n" +
                    "Masukan pokcoy aduk aduk.\n" +
                    "Masukan saus tiram\n" +
                    "tambahkan air sedikit.\n" +
                    "Tunggu hingga matang\n" +
                    "makanan siap disajikan.",
            recipePictures = "tumis_pokcoy_saus_tiram",
            mealType = 3
        ),
        Recipe(
            recipeId = 109,
            name = "Tumis Toge",
            calories = 38.0,
            carbs = 6.7,
            fat = 0.5,
            protein = 4.0,
            ingredients = "100 gram kecambah\n" +
                    "2 siung bawang putih\n" +
                    "1 takaran royco ayam",
            cookingSteps = "Iris bawang putih dan tumis di atas api yang panas tanpa minyak.\n" +
                    "Tambahkan taoge dan penyedap rasa sesuai selera. Campur dengan baik.\n" +
                    "Tumis hingga matang.",
            recipePictures = "tumis_toge",
            mealType = 3
        ),
        Recipe(
            recipeId = 110,
            name = "Ampyang",
            calories = 132.0,
            carbs = 14.5,
            fat = 7.5,
            protein = 3.6,
            ingredients = "300 gram kacang sangrai kering (tanpa garam)\n" +
                    "166 gram gula merah\n" +
                    "63 gram gula pasir",
            cookingSteps = "Masukkan gula merah\n" +
                    "gula pasir dan air ke dalam panci. Aduk hingga mengental dan gula larut.\n" +
                    "Buang kulit kacang panggang dan masukkan ke dalam wajan.\n" +
                    "Matikan api. Aduk hingga adonan ampyang benar-benar mengental dan matang.\n" +
                    "Ambil satu sendok makan campuran ampyang. Tuang di atas kertas lalu bentuk menjadi bentuk datar atau bulat. Lakukan hingga adonan habis.\n" +
                    "Biarkan dingin dan mengeras lalu simpan di toples kedap udara.",
            recipePictures = "ampyang",
            mealType = 3
        ),
        Recipe(
            recipeId = 120,
            name = "Tumis Timun dan Ayam",
            calories = 236.0,
            carbs = 8.9,
            fat = 17.4,
            protein = 10.2,
            ingredients = "1 sejumput garam\n" +
                    "50 gram daging paha dan kulit ayam (ayam pedaging)\n" +
                    "80 gram mentimun (dengan kulit)\n" +
                    "5 ml kecap manis\n" +
                    "1 sdm minyak kanola\n" +
                    "1/2 takaran royco ayam\n" +
                    "1 sejumput lada putih bubuk",
            cookingSteps = "Goreng ayam dalam minyak canola sampai berwarna cokelat keemasan.\n" +
                    "Tambahkan mentimun potong dadu dan bumbu.\n" +
                    "Aduk hingga harum dan merata.\n" +
                    "Pindahkan ke piring dan sajikan dengan nasi\n" +
                    "opsional.",
            recipePictures = "tumis_timmun_dan_ayam",
            mealType = 3
        ),
        Recipe(
            recipeId = 121,
            name = "Tumis Kangkung",
            calories = 58.0,
            carbs = 6.4,
            fat = 3.1,
            protein = 2.3,
            ingredients = "1/2 gram cabai merah atau rawit\n" +
                    "10 gram bawang putih\n" +
                    "9 gram bawang merah\n" +
                    "3 ml minyak goreng\n" +
                    "60 gram kangkung",
            cookingSteps = "Tumis bawang merah dan bawang putih smpai harum dalam minyak goreng.\n" +
                    "Masukan cabai dan kangkung.\n" +
                    "Tumis hingga layu.",
            recipePictures = "tumis_kangkung",
            mealType = 3
        ),
        Recipe(
            recipeId = 122,
            name = "Tumis Telur Tahu",
            calories = 278.0,
            carbs = 15.1,
            fat = 14.7,
            protein = 26.2,
            ingredients = "1 besar telur\n" +
                    "50 gram kecambah\n" +
                    "10 gram kaldu jamur\n" +
                    "10 gram daun bawang\n" +
                    "50 gram bayam\n" +
                    "50 gram tempe\n" +
                    "1 sdt bawang putih cincang\n" +
                    "70 gram tahu\n" +
                    "1 sdm sambal goreng\n" +
                    "2 g royco ayam",
            cookingSteps = "Tumis baput\n" +
                    "bawang putih cincang dan saus sambal lalu masukkan potongan tempe dan tumis hingga harum.\n" +
                    "Kocok telur dan tahu.\n" +
                    "Tambahkan ke tumisan\n" +
                    "lalu aduk hingga telur dan tahu sedikit menggumpal. Tambahkan sedikit air.\n" +
                    "Tambahkan tauge\n" +
                    "daun bawang dan bayam lalu tambahkan lebih banyak air jika perlu.\n" +
                    "Tambahkan bumbu kaldu jamur dan sedikit royco. Tumis hingga semuanya matang.\n" +
                    "Angkat dan sajikan.",
            recipePictures = "tumis_telur_tahu",
            mealType = 3
        ),
        Recipe(
            recipeId = 123,
            name = "Ayam Saos Tiram",
            calories = 669.0,
            carbs = 29.6,
            fat = 26.2,
            protein = 76.7,
            ingredients = "500 gram dada ayam\n" +
                    "1 sejumput lada hitam\n" +
                    "20 gram cabai merah atau rawit\n" +
                    "4 sdm saus tiram\n" +
                    "5 siung bawang putih\n" +
                    "10 gram daun bawang\n" +
                    "3/4 gelas air\n" +
                    "1 sejumput garam laut\n" +
                    "1 sdm minyak goreng\n" +
                    "1 sdm kecap manis\n" +
                    "1 sedang bawang bombay\n" +
                    "22 g saus sambal\n" +
                    "10 g lengkuas",
            cookingSteps = "Potong semua bahan.\n" +
                    "Rebus air hingga mendidih lalu masak ayam. Sisihkan saat Anda selesai.\n" +
                    "Dalam wajan yang sudah dipanaskan\n" +
                    "tumis bawang bombay lalu tambahkan cabai dan lengkuas. Aduk dan tambahkan sisa bumbu dan rempah-rempah.\n" +
                    "Masukkan ayam dan tambahkan sedikit air.\n" +
                    "Tambahkan daun bawang cincang halus. Aduk rata.",
            recipePictures = "ayam_saos_tiram",
            mealType = 3
        ),
        Recipe(
            recipeId = 132,
            name = "Tempe Kecap",
            calories = 489.0,
            carbs = 56.8,
            fat = 23.7,
            protein = 22.4,
            ingredients = "2 siung bawang putih dimasak\n" +
                    "3 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "100 gram tempe\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "2 sdm kecap manis",
            cookingSteps = "Kupas bawang merah\n" +
                    "bawang putih bawang putih dan cincang halus bersama cabai\n" +
                    "Panaskan minyak. Tumis bumbu sampai harum\n" +
                    "Masukan air\n" +
                    "garam secukupnya\n" +
                    "dan kecap manis\n" +
                    "Masukan tempe yang udah dipotong dadu\n" +
                    "Aduk-aduk hingga air susut",
            recipePictures = "tempe_kecap",
            mealType = 3
        ),
        Recipe(
            recipeId = 136,
            name = "Sup Tahu Brokoli",
            calories = 158.0,
            carbs = 8.9,
            fat = 9.3,
            protein = 12.1,
            ingredients = "1 besar telur\n" +
                    "130 gram brokoli\n" +
                    "1 siung bawang putih\n" +
                    "1/2 sdt gula pasir\n" +
                    "5 gram garam laut\n" +
                    "175 gram tahu\n" +
                    "3 g lada putih bubuk\n" +
                    "5 ml minyak goreng",
            cookingSteps = "Cuci bersih brokoli dan pot dg tahu.\n" +
                    "Tumis bawang putih dg minyak lalu masukkan air.\n" +
                    "Masukkan brokoli rebus masukkan bumbu lalu masuk lg tahu.\n" +
                    "Terakhir kocok telur dan tuang sdkit ke dlm panci rebusan.\n" +
                    "Aduk rata\n" +
                    "siap dimakan.",
            recipePictures = "sup_tahu_brokoli",
            mealType = 3
        ),
        Recipe(
            recipeId = 138,
            name = "Sup Tofu Telur",
            calories = 503.0,
            carbs = 31.9,
            fat = 38.6,
            protein = 18.1,
            ingredients = "1 sedang telur\n" +
                    "8 gram kaldu jamur\n" +
                    "8 gram cabai merah atau rawit\n" +
                    "1/4 sdt garam\n" +
                    "1 sdm saus tiram\n" +
                    "2 siung bawang putih\n" +
                    "3 kecil bawang merah\n" +
                    "2 sdm minyak goreng\n" +
                    "180 g egg tofu",
            cookingSteps = "Potong tahu sesuai selera\n" +
                    "lalu cincang bawang putih dan bawang merah.\n" +
                    "Panaskan minyak goreng dengan teflon\n" +
                    "masukan bawang putih masak hingga tercium wanginya.\n" +
                    "Lalu masukan bawang merah sambil diaduk agar tidak gosong.\n" +
                    "Tambahkan air gelas.\n" +
                    "Masukan cabai\n" +
                    "garam\n" +
                    "kaldu jamur dan saus tiram.\n" +
                    "Lalu masukan telur\n" +
                    "orak arik terus.\n" +
                    "Kemudian masukan tofu\n" +
                    "diamkan hingga mendidih atau sudah terlihat matang.",
            recipePictures = "sup_tofu_telur",
            mealType = 3
        ),
        Recipe(
            recipeId = 140,
            name = "Tempe Goreng",
            calories = 25.0,
            carbs = 1.3,
            fat = 1.4,
            protein = 2.3,
            ingredients = "1 sdt garam\n" +
                    "1 siung bawang putih\n" +
                    "100 gram tempe",
            cookingSteps = "Iris tempe tipis tipis.\n" +
                    "Beri bumbu bawang putih dan garam.\n" +
                    "Lumuri bumbu dan masukkan ke air fryer dengan suhu 200°C waktu 20 menit.",
            recipePictures = "tempe_goreng",
            mealType = 3
        ),
        Recipe(
            recipeId = 147,
            name = "Sup Telur",
            calories = 187.0,
            carbs = 11.0,
            fat = 10.9,
            protein = 12.2,
            ingredients = "1 besar telur\n" +
                    "1 mangkok\n" +
                    "segar sawi dimasak (dari segar)\n" +
                    "25 gram kaldu jamur\n" +
                    "1 sdt garam\n" +
                    "25 g sosis ayam\n" +
                    "3 g lada putih bubuk",
            cookingSteps = "Kocok telur dan campurkan garam.\n" +
                    "Didihkan air dan jika air sudah mendidih masukkan telur kocok.\n" +
                    "Kemudian masukkan sosis dan sawi bersama kaldu dan merica.",
            recipePictures = "sup_telur",
            mealType = 3
        ),
        Recipe(
            recipeId = 158,
            name = "Ayam Brokoli Tumis",
            calories = 347.0,
            carbs = 14.5,
            fat = 20.6,
            protein = 27.8,
            ingredients = "80 gram dada ayam\n" +
                    "1 mangkok\n" +
                    "segar\n" +
                    "potongan batang brokoli dimasak (dari segar)\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdt garam\n" +
                    "1 siung bawang putih\n" +
                    "20 g saus tiram\n" +
                    "1 sdm minyak goreng",
            cookingSteps = "Potong ayam dadu\n" +
                    "lalu goreng ayam sampai kering.\n" +
                    "Tumis bawang putih\n" +
                    "brokoli\n" +
                    "dan masuk ayam.\n" +
                    "Tambah air\n" +
                    "saus tiram\n" +
                    "dan bumbu.\n" +
                    "Aduk sampai rata.\n" +
                    "Sajikan.",
            recipePictures = "ayam_brokoli_tumis",
            mealType = 3
        ),
        Recipe(
            recipeId = 162,
            name = "Sup Tomat",
            calories = 206.0,
            carbs = 11.3,
            fat = 10.2,
            protein = 20.6,
            ingredients = "100 gram tomat\n" +
                    "1 besar putih telur\n" +
                    "1 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "200 gram tahu\n" +
                    "115 g royco kaldu rasa sapi\n" +
                    "3 g lada putih bubuk",
            cookingSteps = "Potong-potong tomat dan bawang putih lalu blender dengan air sampai halus.\n" +
                    "Masak sampai mendidih lalu masukkan tahu yang sudah dipotong dadu.\n" +
                    "Setelah kuah berkurang masukkan putih telur.\n" +
                    "Tambahkan royco dan lada bubuk\n" +
                    "tes rasa hingga sesuai yang diinginkan.\n" +
                    "Setelah matang tambahkan daun bawang dam hidangkan.",
            recipePictures = "sup_tomat",
            mealType = 3
        ),
        Recipe(
            recipeId = 163,
            name = "Sup Labu Telur",
            calories = 166.0,
            carbs = 14.1,
            fat = 7.7,
            protein = 8.2,
            ingredients = "2 sedang telur\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "160 gram labu\n" +
                    "1 gelas air\n" +
                    "5 ml minyak goreng\n" +
                    "40 gram tahu\n" +
                    "15 g bihun jagung\n" +
                    "4 g bumbu penyedap rasa sapi",
            cookingSteps = "Rebus bihun hingga matang\n" +
                    "sisihkan.\n" +
                    "Kupas dan bersihkan labu potong dadu\n" +
                    "sisihkan.\n" +
                    "Tumis bawang putih\n" +
                    "masukkan telur dan aduk hingga matang.\n" +
                    "Tambahkan air\n" +
                    "masukkan lahu dan tahu.",
            recipePictures = "sup_labu_telur",
            mealType = 3
        ),
        Recipe(
            recipeId = 176,
            name = "Dada Ayam Paprika",
            calories = 272.0,
            carbs = 12.7,
            fat = 7.4,
            protein = 38.5,
            ingredients = "75 gram dada ayam\n" +
                    "50 gram udang\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sejumput garam\n" +
                    "1 sdm saus tiram\n" +
                    "50 gram brokoli\n" +
                    "30 gram wortel\n" +
                    "40 gram paprika kuning manis\n" +
                    "150 ml air\n" +
                    "1 sdt bawang putih cincang",
            cookingSteps = "Masukan air tunggu sampai mendidih.\n" +
                    "Masukan bawang putih cincang.\n" +
                    "Masukan dada ayam\n" +
                    "aduk sampai mendidih.\n" +
                    "Masukan sayuran dan bumbu lainya.\n" +
                    "Aduk sampai matang.\n" +
                    "Sajikan dengan taburan daun petersely.",
            recipePictures = "dada_ayam_paprika",
            mealType = 3
        ),
        Recipe(
            recipeId = 182,
            name = "Poires Helena",
            calories = 388.0,
            carbs = 68.9,
            fat = 12.8,
            protein = 5.3,
            ingredients = "11 gram kayu manis\n" +
                    "3 sedang buah pir\n" +
                    "50 gram kacang almond\n" +
                    "2 1/2 gelas air\n" +
                    "90 gram gula pasir\n" +
                    "15 g saus manis cokelat pisang\n" +
                    "100 ml ice cream cokelat chip",
            cookingSteps = "Masukkan gula pasir dan kayu manis\n" +
                    "tambahkan 1/2 gelas air.\n" +
                    "Aduk\n" +
                    "kemudian tambahkan 2 gelas air dan rebus 3 buah pir.\n" +
                    "Tiriskan\n" +
                    "plating buah pir kemudian tambahkan 2 scoops ice cream\n" +
                    "tambahkan saus coklat dan beri taburan kacang almond.",
            recipePictures = "poires_helena",
            mealType = 3
        ),
        Recipe(
            recipeId = 183,
            name = "Bayam Jamur",
            calories = 83.0,
            carbs = 10.4,
            fat = 2.0,
            protein = 5.9,
            ingredients = "1 kecil telur\n" +
                    "15 gram bawang putih\n" +
                    "80 gram bayam\n" +
                    "12 gram bawang merah\n" +
                    "103 g jamur enoki\n" +
                    "60 ml non fat milk",
            cookingSteps = "Iris halus bawang merah dan putih.\n" +
                    "Tumis bawang merah dan putih sampai kecoklatan.\n" +
                    "Masukan bayam yang sudah dicuci\n" +
                    "aduk sebentar\n" +
                    "tambahkan 50ml air matang.\n" +
                    "Masukan telur.\n" +
                    "Aduk2 sebentar sampai telur tercampur dan bayam layu.\n" +
                    "Masukan jamur enoki yg sdh dicuci bersih.\n" +
                    "Tambahkan susu non fat.\n" +
                    "Diamkan sebentar kemudian angkat.",
            recipePictures = "bayam_jamur",
            mealType = 3
        ),
        Recipe(
            recipeId = 184,
            name = "Gyoza",
            calories = 49.0,
            carbs = 4.9,
            fat = 1.6,
            protein = 3.7,
            ingredients = "200 gram dada ayam\n" +
                    "20 kecil pangsit polos\n" +
                    "1 sdt minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "100 gram kubis\n" +
                    "2 siung bawang putih\n" +
                    "1 mangkok daun bawang\n" +
                    "1 sejumput garam laut\n" +
                    "10 g tepung tapioka",
            cookingSteps = "Haluskan Dada ayam\n" +
                    "tapioka\n" +
                    "1 butir telur\n" +
                    "kubis\n" +
                    "ke dalam chopper sampai halus jangan lupa di beri bumbu.\n" +
                    "Isi kulit pangsit dengan isian gyoza.\n" +
                    "Kukus di atas teflon selama 30 menit dengan sedikit air sambil ditutup.\n" +
                    "10 anggota telah menambahkan resep ini ke buku masak mereka",
            recipePictures = "gyoza",
            mealType = 3
        ),
        Recipe(
            recipeId = 189,
            name = "Bola Ubi Jalar",
            calories = 15.0,
            carbs = 2.9,
            fat = 0.3,
            protein = 0.3,
            ingredients = "58 gram nanas\n" +
                    "377 gram ubi jalar (manis)\n" +
                    "18 gram biji wijen kering utuh\n" +
                    "2 sdt fiber creme",
            cookingSteps = "Kukus ubi hingga matang.\n" +
                    "Hancurkan ubi\n" +
                    "tambahkan fiber creme.\n" +
                    "Bentuk ubi dan isi dengan nanas.\n" +
                    "Lapisi dengan biji wijen.",
            recipePictures = "bola_ubi_jalar",
            mealType = 3
        ),
        Recipe(
            recipeId = 193,
            name = "Bakso dan Sup Telur",
            calories = 362.0,
            carbs = 27.8,
            fat = 14.4,
            protein = 30.0,
            ingredients = "1/2 slice soft silken tofu\n" +
                    "45 g bakso sapi\n" +
                    "250 ml air mineral\n" +
                    "50 g fish roll\n" +
                    "40 g telur ayam kampung\n" +
                    "48 g crab stick",
            cookingSteps = "Didihkan air.\n" +
                    "Setelah didih masukan telur.\n" +
                    "Lalu di aduk sebentar dan masukan semua bahan.\n" +
                    "Tambahkan sedikit penyedap\n" +
                    "gula\n" +
                    "bawang putih bubuk\n" +
                    "dan lada hitam.",
            recipePictures = "bakso_dan_sup_telur",
            mealType = 3
        ),
        Recipe(
            recipeId = 196,
            name = "Martabak Tofu",
            calories = 36.0,
            carbs = 2.2,
            fat = 1.9,
            protein = 3.2,
            ingredients = "1 tsp low sodium\n" +
                    "dry beef broth\n" +
                    "bouillon or consomme\n" +
                    "\n" +
                    "1 besar egg\n" +
                    "1 medium carrots\n" +
                    "1 tsp white pepper\n" +
                    "2 sejumput salt\n" +
                    "1/2 sdm oyster sauce\n" +
                    "2 siung garlic\n" +
                    "1 mangkok\n" +
                    "dicincang scallions or spring onions\n" +
                    "3/4 block regular tofu (with calcium sulfate)",
            cookingSteps = "Hancurkan tahu\n" +
                    "parut wortel dan potong daun bawang.\n" +
                    "Campurkan semua bahan dan telur aduk rata dan tambahkan bumbu-bumbu.\n" +
                    "Siapkan pan panas lalu kecilkan api dan panggang bertahap.\n" +
                    "6 anggota telah menambahkan resep ini ke buku masak mereka",
            recipePictures = "martabak_tofu",
            mealType = 3
        ),
        Recipe(
            recipeId = 201,
            name = "Lumpia Pisang Aroma",
            calories = 329.0,
            carbs = 60.7,
            fat = 7.1,
            protein = 8.6,
            ingredients = "15 gram keju cheddar\n" +
                    "4 sedang pisang\n" +
                    "20 gram gula pasir\n" +
                    "1 mangkok saus cokelat\n" +
                    "16 sedang pangsit polos",
            cookingSteps = "Siapkan bahan dan lelehakan coklat.\n" +
                    "Masukkan pisang keju gula pasir ke kulit pangsit lalu gulung.\n" +
                    "Goreng.\n" +
                    "Tambahkan lelehan coklat dan keju diatas nya.",
            recipePictures = "tumpia_pisang_aroma",
            mealType = 3
        ),
        Recipe(
            recipeId = 204,
            name = "Onde-onde Panggang",
            calories = 62.0,
            carbs = 8.5,
            fat = 2.9,
            protein = 1.6,
            ingredients = "1 sdm selai kacang\n" +
                    "5 sdm biji wijen kering utuh\n" +
                    "7\n" +
                    "8 g stevia sweetener\n" +
                    "5 ml fiber creme\n" +
                    "40 g tepung beras\n" +
                    "60 g tepung tapioka\n" +
                    "170 gram ubi ungu rebus",
            cookingSteps = "Kukus ubi lalu haluskan\n" +
                    "setelah halus Ini dengan fiber crime\n" +
                    "selai kacang dan gula stevia. Aduk sampai rata.\n" +
                    "Campur tepung beras dan tapioka dengan air panas 4sdm\n" +
                    "aduk sampai kalis diamkan 10 menit . Seharusnya tepung ketan putih karena gak punya pakai yg ada.\n" +
                    "Bagi menjadi 12 bagian tepungnya\n" +
                    "masukan isian ubinya bulat dan gepengkan. Lalu panggang selama 1 jam di suhu 120°c.\n" +
    "Onde-onde ubi panggang siap disajikan.",
    recipePictures = "onde_onde_panggang",
    mealType = 3
    ),
    Recipe(
    recipeId = 206,
    name = "Sup Telur Jagung",
    calories = 216.0,
    carbs = 0.0,
    fat = 7.4,
    protein = 8.5,
    ingredients = "1 irisan tipis ayam\n" +
    "1 besar telur rebus\n" +
    "100 gram wortel dimasak\n" +
    "100 gram bawang bombay dimasak\n" +
    "1 sdt garam\n" +
    "1 mangkok jagung manis kuning\n" +
    "1 mangkok daun bawang\n" +
    "1 sdt bawang putih cincang\n" +
    "600 ml air mineral",
    cookingSteps = "Tumis bawang putih & bombay.\n" +
    "Jika sudah harum masukan kurleb air.\n" +
    "Masukan ayam\n" +
    "wortel\n" +
    "jagung\n" +
    "diamkan kurleb 20menit.\n" +
    "Lalu kocok telur lalu masukan\n" +
    "setelah itu masukan jg daun bawang.\n" +
    "Tambahkan garam dan merica setelah itu dicicip.\n" +
    "Sudah biss dihidangkan.",
    recipePictures = "sup_telur_jagung",
    mealType = 3
    ),
    Recipe(
    recipeId = 208,
    name = "Perdekel Tahu Bawang",
    calories = 42.0,
    carbs = 1.2,
    fat = 3.4,
    protein = 2.4,
    ingredients = "1 besar telur\n" +
    "10 gram bawang putih\n" +
    "10 gram daun bawang\n" +
    "15 gram bawang merah\n" +
    "150 gram tahu\n" +
    "1 sdm virgin coconut oil",
    cookingSteps = "Iris bawang-bawangan\n" +
    "kemudian panggang hingga harum.\n" +
    "Hancurkan tahu\n" +
    "tambahkan bawang-bawangan.\n" +
    "Masukan telur ke dalam adonan tahu.\n" +
    "Tambahkan garam\n" +
    "merica\n" +
    "dan kaldu jamur.\n" +
    "Panaskan 1sdm vco di teflon anti lengket.\n" +
    "Mulai dry frying adonan\n" +
    "kurleb 1 sdm adonan setiap goreng perkedel.\n" +
    "Tunggu hingga ke coklatan\n" +
    "dan angkat. Perkedel siap dinikmati.",
    recipePictures = "perkedel_tahu_bawang",
    mealType = 3
    ),
    Recipe(
    recipeId = 211,
    name = "Kare Capit Kepiting",
    calories = 205.0,
    carbs = 13.2,
    fat = 5.4,
    protein = 26.8,
    ingredients = "500 gram kepiting\n" +
    "1/4 ikat ketumbar\n" +
    "1 sdt bubuk kari\n" +
    "20 gram cabai merah atau rawit\n" +
    "3 siung bawang putih\n" +
    "5 kecil bawang merah\n" +
    "5\n" +
    "50 ml minyak goreng\n" +
    "15 ml sun kara",
    cookingSteps = "Tumis bumbu halus hingga wangi.\n" +
    "Masukkan air secukupnya.\n" +
    "Masukkan kara.\n" +
    "Masukkan kepiting\n" +
    "garam dan kaldu bubuk\n" +
    "tambahkan cabe rawit utuh.\n" +
    "Tes rasa.",
    recipePictures = "kare_capit_kepiting",
    mealType = 3
    ),
    Recipe(
    recipeId = 214,
    name = "Ayam Tempe Penyet",
    calories = 458.0,
    carbs = 31.4,
    fat = 18.4,
    protein = 47.9,
    ingredients = "210 gram dada ayam\n" +
    "30 gram cabai merah atau rawit\n" +
    "1/2 sdt garam\n" +
    "4 siung bawang putih\n" +
    "138 gram tempe\n" +
    "4 kecil bawang merah\n" +
    "5 g terasi",
    cookingSteps = "Ulek semua bumbu atau blender ditambahkan air.\n" +
    "Setelah halus masukan ke panci\n" +
    "masukan ayam dan tempe\n" +
    "dan air.\n" +
    "Tambah garam. Dan biarkan air menyusut.\n" +
    "Hidangkan..",
    recipePictures = "ayam_tempe_penyet",
    mealType = 3
    ),
    Recipe(
    recipeId = 223,
    name = "Sawi Gulung",
    calories = 44.0,
    carbs = 1.6,
    fat = 2.5,
    protein = 4.1,
    ingredients = "2 besar telur\n" +
    "5 gram kaldu jamur\n" +
    "200 gram tahu\n" +
    "250 gram sawi hijau\n" +
    "1/2 tbsp saus tiram\n" +
    "Cara memasak",
    cookingSteps = "Rebus lembaran sawi sampai layu\n" +
    "kemudian rebus 1 buah telur\n" +
    "kemudian potong mnjadi 8 bagian.\n" +
    "Hancurkan tahu.\n" +
    "Masukkan telur mentah\n" +
    "saus tiram dan kaldu jamur.\n" +
    "Ambil selembar sawi\n" +
    "masukan adonan tahu dan telur rebus\n" +
    "gulung seperti menggulung risoles.\n" +
    "Lakukan sampai adonan tahu habis.\n" +
    "Kukus kurang lebih 10 menit.",
    recipePictures = "sawi_gulung",
    mealType = 3
    ),
    Recipe(
    recipeId = 225,
    name = "Dragon Fruit Jelly",
    calories = 132.0,
    carbs = 32.5,
    fat = 0.9,
    protein = 1.9,
    ingredients = "2 gelas air\n" +
    "500 g buah naga\n" +
    "3\n" +
    "75 g jelly powder anggur",
    cookingSteps = "Rebus nutrijell rasa apa pun dalam 2 gelas air. Rebus hingga mendidih .. Setelah masak\n" +
    "diamkan 3 menit\n" +
    "lalu tambahkan asam buah dan biarkan dingin.\n" +
    "Belah buah naga menjadi dua\n" +
    "keluarkan isinya lalu potong dadu\n" +
    "usahakan kulit buah naga masih tersisa.\n" +
    "Potong Nutrijell dingin menjadi kubus\n" +
    "lalu sajikan dengan irisan buah naga di atas kulit buah naga. Selamat menikmati.",
    recipePictures = "dragon_fruit_jelly",
    mealType = 3
    ),
    Recipe(
    recipeId = 226,
    name = "Dimsum Diet",
    calories = 52.0,
    carbs = 5.9,
    fat = 1.5,
    protein = 3.7,
    ingredients = "100 gram dada ayam\n" +
    "15 kecil pangsit polos\n" +
    "1 besar putih telur\n" +
    "1 sdt garam\n" +
    "50 gram wortel\n" +
    "3 siung bawang putih\n" +
    "3 kecil bawang merah\n" +
    "125 gram tahu\n" +
    "230 g royco kaldu rasa sapi\n" +
    "3 g lada putih bubuk",
    cookingSteps = "Perajang / haluskan bawang putih\n" +
    "bawang bombay\n" +
    "dada ayam\n" +
    "tahu\n" +
    "garam\n" +
    "merica\n" +
    "putih telur\n" +
    "dan kaldu.\n" +
    "Parut semua wortel dengan parutan rujak / potong korek api tipis2.\n" +
    "Isi kulit pangsit dengan isian ayam tahu yg sudah di haluskan.. Kemudian kukus sampai matang kurang lebih 15 menit . Siap di santap dan di nikmati.",
    recipePictures = "dimsum_diet",
    mealType = 3
    ),
    Recipe(
    recipeId = 239,
    name = "Klapertart",
    calories = 176.0,
    carbs = 21.0,
    fat = 9.4,
    protein = 3.3,
    ingredients = "3 besar telur\n" +
    "1 sdt kayu manis\n" +
    "1/4 sdt garam\n" +
    "1/4 sdt ekstrak vanila\n" +
    "50 gram kismis (tanpa biji)\n" +
    "200 gram kelapa\n" +
    "300 gram air kelapa\n" +
    "100 gram gula pasir\n" +
    "2 sdm mentega\n" +
    "300 ml susu uht full cream\n" +
    "60 g tepung pati jagung",
    cookingSteps = "Campur susu\n" +
    "air kelapa\n" +
    "maizena\n" +
    "vanila\n" +
    "garam\n" +
    "kuning telur\n" +
    "gula. Aduk hingga mengental.\n" +
    "Tambah blue band\n" +
    "kelapa muda kismis.\n" +
    "Letakan di 3/4 wadahnya.\n" +
    "Kocok putih telur dengan gula pasir.\n" +
    "Letakkan diatas adonan pertama.\n" +
    "Panggang dengan suhu 170 derajat selama 20menit.",
    recipePictures = "klapertart",
    mealType = 3
    ),
    Recipe(
    recipeId = 268,
    name = "Agar-agar Kukus Coklat",
    calories = 113.0,
    carbs = 16.0,
    fat = 4.4,
    protein = 2.4,
    ingredients = "1 besar telur (utuh)\n" +
    "10 gram kacang sangrai kering (dengan garam)\n" +
    "25 gram gula pasir\n" +
    "7 g agar agar powder\n" +
    "35 g white coffee less sugar\n" +
    "9 g agarasa coklat",
    cookingSteps = "Kocok telur sampai mengembang.\n" +
    "Tuang bubuk agarasa\n" +
    "agar2putih dan bubuk kopi. Aduk rata.\n" +
    "Panaskan kukusan.\n" +
    "Masukkan kedalam cetakan tambahkan kacang secukupnya.\n" +
    "Kukus 10-15menit. Angkat.",
    recipePictures = "agar_agar_kukus_coklat",
    mealType = 3
    ),
    Recipe(
    recipeId = 270,
    name = "Sup Daging Tahu",
    calories = 269.0,
    carbs = 22.5,
    fat = 14.2,
    protein = 14.4,
    ingredients = "150 gram daging sapi\n" +
    "1 sdt jinten\n" +
    "1 sdt garam\n" +
    "125 gram wortel\n" +
    "100 gram jagung manis kuning\n" +
    "3 siung bawang putih\n" +
    "1 sdt jahe\n" +
    "1 daun bawang\n" +
    "700 ml air\n" +
    "20 gram bawang merah\n" +
    "2 sdm minyak goreng\n" +
    "100 gram tahu\n" +
    "230 g royco kaldu rasa sapi\n" +
    "3 g lada putih bubuk\n" +
    "150 gram kentang rebus\n" +
    "60 gram bawang bombay",
    cookingSteps = "Daging sapi sebelumnya sudah direbus terlebih dahulu.\n" +
    "Halus lada\n" +
    "jintan\n" +
    "jahe\n" +
    "bawang putih dan bawang merah.\n" +
    "Tumis bumbu halus\n" +
    "tambahkan bawang Bombay yang sudah di potong-potong\n" +
    "setelah itu tambahkan air biarkan sampai mendidih.\n" +
    "Tambah semua bahan\n" +
    "test rasa.\n" +
    "Masak sampai mendidih.",
    recipePictures = "sup_daging_tahu",
    mealType = 3
    ),
    Recipe(
    recipeId = 271,
    name = "Soto Ayam",
    calories = 121.0,
    carbs = 16.2,
    fat = 1.9,
    protein = 10.6,
    ingredients = "178 gram dada ayam rebus (kulit tidak dimakan)\n" +
    "200 gram kecambah\n" +
    "2 gram ketumbar\n" +
    "2 sdt kunyit\n" +
    "1 sdt garam\n" +
    "100 gram selada kol\n" +
    "3 siung bawang putih\n" +
    "1 sdt jahe\n" +
    "1 mangkok daun bawang\n" +
    "2 gram sereh\n" +
    "2 gram kacang kemiri\n" +
    "2 kecil bawang merah\n" +
    "500 g mie basah matang\n" +
    "1 sdm kecap manis\n" +
    "2 g royco ayam\n" +
    "3 g lada putih bubuk",
    cookingSteps = "Haluskan bawmer\n" +
    "bawput\n" +
    "jahe\n" +
    "kemiri\n" +
    "kunyit dan geprek laos dan sereh.\n" +
    "Tumis bumbu halus tadi bersama dgn laos dan sereh yg sudah di geprek dan tambah duan salam dan daun jeruk.\n" +
    "Setelah matang\n" +
    "masukan air dan air sisa rebusan ayam.\n" +
    "Rebus ayam\n" +
    "tauge\n" +
    "mie shirataki Secara terpisah.\n" +
    "Setelah air mendidih\n" +
    "masukan bumbu spt garam\n" +
    "lada bubuk\n" +
    "royko dan kecap manis 1sdm Setelah di rasa pas\n" +
    "matikan api kompor.\n" +
    "Siapkan ayam suir\n" +
    "tauge\n" +
    "kol di iris\n" +
    "jeruk nipis\n" +
    "dan mi shirataki nya.\n" +
    "Kalo mau pake sambel\n" +
    "tinggal haluskan rawit dan juga air kuah sotonya.\n" +
    "Selesai.",
    recipePictures = "soto_ayam",
    mealType = 3
    ),
    Recipe(
    recipeId = 278,
    name = "Sup Bening",
    calories = 162.0,
    carbs = 22.7,
    fat = 5.5,
    protein = 5.9,
    ingredients = "1 besar boiled egg\n" +
    "1/4 mangkok\n" +
    "dicincang broccoli\n" +
    "1/2 sdt minced garlic\n" +
    "1/4 scallops\n" +
    "1/4 ons cabbage (with salt\n" +
    "drained\n" +
    "cooked\n" +
    "boiled)\n" +
    "1/2 kecil cooked carrots\n" +
    "1 medium boiled potato",
    cookingSteps = "Potong semua bahan.\n" +
    "Rebus air hingga mendidih lalu masukkan bawang merah dan bawang putih.\n" +
    "Setelah 3 menit masukkan kentang dan wortel.\n" +
    "Setelah wortel dan kentang setengah matang masukkan brokoli dan kol.\n" +
    "Taburkan sedikit garam. Menyajikan.",
    recipePictures = "sup_bening",
    mealType = 3
    ),
    Recipe(
    recipeId = 288,
    name = "Puding Chia",
    calories = 76.0,
    carbs = 13.6,
    fat = 0.7,
    protein = 4.5,
    ingredients = "8 lengkeng\n" +
    "50 gram mangga\n" +
    "8 gram chia seed\n" +
    "3\n" +
    "5 g agar agar powder\n" +
    "500 ml skimmed milk (250ml)\n" +
    "2 sdm brown sugar\n" +
    "4 sedang stroberi",
    cookingSteps = "Membuat puding chia seed: campur chia seed dengan air atau susu lalu aduk rata dan simpan di lemari es. Diamkan 1 jam kemudian biji chia akan mengembang. Just adjust the water/milk according to the chia seeds.\n" +
    "Sambil menunggu puding chia dingin\n" +
    "sesuaikan air/susu sesuai dengan biji chia. Tuang susu\n" +
    "gelatin\n" +
    "dan gula merah ke dalam panci.\n" +
    "Masak dengan api kecil sambil diaduk2 sampai mendidih.\n" +
    "Setelah mendidih tuang ketempat yg telah disediakan.\n" +
    "Tunggu hingga puding set beri puding chia seed dan buah2an yang sudah dipotong2 lalu simpan dikulkas.",
    recipePictures = "puding_chia",
    mealType = 3
    ),
    Recipe(
    recipeId = 305,
    name = "Protein Bar Kacang Tanah",
    calories = 72.0,
    carbs = 9.0,
    fat = 3.6,
    protein = 2.2,
    ingredients = "100 gram roasted unsalted peanuts\n" +
    "25 gram raisins\n" +
    "75 gram honey\n" +
    "50 g instant oatmeal",
    cookingSteps = "Giling kacang goreng.\n" +
    "Campurkan semua bahan dan kacau sehingga menjadi doh.\n" +
    "Masukkan ke dalam cetakan/tutup dengan plastik cling dan potong menjadi batangan.\n" +
    "Dinginkan di lemari es.",
    recipePictures = "protein_bar_kacang_tanah",
    mealType = 3
    ),
    )

    val recommendations = listOf(
        Recommendation(
            recommendationId = 1,
            date = Date(System.currentTimeMillis()), //hari ini / literally sekarang
            isSelected = 0,
            recipeId = 3
        ),
        Recommendation(
            recommendationId = 2,
            date = Date(System.currentTimeMillis() - 86400000),
            isSelected = 0,
            recipeId = 10
        ),
        Recommendation(
            recommendationId = 3,
            date = Date(System.currentTimeMillis() + 86400000),
            isSelected = 0,
            recipeId = 15
        ),
        Recommendation(
            recommendationId = 4,
            date = Date(System.currentTimeMillis() + 86400000 * 2),
            isSelected = 0,
            recipeId = 20
        ),
        Recommendation(
            recommendationId = 5,
            date = Date(System.currentTimeMillis() + 86400000 * 2),
            isSelected = 0,
            recipeId = 24
        ),
        Recommendation(
            recommendationId = 6,
            date = Date(System.currentTimeMillis() + 86400000 * 2),
            isSelected = 0,
            recipeId = 19,
        ),
        Recommendation(
            recommendationId = 7,
            date = Date(System.currentTimeMillis() + 86400000 * 2),
            isSelected = 0,
            recipeId = 19
        ),
        Recommendation(
            recommendationId = 8,
            date = Date(System.currentTimeMillis() + 86400000),
            isSelected = 0,
            recipeId = 14
        )
    )
}
