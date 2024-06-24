package com.telyu.nourimate.data.local

import com.telyu.nourimate.data.local.models.RecipeHistory
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.GeneralUtil
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
        val cookTime: String,
        val prepTime: String,
        val portion: Int,
    )

    data class Recommendation(
        val recommendationId: Int,
        val date: String,
        val isSelected: Int,
        val recipeId: Int,
        val userId: Int,
    )

    val recipes = listOf(
        Recipe(
            recipeId = 1,
            name = "Roti Pisang Oat",
            calories = 193.0,
            carbs = 29.77,
            fat = 6.93,
            protein = 4.28,
            ingredients = "2 besar telur\n" +
                    "2 besar pisang\n" +
                    "1 tsp baking powder (sodium aluminum sulfate, double acting)\n" +
                    "6 sdm honey\n" +
                    "170 g quick cooking oatmeal\n" +
                    "54 g roasted almond\n" +
                    "100 g choco chips\n" ,
            cookingSteps = "1. Panaskan oven terlebih dahulu pada suhu 170°C.\n" +
                    "2. Hancurkan pisang, campur dengan telur dan madu.\n" +
                    "3. Giling oatmeal dan campur dengan baking powder.\n" +
                    "4. Aduk bahan basah dan kering hingga rata, tambahkan coklat keping tapi sisakan sedikit untuk topping.\n" +
                    "5. Tuang ke dalam loyang dan taburi dengan sisa choco chips dan almond. Panggang selama 40 menit atau sampai matang.\n" ,
            recipePictures = "roti_pisang_oat",
            mealType = 3,
            cookTime = "40 menit",
            prepTime = "10 menit",
            portion = 12
        ),
Recipe(
            recipeId = 2,
            name = "Donat",
            calories = 106.0,
            carbs = 19.3,
            fat = 1.73,
            protein = 3.7,
            ingredients = "3 gram ragi\n" +
                    "40 gram kuning telur\n" +
                    "30 gram gula pasir\n" +
                    "130 ml susu uht full cream\n" +
                    "15 g susu\n" +
                    "250 g tepung terigu cakra kembar\n" ,
            cookingSteps = "1. Campur semua bahan kering.\n" +
                    "2. Tambahkan semua bahan basah dan uleni hingga halus.\n" +
                    "3. Diamkan hingga ukurannya dua kali lipat.\n" +
                    "4. Bentuk adonan dan goreng hingga berwarna cokelat keemasan.\n" ,
            recipePictures = "donat",
            mealType = 1,
            cookTime = "15 menit",
            prepTime = "20 menit",
            portion = 12
        ),
Recipe(
            recipeId = 3,
            name = "Muffin Oat Pisang",
            calories = 136.0,
            carbs = 23.62,
            fat = 2.98,
            protein = 4.02,
            ingredients = "1 sedang telur\n" +
                    "1 sdt ekstrak vanila\n" +
                    "154 gram pisang\n" +
                    "5 gram baking soda\n" +
                    "6 gram madu\n" +
                    "100 g quick cooking oatmeal\n" +
                    "50 g squeeze yoghurt\n" ,
            cookingSteps = "1. Masukan semua bahan pada blender.\n" +
                    "2. Setelah tercampur, bagi adonan ke dalam cetakan.\n" +
                    "3. Panggang dengan suhu 175°C selama 25 menit.\n" ,
            recipePictures = "muffin_oat_pisang",
            mealType = 3,
            cookTime = "25 menit",
            prepTime = "10 menit",
            portion = 5
        ),
Recipe(
            recipeId = 4,
            name = "Banana Bread Oats",
            calories = 247.0,
            carbs = 32.96,
            fat = 8.53,
            protein = 8.84,
            ingredients = "2 besar telur\n" +
                    "1 sdt kayu manis\n" +
                    "115 g banana\n" +
                    "120 g instant oatmeal grain\n" +
                    "120 ml almond breeze vanilla\n" +
                    "10 g baking powder\n" +
                    "13 g 1/3 pb original\n" +
                    "20 g fineza chocolate button\n" ,
            cookingSteps = "1. Panaskan oven/airfryer dengan suhu 160°C selama 10 menit.\n" +
                    "2. Sementara itu, campurkan semua bahan dalam blender, kecuali coklat kancing.\n" +
                    "3. Blender adonan hingga halus. Tuang ke dalam cangkir aluminium dan hiasi tombol cokelat di atasnya.\n" +
                    "4. Panggang adonan dengan suhu 160°C selama 15 menit.\n" ,
            recipePictures = "banana_bread_oats",
            mealType = 1,
            cookTime = "15 menit",
            prepTime = "15 menit",
            portion = 4
        ),
Recipe(
            recipeId = 5,
            name = "Bilah Energi Oatmeal Cokelat",
            calories = 92.0,
            carbs = 13.29,
            fat = 3.61,
            protein = 2.06,
            ingredients = "25 gram biji bunga matahari (yang) dikuliti sangrai kering\n" +
                    "60 gram kurma\n" +
                    "40 gram kismis (tanpa biji)\n" +
                    "20 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "20 g choco chips\n" +
                    "60 g selai kacang\n" +
                    "150 g oatmeal\n" +
                    "100 g madu tj murni\n" ,
            cookingSteps = "1. Sangrai oatmeal sampai kecoklatan. Dinginkan sampai suhu ruang.\n" +
                    "2. Cincang kurma dengan halus.\n" +
                    "3. Campur semua bahan dalam satu wadah menggunakan spatula.\n" +
                    "4. Pindahkan pada wadah, padatkan, tutup rapat. Simpan pada frezer minimal 30 menit.\n" +
                    "5. Potong menjadi batangan.\n" ,
            recipePictures = "bilah_energi_oatmeal_cokelat",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "30 menit",
            portion = 16
        ),
Recipe(
            recipeId = 8,
            name = "Kue Gandum Pisang",
            calories = 103.0,
            carbs = 19.26,
            fat = 1.93,
            protein = 3.18,
            ingredients = "30 gram susu\n" +
                    "1 ekstra besar telur\n" +
                    "175 gram pisang\n" +
                    "1 sdm madu\n" +
                    "50 gram tepung terigu putih (semua keperluan)\n" +
                    "10 g bubuk pengembang\n" +
                    "80 g gulungan gandum\n" ,
            cookingSteps = "1. Kupas dan haluskan pisang. Campur dengan telur, susu, dan madu.\n" +
                    "2. Tambahkan bahan kering dan aduk rata.\n" +
                    "3. Pindahkan ke loyang dan masak dalam oven dengan suhu 180°C selama 30 menit.\n" +
                    "4. Biarkan dingin, buka cetakannya, dan potong-potong.\n" ,
            recipePictures = "kue_gandum_pisang",
            mealType = 3,
            cookTime = "30 menit",
            prepTime = "30 menit",
            portion = 8
        ),
Recipe(
            recipeId = 9,
            name = "Gandum dengan Buah",
            calories = 251.0,
            carbs = 50.22,
            fat = 3.8,
            protein = 4.57,
            ingredients = "1 sdm madu\n" +
                    "1 gelas yogurt stroberi\n" +
                    "3 sdm gandum\n" +
                    "100 g buah naga\n" +
                    "100 g apel\n" ,
            cookingSteps = "1. Masak gandum dengan air sesuai kemasan.\n" +
                    "2. Pindahkan ke mangkuk lalu tambahkan yogurt.\n" +
                    "3. Iris buah dan tambahkan ke mangkuk. Gerimis dengan madu.\n" ,
            recipePictures = "gandum_dengan_buah",
            mealType = 3,
            cookTime = "10 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 10,
            name = "Sandwich Telur Dadar",
            calories = 306.0,
            carbs = 36.48,
            fat = 11.59,
            protein = 13.63,
            ingredients = "1 daun sedang selada\n" +
                    "1 besar telur dadar\n" +
                    "1 utuh sedang tomat merah\n" +
                    "1 lembar roti tawar gandum\n" ,
            cookingSteps = "1. Panggang roti dalam wajan antilengket selama satu menit di setiap sisi.\n" +
                    "2. Atur telur dadar dan sayuran di dalam roti.\n" +
                    "3. Iris dan sajikan.\n" ,
            recipePictures = "sandwich_telur_dadar",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "15 menit",
            portion = 1
        ),
Recipe(
            recipeId = 11,
            name = "Banana Oats Coklat",
            calories = 314.0,
            carbs = 45.52,
            fat = 10.7,
            protein = 12.27,
            ingredients = "61 gram telur\n" +
                    "1 sdt ekstrak vanila\n" +
                    "157 gram pisang\n" +
                    "1 sdt baking soda\n" +
                    "11 gram kakao bubuk (tanpa gula)\n" +
                    "6 g unsalted butter\n" +
                    "49 g oat\n" +
                    "200 ml susu uht low fat\n" +
                    "6 g choco chips\n" ,
            cookingSteps = "1. Haluskan pisang.\n" +
                    "2. Tambahkan oat, coklat bubuk, vanilla, dan baking soda, lalu aduk.\n" +
                    "3. Setelah adonan tercampur rata, masukkan susu, telur dan mentega cair.\n" +
                    "4. Tuang ke dalam cetakan yang dialasi kertas roti dan taburi choco chips.\n" +
                    "5. Panggang dengan suhu 180°C selama 20 menit.\n" ,
            recipePictures = "banana_oats_coklat",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 12,
            name = "Wafel",
            calories = 231.0,
            carbs = 26.41,
            fat = 11.32,
            protein = 4.78,
            ingredients = "240 ml susu\n" +
                    "1/2 besar telur\n" +
                    "120 gram tepung putih\n" +
                    "1/2 sdt garam\n" +
                    "1/2 sdt ekstrak vanila\n" +
                    "10 gram gula pasir\n" +
                    "60 g mentega tawar\n" +
                    "20 g tepung pati jagung\n" +
                    "2 g bubuk pengembang\n" ,
            cookingSteps = "1. Hangatkan susu tapi tidak mendidih, tambahkan butter, aduk hingga butter meleleh, sisihkan.\n" +
                    "2. Campur bahan kering jadi satu.\n" +
                    "3. Tambahkan telur ke dalam susu lalu masukkan ekstrak vanili.\n" +
                    "4. Campur semuanya sampai halus. Diamkan selama 15 menit.\n" +
                    "5. Masak dalam waffle iron hingga berwarna keemasan.\n" +
                    "6. Sajikan dengan buah atau topping yang Anda inginkan.\n" ,
            recipePictures = "wafel",
            mealType = 2,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 5
        ),
Recipe(
            recipeId = 15,
            name = "Kue Oat Pisang",
            calories = 153.0,
            carbs = 23.24,
            fat = 4.96,
            protein = 4.04,
            ingredients = "100 g telur\n" +
                    "1 porsi minyak nabati\n" +
                    "250 g oat\n" +
                    "45 ml low fat milk\n" +
                    "30 g choco chips\n" +
                    "200 gram pisang raja\n" ,
            cookingSteps = "1. Haluskan pisang raja.\n" +
                    "2. Tambahkan telur, minyak, dan susu. Campur dengan baik.\n" +
                    "3. Tambahkan oat giling dan 15g keping cokelat, lalu aduk rata.\n" +
                    "4. Tuang adunan ke dalam acuan dan hiaskan baki choco chips. Bakar selama kira-kira 30 minit pada suhu 150°C.\n" +
                    "5. Biarkan dingin lalu iris.\n" ,
            recipePictures = "kue_oat_pisang",
            mealType = 3,
            cookTime = "30 menit",
            prepTime = "15 menit",
            portion = 11
        ),
Recipe(
            recipeId = 16,
            name = "Kue Pisang Kukus",
            calories = 181.0,
            carbs = 21.89,
            fat = 9.44,
            protein = 4.32,
            ingredients = "170 gram putih telur\n" +
                    "270 gram pisang\n" +
                    "10 gram chia seed\n" +
                    "5 gram kacang almond\n" +
                    "1 sdt, bongkahan gula merah\n" +
                    "75 g salted butter\n" +
                    "120 g oatmeal rolled oats\n" +
                    "75 g palm sugar\n" +
                    "60 g dark chocolate chips\n" +
                    "27 g susu\n" ,
            cookingSteps = "1. Kocok putih telur dengan 2 gula.\n" +
                    "2. Masukkan pisang lecek, oat, dan biji chia.\n" +
                    "3. Tambahkan mentega cair dan susu. Campur dengan baik.\n" +
                    "4. Tempatkan ke dalam cetakan dan hiasi dengan keping cokelat dan almond cincang. Kukus selama 25 menit.\n" ,
            recipePictures = "kue_pisang_kukus",
            mealType = 3,
            cookTime = "25 menit",
            prepTime = "15 menit",
            portion = 12
        ),
Recipe(
            recipeId = 19,
            name = "Tahu Dihancurkan dengan Telur Orak Arik",
            calories = 156.0,
            carbs = 6.43,
            fat = 11.34,
            protein = 8.91,
            ingredients = "110 gram telur\n" +
                    "16 gram cabai merah atau rawit\n" +
                    "10 gram bawang putih\n" +
                    "30 gram daun bawang\n" +
                    "2 sdm minyak goreng\n" +
                    "232 gram tahu\n" +
                    "35 gram bawang bombay\n" ,
            cookingSteps = "1. Tuangkan minyak, kemudian tumis bawang putih, bawang bombay, dan rawit.\n" +
                    "2. Setelah berwarna sedikit kecoklatan, masukkan telur dan diorak arik.\n" +
                    "3. Tambahkan tahu dan hancurkan. Tuangkan air secukupnya.\n" +
                    "4. Bumbui sesuai selera.\n" +
                    "5. Masukkan bawang daun dan sajikan.\n" ,
            recipePictures = "tahu_dihancurkan_dengan_telur_orak_arik",
            mealType = 2,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 4
        ),
Recipe(
            recipeId = 22,
            name = "Nasi Goreng Sayuran",
            calories = 416.0,
            carbs = 70.19,
            fat = 8.87,
            protein = 15.58,
            ingredients = "1 besar telur\n" +
                    "1 mangkok,masak nasi putih\n" +
                    "100 gram wortel\n" +
                    "100 gram selada kol\n" +
                    "1 sdm sambal goreng\n" +
                    "100 gram sawi hijau\n" ,
            cookingSteps = "1. Dalam wajan antilengket, kocok telur.\n" +
                    "2. Tambahkan nasi dan saus sambal. Mengaduk.\n" +
                    "3. Masukkan sayuran dan aduk rata.\n" +
                    "4. Bumbui sesuai selera.\n" ,
            recipePictures = "nasi_goreng_sayuran",
            mealType = 2,
            cookTime = "10 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 24,
            name = "Roti Pisang Havermut",
            calories = 232.0,
            carbs = 39.64,
            fat = 5.86,
            protein = 7.3,
            ingredients = "50 g telur\n" +
                    "1 sejumput garam\n" +
                    "1/2 sdt ekstrak vanila\n" +
                    "120 gram pisang\n" +
                    "1/2 sdt baking soda\n" +
                    "1 sdm madu\n" +
                    "30 g havermut instan\n" +
                    "20 g cokelat hitam & granola almond\n" ,
            cookingSteps = "1. Hancurkan pisang dan campur dengan telur, vanila, dan madu.\n" +
                    "2. Tambahkan oatmeal, garam, dan soda kue.\n" +
                    "3. Tuang ke dalam cetakan roti dan taburi dengan granola.\n" +
                    "4. Panggang dalam oven dengan suhu 180°C selama 20 menit atau hingga adonan tidak lengket lagi.\n" +
                    "5. Biarkan dingin sebelum membuka cetakan dan mengiris.\n" ,
            recipePictures = "roti_pisang_havermut",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "20 menit",
            portion = 2
        ),
Recipe(
            recipeId = 25,
            name = "Goreng Labu dengan Jagung",
            calories = 121.0,
            carbs = 19.07,
            fat = 3.93,
            protein = 3.82,
            ingredients = "1 besar telur\n" +
                    "1 gelas jagung\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "1 sdt, tumbuk oregano\n" +
                    "1 sejumput lada hitam\n" +
                    "100 gram labu\n" +
                    "1 sdm minyak zaitun extra virgin\n" +
                    "100 g tepung terigu\n" ,
            cookingSteps = "1. Potong dadu labu, rebus dengan air hingga empuk, tiriskan.\n" +
                    "2. Campur dalam blender dengan bahan lain, kecuali tepung dan minyak.\n" +
                    "3. Masukkan tepung dan aduk hingga rata.\n" +
                    "4. Masak dalam wajan dengan minyak sampai berwarna keemasan dan kencang.\n" ,
            recipePictures = "goreng_labu_dengan_jagung",
            mealType = 2,
            cookTime = "15 menit",
            prepTime = "20 menit",
            portion = 6
        ),
Recipe(
            recipeId = 26,
            name = "Roti Pisang dan Keju",
            calories = 280.0,
            carbs = 41.5,
            fat = 8.75,
            protein = 9.0,
            ingredients = "100 g pisang\n" +
                    "30 g keju quick leleh\n" +
                    "1 lembar roti tawar gandum\n" ,
            cookingSteps = "1. Iris pisang dan parut keju.\n" +
                    "2. Susun di atas roti.\n" +
                    "3. Masak dalam penggorengan udara pada suhu 190°C selama 5 menit.\n" ,
            recipePictures = "roti_pisang_dan_keju",
            mealType = 3,
            cookTime = "5 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 27,
            name = "Pancake Protein Oat",
            calories = 86.0,
            carbs = 8.3,
            fat = 1.62,
            protein = 8.57,
            ingredients = "1 besar telur\n" +
                    "1/2 gelas air\n" +
                    "35 g oat gulung biasa\n" +
                    "2 ml pemanis\n" +
                    "1 sendok air dadih coklat\n" +
                    "3 g bubuk pengembang\n" ,
            cookingSteps = "1. Giling oat hingga menjadi bubuk lalu campurkan dengan bahan kering lainnya.\n" +
                    "2. Campur semuanya sampai halus.\n" +
                    "3. Masak dalam wajan antilengket sampai keras di kedua sisi.\n" ,
            recipePictures = "pancake_protein_oat",
            mealType = 1,
            cookTime = "5 menit",
            prepTime = "10 menit",
            portion = 4
        ),
Recipe(
            recipeId = 28,
            name = "Brownies Oatmeal",
            calories = 165.0,
            carbs = 26.59,
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
                    "4 g baking powder\n" ,
            cookingSteps = "1. Giling oatmeal dalam blender. Menyisihkan.\n" +
                    "2. Tumbuk pisang.\n" +
                    "3. Campurkan semua bahan dengan baik.\n" +
                    "4. Masukkan adunan ke dalam acuan dan kukus selama 15 minit.\n" +
                    "5. Biarkan ia sejuk, buang acuan dan potong.\n" ,
            recipePictures = "brownies_oatmeal",
            mealType = 3,
            cookTime = "15 menit",
            prepTime = "20 menit",
            portion = 4
        ),
Recipe(
            recipeId = 30,
            name = "Salad Sayur dengan Ayam, Tahu, dan Tempe",
            calories = 195.0,
            carbs = 9.58,
            fat = 9.88,
            protein = 18.78,
            ingredients = "70 gram daging ayam (panggang, bakar, dimasak)\n" +
                    "100 gram kubis\n" +
                    "100 gram selada daun hijau\n" +
                    "50 gram tempe\n" +
                    "100 gram tahu\n" +
                    "20 g saus salad pulau seribu\n" ,
            cookingSteps = "1. Potong-potong ayam, tahu, dan tempe. Masak dalam wajan antilengket sampai empuk.\n" +
                    "2. Masukkan semua bahan ke dalam mangkuk.\n" +
                    "3. Sajikan sampai segar.\n" ,
            recipePictures = "salad_sayur_dengan_ayam,_tahu,_dan_tempe",
            mealType = 2,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 31,
            name = "Bihun dengan Sayuran",
            calories = 242.0,
            carbs = 47.79,
            fat = 3.85,
            protein = 4.35,
            ingredients = "60 gram carrots\n" +
                    "60 gram broccoli (with salt, drained, cooked, boiled)\n" +
                    "55 g bihun rasa ayam bawang\n" ,
            cookingSteps = "1. Masak mie sesuai petunjuk kemasan.\n" +
                    "2. Rebus sayuran selama 5 menit.\n" +
                    "3. Campur mie dan sayuran, bumbui sesuai keinginan, dan sajikan.\n" ,
            recipePictures = "bihun_dengan_sayuran",
            mealType = 1,
            cookTime = "10 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 35,
            name = "Smoothie Buah Naga dan Pisang",
            calories = 280.0,
            carbs = 60.4,
            fat = 3.48,
            protein = 4.98,
            ingredients = "2 sedang pisang\n" +
                    "130 ml yakult\n" +
                    "1 mangkok buah naga\n" +
                    "20 g cinnamon & raisin\n" +
                    "70 g squeeze yogurt peach\n" ,
            cookingSteps = "1. Potong pisang dan buah naga.\n" +
                    "2. Masukkan kesemuanya ke dalam pengisar dan kisar sehingga sebati.\n" +
                    "3. Tempatkan dalam mangkuk dan hiasi dengan granola dan buah sesuai keinginan. Nikmati sarapan Anda.\n" ,
            recipePictures = "smoothie_buah_naga_dan_pisang",
            mealType = 3,
            cookTime = "0 menit",
            prepTime = "15 menit",
            portion = 2
        ),
Recipe(
            recipeId = 36,
            name = "Bolu Oatmeal Pisang Cokelat",
            calories = 106.0,
            carbs = 18.69,
            fat = 2.83,
            protein = 2.96,
            ingredients = "50 gram egg\n" +
                    "375 gram banana\n" +
                    "10 g cocoa powder\n" +
                    "150 g whole rolled oats\n" +
                    "5 g coconut sugar\n" +
                    "50 g choco chips\n" +
                    "2.5 g baking powder\n" ,
            cookingSteps = "1. Haluskan pisang.\n" +
                    "2. Campur semua bahan.\n" +
                    "3. Panggang selama 30 menit pada suhu 170°C.\n" +
                    "4. Hasilnya jadi 2 loyang bisa dibagi 12 potong.\n" ,
            recipePictures = "bolu_oatmeal_pisang_cokelat",
            mealType = 1,
            cookTime = "30 menit",
            prepTime = "15 menit",
            portion = 12
        ),
Recipe(
            recipeId = 39,
            name = "Oat Panekuk",
            calories = 54.0,
            carbs = 7.1,
            fat = 1.87,
            protein = 2.44,
            ingredients = "1 besar telur\n" +
                    "1/4 cangkir instant oatmeal\n" +
                    "80 ml chocolate drink\n" ,
            cookingSteps = "1. Haluskan oatmeal hinggah tekstur menjadi tepung.\n" +
                    "2. Tambahkan sebutir telur dan aduk rata.\n" +
                    "3. Tambahkan chocolatos dan aduk.\n" +
                    "4. Masak di atas pan anti lengket hingga matang kedua sisinya.\n" ,
            recipePictures = "oat_panekuk",
            mealType = 1,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 5
        ),
Recipe(
            recipeId = 41,
            name = "Tumis Brokoli",
            calories = 79.0,
            carbs = 17.22,
            fat = 0.52,
            protein = 3.49,
            ingredients = "250 gram brokoli\n" +
                    "160 gram wortel\n" +
                    "3 siung bawang putih\n" +
                    "1/2 sdt gula pasir\n" +
                    "1 sejumput garam laut\n" +
                    "2 kecil bawang merah\n" +
                    "2.5 g kaldu jamur\n" ,
            cookingSteps = "1. Rebus wortel dan brokoli, tujuannya supaya empuk dan matang merata ketika ditumis.\n" +
                    "2. Tumis bawang merah dan bawang putih yang sudah dicincang pada teflon anti lengket tanpa menggunakan minyak masak.\n" +
                    "3. Setelah bumbu matang, masukkan wortel dan brokoli yang sudah direbus, aduk merata sambil ditambahkan gula, garam dan kaldu jamur.\n" +
                    "4. Tumis brokoli siap disajikan.\n" ,
            recipePictures = "tumis_brokoli",
            mealType = 3,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 3
        ),
Recipe(
            recipeId = 42,
            name = "Omlete Tahu Telur",
            calories = 232.0,
            carbs = 2.48,
            fat = 18.92,
            protein = 14.26,
            ingredients = "1 besar telur\n" +
                    "1 sdt garam\n" +
                    "100 gram tahu\n" +
                    "1 sendok makan minyak goreng sawit\n" ,
            cookingSteps = "1. Hancurkan tahu dalam mangkuk.\n" +
                    "2. Kocok telur, campur dengan tahu dan bumbui dengan garam.\n" +
                    "3. Masukkan minyak ke dalam wajan.\n" +
                    "4. Goreng hingga matang.\n" ,
            recipePictures = "omlete_tahu_telur",
            mealType = 2,
            cookTime = "10 menit",
            prepTime = "5 menit",
            portion = 1
        ),
Recipe(
            recipeId = 44,
            name = "Muesli Simple",
            calories = 255.0,
            carbs = 42.21,
            fat = 7.3,
            protein = 6.96,
            ingredients = "100 ml susu (susu murni)\n" +
                    "2 gram chia seed\n" +
                    "50 g muesli dried fruit\n" ,
            cookingSteps = "1. Masukan susu dan muesli kedalam mangkok.\n" +
                    "2. Lalu masukan ke kulkas diamkan semalaman.\n" +
                    "3. Siap disantap pada pagi hari lalu taburi chia seed diatasnya.\n" +
                    "4. Bisa ditambahkan dengan buah buahan (opsional).\n" ,
            recipePictures = "muesli_simple",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 45,
            name = "Jus Bokcoy",
            calories = 94.0,
            carbs = 24.57,
            fat = 0.18,
            protein = 1.02,
            ingredients = "1/2 fresh lime juice\n" +
                    "3 sdt sugar\n" +
                    "3 slices pineapple (extra sweet)\n" +
                    "3 daun chinese cabbage (bok-choy, pak-choi)\n" +
                    "700 ml water\n" ,
            cookingSteps = "1. Cuci buah dan sayur.\n" +
                    "2. Rendam bokcoy dalam air panas, sebentar saja.\n" +
                    "3. Blender semua bahan.\n" ,
            recipePictures = "jus_bokcoy",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "5 menit",
            portion = 2
        ),
Recipe(
            recipeId = 46,
            name = "Jus",
            calories = 35.0,
            carbs = 8.58,
            fat = 0.28,
            protein = 0.93,
            ingredients = "1/4 iris lime\n" +
                    "6 sedang strawberries\n" +
                    "1 medium carrots\n" +
                    "1 kecil utuh tomatoes\n" +
                    "1.2 l air\n" +
                    "1 sachet sweetener classic\n" ,
            cookingSteps = "1. Cuci dan potong buah dan sayur.\n" +
                    "2. Masukkan ke blender, campurkan dengan air dan pemanis, tambahkan air jeruk nipis.\n" +
                    "3. Blender hingga halus.\n" ,
            recipePictures = "jus",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "8 menit",
            portion = 2
        ),
Recipe(
            recipeId = 51,
            name = "Banana Oat Cookies",
            calories = 120.0,
            carbs = 25.29,
            fat = 2.06,
            protein = 2.57,
            ingredients = "3 kurma\n" +
                    "10 gram kismis\n" +
                    "125 gram pisang\n" +
                    "60 g havermout oatmeal instan\n" +
                    "6 g choco chips\n" ,
            cookingSteps = "1. Lumatkan pisang sampai halus dengan garpu.\n" +
                    "2. Tambahkan oat dan bahan lainnya, campur sampai menyatu.\n" +
                    "3. Ambil sesendok campuran dan letakkan di airfryer / loyang, tekan ratakan dengan garpu.\n" +
                    "4. Panggang kue dengan suhu 160°C selama 30 menit.\n" +
                    "5. Angkat dan dinginkan. Siap disajikan.\n" ,
            recipePictures = "banana_oat_cookies",
            mealType = 3,
            cookTime = "30 menit",
            prepTime = "10 menit",
            portion = 4
        ),
Recipe(
            recipeId = 53,
            name = "Healthy Banana Oat Muffin",
            calories = 459.0,
            carbs = 78.65,
            fat = 12.23,
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
                    "2100 g squeeze yoghurt\n" ,
            cookingSteps = "1. Blender oats sampai jadi halus atau merupai tepung. Bisa pakai tepung lain.\n" +
                    "2. Campur tepung oats, cocoa powder, baking soda, baking powder, kopi instan, gula dan garam.\n" +
                    "3. Haluskan pisang lalu campurkan dengan telur dan yogurt.\n" +
                    "4. Campur adonan basah dengan adonan kering.\n" +
                    "5. Masukan adonan muffin ke cup/loyang yang sudah anda sediakan.\n" +
                    "6. Tambahkan topping pilihan anda sesuai selera.\n" +
                    "7. Panggang di oven 180°C sekitar 20 - 25 menit.\n" +
                    "8. Angkat dan dinginkan.\n" ,
            recipePictures = "healthy_banana_oat_muffin",
            mealType = 1,
            cookTime = "25 menit",
            prepTime = "15 menit",
            portion = 6
        ),
Recipe(
            recipeId = 55,
            name = "Tahu Telur",
            calories = 203.0,
            carbs = 3.7,
            fat = 14.97,
            protein = 14.7,
            ingredients = "1 besar telur\n" +
                    "1 siung bawang putih dimasak\n" +
                    "1 sdt garam\n" +
                    "10 gram bayam\n" +
                    "2 gram bawang merah\n" +
                    "100 gram tahu\n" +
                    "2 ml minyak goreng\n" ,
            cookingSteps = "1. Hancurkan tahu.\n" +
                    "2. Iris kecil-kecil bayam.\n" +
                    "3. Campur tahu, bayam dengan telur.\n" +
                    "4. Haluskan bawang merah dan bawang putih beserta garam.\n" +
                    "5. Tumis bumbu halus.\n" +
                    "6. Masukkan adonan tahu dan masak hingga matang.\n" ,
            recipePictures = "tahu_telur",
            mealType = 2,
            cookTime = "10 menit",
            prepTime = "5 menit",
            portion = 1
        ),
Recipe(
            recipeId = 56,
            name = "Chia Seed Yakult",
            calories = 154.0,
            carbs = 27.62,
            fat = 3.12,
            protein = 3.64,
            ingredients = "10 gram chia seed\n" +
                    "2 botol yakult\n" +
                    "10 gram buah naga\n" ,
            cookingSteps = "1. Campurkan chia seed dengan Yakult.\n" +
                    "2. Diamkan selama 5-10 menit di lemari es.\n" +
                    "3. Tambahkan potongan buah naga.\n" ,
            recipePictures = "chia_seed_yakult",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "15 menit",
            portion = 1
        ),
Recipe(
            recipeId = 59,
            name = "Oat Cookies",
            calories = 24.0,
            carbs = 4.66,
            fat = 0.51,
            protein = 0.54,
            ingredients = "4 kurma\n" +
                    "20 gram keju cheddar\n" +
                    "1 sdt ekstrak vanila\n" +
                    "2 kecil pisang\n" +
                    "62 g oatmeal\n" ,
            cookingSteps = "1. Kupas buah pisang masukan dalam mangkok hancurkan hingga halus.\n" +
                    "2. Potong kurma dan parut keju masukan dalam mangkuk hancuran pisang tadi tambahkan vanilla paste. Tambahkan oatmeal.\n" +
                    "3. Campur adonan hingga merata cetak adonan pada loyang lalu panggang adoan di oven suhu 180°C selama kurleb 15 menitan.\n" ,
            recipePictures = "oat_cookies",
            mealType = 3,
            cookTime = "15 menit",
            prepTime = "15 menit",
            portion = 17
        ),
Recipe(
            recipeId = 60,
            name = "Bakso Ayam Oat",
            calories = 197.0,
            carbs = 15.26,
            fat = 6.18,
            protein = 19.25,
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
                    "1 sdm saos tiram\n" ,
            cookingSteps = "1. Goreng bawang merah dg minyak kelapa hingga kering.\n" +
                    "2. Campur semua bahan dalam blender kecuali daun bawang. Tambahkan daun bawang dan bawang goreng.\n" +
                    "3. Rebus air hingga mendidih,kecilkan kompor. Masukkan bakso bentuk sesuai selera.\n" +
                    "4. Didihkan kembali rebus selama 10 menit, hingga bakso mengambang.\n" +
                    "5. Sajikan bisa di sup/dibakar.\n" ,
            recipePictures = "bakso_ayam_oat",
            mealType = 3,
            cookTime = "15 menit",
            prepTime = "15 menit",
            portion = 4
        ),
Recipe(
            recipeId = 65,
            name = "Healthy Oatmeal Bar",
            calories = 94.0,
            carbs = 13.67,
            fat = 3.51,
            protein = 2.84,
            ingredients = "1 sdt ekstrak vanila\n" +
                    "135 g instant oatmeal\n" +
                    "60 g creamy peanut butter\n" +
                    "150 g banana\n" +
                    "20 g choco chips\n" ,
            cookingSteps = "1. Hancurkan pisang dengan garpu sampai lembut.\n" +
                    "2. Tuang bahan lain kecuali chocochips, aduk sampai rata.\n" +
                    "3. Masukkan choco chips, aduk lagi sampai rata.\n" +
                    "4. Tuang ke loyang yg sudah di alas baking paper. Panggang api bawah 180° kurang lebih 15-20 menit.\n" ,
            recipePictures = "healthy_oatmeal_bar",
            mealType = 1,
            cookTime = "20 menit",
            prepTime = "8 menit",
            portion = 12
        ),
Recipe(
            recipeId = 67,
            name = "Smoothie Buah Naga",
            calories = 138.0,
            carbs = 20.28,
            fat = 5.5,
            protein = 3.43,
            ingredients = "20 gram nanas\n" +
                    "4 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "30 gram buah naga\n" +
                    "30 g pisang single\n" +
                    "15 g oatmeal\n" +
                    "50 ml almond breeze almond milk original\n" +
                    "8 g chia seed\n" ,
            cookingSteps = "1. Bekukan semua buah buahan.\n" +
                    "2. Blender dengan susu dan oatmeal.\n" +
                    "3. Taburi dengan topping yang disukai seperti chia, almond, dll. dan sajikan.\n" ,
            recipePictures = "smoothie_buah_naga",
            mealType = 3,
            cookTime = "0 menit",
            prepTime = "15 menit",
            portion = 1
        ),
Recipe(
            recipeId = 70,
            name = "Pancake Pisang",
            calories = 51.0,
            carbs = 10.89,
            fat = 0.33,
            protein = 1.35,
            ingredients = "140 gram pisang\n" +
                    "1/2 sdt gula pasir\n" +
                    "50 gram tepung terigu putih (semua keperluan)\n" +
                    "80 ml susu skim\n" ,
            cookingSteps = "1. Haluskan pisang, lalu campur dengan terigu. Aduk rata.\n" +
                    "2. Tambahkan gula dan susu secara bertahap sambil diaduk hingga rata.\n" +
                    "3. Panaskan wajan antilengket, tuang satu sendok sayur adonan.\n" +
                    "4. Apabila sudah muncul gelembung, balik adonan pancake. Tunggu hingga kedua sisi matang.\n" +
                    "5. Sajikan hangat, boleh ditambah madu atau topping sesuai selera.\n" ,
            recipePictures = "pancake_pisang",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "5 menit",
            portion = 7
        ),
Recipe(
            recipeId = 73,
            name = "Bolognese Wrap",
            calories = 242.0,
            carbs = 29.08,
            fat = 8.41,
            protein = 12.78,
            ingredients = "100 gram daging sapi giling\n" +
                    "200 gram wortel dimasak\n" +
                    "2 siung bawang putih dimasak\n" +
                    "200 gram mentimun (kupas)\n" +
                    "15 g saus sambal\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm saos tiram\n" +
                    "3 gandum tortilla\n" ,
            cookingSteps = "1. Kupas timun dan wortel lalu iris memanjang. Rebus wortel dalam air yang mendidih selama 1 menit (jangan terlalu layu).\n" +
                    "2. Cincang 2 siung bawang putih. Tumis hingga harum kemudian masukkan daging cincang, tambahkan saus tiram, saus sambal, garam, lada. Masak hingga daging matang.\n" +
                    "3. Letakkan daging cincang, wortel dan timun, susun memanjang diatas tortilla kemudian gulung.\n" +
                    "4. Panggang tortilla hingga berwarna kecoklatan. Siap disajikan.\n" ,
            recipePictures = "bolognese_wrap",
            mealType = 2,
            cookTime = "25 menit",
            prepTime = "10 menit",
            portion = 3
        ),
Recipe(
            recipeId = 80,
            name = "Bungkus Salad Ayam",
            calories = 382.0,
            carbs = 27.1,
            fat = 15.03,
            protein = 33.22,
            ingredients = "100 gram dada ayam\n" +
                    "1 sejumput lada hitam\n" +
                    "1 siung bawang putih\n" +
                    "2 gram daun bawang\n" +
                    "1 sejumput garam laut\n" +
                    "1/2 sdm minyak goreng\n" +
                    "1 1/2 sdm kecap manis\n" +
                    "1 sdm saos tiram\n" +
                    "10 gram bawang bombay\n" ,
            cookingSteps = "1. Bumbui ayam dengan kaldu jamur lalu panggang di wajan tanpa minyak.\n" +
                    "2. Atur semuanya di atas tortilla dan gulung.\n" +
                    "3. Panaskan bungkus dalam wajan antilengket selama 2-3 menit.\n" ,
            recipePictures = "bungkus_salad_ayam",
            mealType = 3,
            cookTime = "10 menit",
            prepTime = "15 menit",
            portion = 1
        ),
Recipe(
            recipeId = 92,
            name = "Steam Tofu Udang",
            calories = 77.0,
            carbs = 2.04,
            fat = 3.94,
            protein = 8.84,
            ingredients = "125 gram udang\n" +
                    "2 sedang telur\n" +
                    "2 gram minyak wijen\n" +
                    "2 siung bawang putih\n" +
                    "1 sdt garam laut\n" +
                    "140 g egg tofu\n" +
                    "3 g kaldu jamur\n" +
                    "9 ml saus rasa tiram\n" ,
            cookingSteps = "1. Tumis bawang putih cincang pakai minyak wijen.\n" +
                    "2. Masukan udang hingga berubah warna.\n" +
                    "3. Tambahkan air secukupnya untuk membuat sup.\n" +
                    "4. Masukkan tofu hingga semua matang.\n" +
                    "5. Masukan garam saus tiram kaldu jamur.\n" +
                    "6. Masukan Kocokan telur aduk hingga berserabut. Tambahkan daun bawang (opsional).\n" ,
            recipePictures = "steam_tofu_udang",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 6
        ),
Recipe(
            recipeId = 95,
            name = "Tuna Bruschetta",
            calories = 129.0,
            carbs = 17.41,
            fat = 2.7,
            protein = 8.67,
            ingredients = "55 gram tuna dalam air (kalengan)\n" +
                    "3 irisan reguler roti multigrain\n" +
                    "30 g corn kernel\n" +
                    "5 g kaldu jamur\n" +
                    "3 g lada putih bubuk\n" +
                    "40 ml squeeze yogurt original\n" +
                    "15 g mozzarella & cheddar\n" ,
            cookingSteps = "1. Campurkan tuna, yoghurt, kernel, kaldu jamur dan merica.\n" +
                    "2. Oleskan adonan tuna di atas roti multigrain.\n" +
                    "3. Letakkan potongan keju diatasnya.\n" +
                    "4. Panggang dengan airfryer selama 15 menit.\n" ,
            recipePictures = "tuna_bruschetta",
            mealType = 3,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 3
        ),
Recipe(
            recipeId = 96,
            name = "Oatmeal Goreng",
            calories = 416.0,
            carbs = 50.24,
            fat = 19.21,
            protein = 14.25,
            ingredients = "1 besar telur\n" +
                    "3 gram cabai merah atau rawit\n" +
                    "2 siung bawang putih\n" +
                    "10 gram selada air\n" +
                    "1 sedang wortel bayi\n" +
                    "3 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "35 g instant oatmeal\n" ,
            cookingSteps = "1. Panaskan frypan, masukan minyak lalu tumis bawang merah, bawang putih, sama cabe.\n" +
                    "2. Setelah layu masukan telur tumis sampai mateng.\n" +
                    "3. Masukan wortel tumis sampai layu.\n" +
                    "4. Setelah layu masukkan oatmeal aduk sampai tercampur, tambahkan air sedikit agar dy bisa tercampur.\n" +
                    "5. Tambahkan selada air atau sayuran pilihan lainnya.\n" ,
            recipePictures = "oatmeal_goreng",
            mealType = 2,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 102,
            name = "Salad Mentimun dengan Coleslaw",
            calories = 525.0,
            carbs = 88.56,
            fat = 18.06,
            protein = 10.39,
            ingredients = "195 gram selada kol\n" +
                    "135 gram jagung rebus\n" +
                    "155 gram mentimun (dengan kulit)\n" +
                    "142 gram kentang rebus\n" +
                    "35 g mayonnaise roasted sesame\n" ,
            cookingSteps = "1. Cuci semua sayuran dengan air mengalir.\n" +
                    "2. Potong sayuran sesuai keinginan.\n" +
                    "3. Rebus air hingga mendidih lalu rebus jagung dan kentang. Biarkan agak dingin.\n" +
                    "4. Campur semua sayuran dalam mangkuk dan tambahkan sisa bahan.\n" +
                    "5. Pindahkan ke mangkuk dan sajikan.\n" ,
            recipePictures = "salad_mentimun_dengan_coleslaw",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 105,
            name = "Bubur Ayam Merah",
            calories = 331.0,
            carbs = 36.34,
            fat = 12.76,
            protein = 19.3,
            ingredients = "30 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "1 kecil telur\n" +
                    "100 gram nasi merah\n" +
                    "1 gelas kaldu ayam\n" +
                    "10 gram daun bawang\n" +
                    "10 gram kacang sangrai kering (dengan garam)\n" +
                    "1 sdt gula pasir\n" +
                    "2 sdt fiber creme\n" ,
            cookingSteps = "1. Membuat telur dadar. Gulung dan potong. Sisihkan untuk digunakan nanti.\n" +
                    "2. Rebus dada ayam bersama bawang putih, daun salam dan garam secukupnya hingga matang. Tiriskan lalu suwir-suwir dagingnya.\n" +
                    "3. Hancurkan beras merah dan rebus dengan kaldu ayam, tambahkan serat creme, gula dan garam secukupnya.\n" +
                    "4. Tuang bubur ke dalam mangkuk. Tambahkan ayam suwir, kacang tanah sangrai, irisan telur dadar untuk topping dan taburi daun bawang.\n" ,
            recipePictures = "bubur_ayam_merah",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 115,
            name = "Sup Telur Pedas",
            calories = 382.0,
            carbs = 17.04,
            fat = 32.01,
            protein = 10.12,
            ingredients = "2 besar telur rebus\n" +
                    "1 gram kaldu jamur\n" +
                    "3 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "100 gram brokoli\n" +
                    "1 siung bawang putih\n" +
                    "2 gelas air\n" +
                    "2 kecil bawang merah\n" +
                    "5 sdm minyak goreng\n" +
                    "100 gram sawi hijau\n" ,
            cookingSteps = "1. Haluskan cabe dan bawang merah, bawang putih lalu tumis dengan minyak goreng.\n" +
                    "2. Lalu masukkan air, tunggu mendidih dan masukkan telur agar matang, serta garam dan kaldu jamur.\n" +
                    "3. Saat telur sudah matang, masukkan sayuran dan tunggu hingga matang sesuai selera.\n" ,
            recipePictures = "sup_telur_pedas",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 117,
            name = "Bubur Ayam Oatmeal",
            calories = 434.0,
            carbs = 38.34,
            fat = 15.94,
            protein = 35.87,
            ingredients = "100 gram dada ayam\n" +
                    "1 sdt minyak wijen\n" +
                    "1 siung bawang putih\n" +
                    "1 sdt jahe\n" +
                    "1 iris daun bawang\n" +
                    "20 gram sereh\n" +
                    "1 kecil bawang merah\n" +
                    "35 g instant oatmeal\n" ,
            cookingSteps = "1. Tumis baput bawang merah, bawang putih sereh dan jahe.\n" +
                    "2. Masukkan ayam, tumis sebentar.\n" +
                    "3. Tambahkan air, masak hingga mendidih.\n" +
                    "4. Masukkan oatmeal. Bumbui dengan garam, merica, kaldu jamur (optional).\n" +
                    "5. Tambahkan minyak wijen.\n" +
                    "6. Masak hingga tekstur sesuai yg di inginkan.\n" +
                    "7. Taburi dengan daun bawang saat disajikan.\n" ,
            recipePictures = "bubur_ayam_oatmeal",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 125,
            name = "Salad Sayur",
            calories = 134.0,
            carbs = 32.69,
            fat = 0.74,
            protein = 3.26,
            ingredients = "20 gram jeruk nipis\n" +
                    "1/4 mangkok, irisan paprika merah manis\n" +
                    "2 sdm kemangi\n" +
                    "1/2 mangkok, irisan mangga\n" +
                    "1/2 mangkok, dicincang kale\n" +
                    "10 gram bayam\n" +
                    "100 gram bengkoang\n" +
                    "25 gram bawang bombay\n" ,
            cookingSteps = "1. Potong dan iris semua bahan sesuai selera.\n" +
                    "2. Tambahkan perasan jeruk nipis.\n" +
                    "3. Tambahkan sedikit lada dan garam.\n" +
                    "4. Aduk rata.\n" ,
            recipePictures = "salad_sayur",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 126,
            name = "Bakwan Sayur Teflon",
            calories = 216.0,
            carbs = 34.6,
            fat = 6.72,
            protein = 4.4,
            ingredients = "30 gram kecambah\n" +
                    "22 gram wortel\n" +
                    "3 gram seledri\n" +
                    "7 gram daun bawang\n" +
                    "55 ml air\n" +
                    "1 sdm extra virgin olive oil\n" +
                    "40 g tepung bakwan\n" ,
            cookingSteps = "1. Potong2 wortel, seledri n daun bawang.\n" +
                    "2. Masukan kecambah, wortel, air dan tepung bakwan. Aduk rata dan bumbui dengan garam dan merica.\n" +
                    "3. Beri teflon minyak zaitun. Panaskan minyak (jangan sampai terlalu panas).\n" +
                    "4. Goreng hingga matang.\n" ,
            recipePictures = "bakwan_sayur_teflon",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 127,
            name = "Green Smoothie",
            calories = 229.0,
            carbs = 33.68,
            fat = 7.58,
            protein = 6.94,
            ingredients = "50 gram celery\n" +
                    "10 g pisang single\n" +
                    "35 g oat\n" +
                    "15 g chia seed\n" ,
            cookingSteps = "1. Cuci bersih dab siapkan bahan.\n" +
                    "2. Blend jadi satu.\n" +
                    "3. Serving tabur chia seed.\n" ,
            recipePictures = "green_smoothie",
            mealType = 3,
            cookTime = "0 menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 129,
            name = "Omlet Bayam",
            calories = 95.0,
            carbs = 7.55,
            fat = 4.8,
            protein = 7.04,
            ingredients = "3 sedang telur\n" +
                    "3 gram cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 ikat bayam\n" +
                    "2 kecil bawang merah\n" +
                    "5 g margarin\n" +
                    "2 g royco ayam\n" ,
            cookingSteps = "1. Pecahkan telur, masukan cincang bawang merah, bawang putih, cabai. Kocok.\n" +
                    "2. Setelah tercampur masukan royco secukupnya.\n" +
                    "3. Lalu masukan bayam yang sudah di ambil bagian daun nya saja.\n" +
                    "4. Siapkan pan anti lengket, masukan margarin sedikit saja.\n" +
                    "5. Masukan adonan omlet bayam, lalu masak hingga matang.\n" +
                    "6. Bolak balik adonan dan pastikan semua matang merata.\n" +
                    "7. Setelah yakin sudah matang, angkat dan sajikan.\n" ,
            recipePictures = "omlet_bayam",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 131,
            name = "Pokcoy Kuah Pedas",
            calories = 348.0,
            carbs = 26.22,
            fat = 14.18,
            protein = 29.26,
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
                    "150 gram tahu susu\n" ,
            cookingSteps = "1. Rebus air dan sedikit garam, masukan pakcoy, tisirkan, tata dipiring.\n" +
                    "2. Tumis bawang putih dan bawang merah dengan minyak kelapa sampai sedikit layu. Masukkan butir telur ayam biarkan matang, dibalik baru di orak arik, masukkan cabai, tumis sampai harum.\n" +
                    "3. Masukkan ayam dan jamur kancing sampai ayam matang (berubah warna). Tambahkan garam, lada, royco, dan gula pasir.\n" +
                    "4. Masukkan saus sambal, kecap manis, minyak wijen, aduk sampai rata, tambahkan air sesuai selera kemudian test rasa.\n" +
                    "5. Terakhir masukkan tahu sebentar tidak usah diaduk-aduk, tunggu sampai air sedikit berkurang. Matikan api kompor, siram diatas pokcoy.\n" ,
            recipePictures = "pokcoy_kuah_pedas",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 133,
            name = "Bayam Tumis Tahu",
            calories = 397.0,
            carbs = 11.82,
            fat = 27.12,
            protein = 31.87,
            ingredients = "1 besar telur\n" +
                    "150 gram bayam\n" +
                    "1 sdm minyak goreng\n" +
                    "230 gram tahu\n" +
                    "2 g royco ayam\n" +
                    "5 gram udang rebon\n" +
                    "1 sdm saos tiram\n" ,
            cookingSteps = "1. Tumis udang, lalu masukkan telur dan di orak-arik dengan minyak goreng.\n" +
                    "2. Tambahkan tahu yg sudah di hancurkan.\n" +
                    "3. Sesudah wangi, masukkan bayam lalu tambahkan saos tiram dan royco.\n" +
                    "4. Tunggu hingga bayam benar-benar matang.\n" +
                    "5. Siap di hidangkan.\n" ,
            recipePictures = "bayam_tumis_tahu",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 135,
            name = "Telor Dadar",
            calories = 246.0,
            carbs = 7.0,
            fat = 19.17,
            protein = 11.04,
            ingredients = "1 besar egg\n" +
                    "50 gram chives\n" +
                    "1 sdm, cincang scallions or spring onions\n" +
                    "25 g sosis ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm canola oil\n" ,
            cookingSteps = "1. Masukkan kucai kedalam teflon yg sudah diminyaki, masak sampai layu.\n" +
                    "2. Kocok sosis potong dengan telor, masukkan bumbu dan daun bawang secukupnya.\n" +
                    "3. Tuangkan adonan telor ke atas kucai, masak sampai matang.\n" ,
            recipePictures = "telor_dadar",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 139,
            name = "Nasi Goreng Mentega",
            calories = 344.0,
            carbs = 41.13,
            fat = 14.91,
            protein = 8.15,
            ingredients = "1 besar telur\n" +
                    "130 gram brokoli\n" +
                    "1 siung bawang putih\n" +
                    "1/2 sdt gula pasir\n" +
                    "5 gram garam laut\n" +
                    "175 gram tahu\n" +
                    "3 g lada putih bubuk\n" +
                    "5 ml minyak goreng\n" ,
            cookingSteps = "1. Tumis bawang putih.\n" +
                    "2. Masukkan sosis, jamur. Angkat.\n" +
                    "3. Orek telor, angkat.\n" +
                    "4. Tumis bayam, angkat.\n" +
                    "5. Rebus bihun, tiriskan.\n" +
                    "6. Tumis bihun, masukkan kecap asin, kecap manis, dan kaldu jamur Masukkan sosis, jamur, telor, bayam.\n" +
                    "7. Masukkan sosis, jamur, telor, bayam.\n" +
                    "8. Bumbui dengan garam dan merica sesuai selera.\n" ,
            recipePictures = "nasi_goreng_mentega",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 141,
            name = "Smoothies Berry",
            calories = 136.0,
            carbs = 26.9,
            fat = 2.13,
            protein = 5.28,
            ingredients = "100 gram mangga\n" +
                    "100 gram stroberi\n" +
                    "1/2 buah naga\n" +
                    "200 ml skimmed milk (200ml)\n" +
                    "20 g granola creations cinnamon & raisin\n" ,
            cookingSteps = "1. Cara memasak\n" +
                    "2. Masukkan semua bahan, kecuali granola dan blend.\n" +
                    "3. Tuang ke mangkuk. Tambahkan granola.\n" +
                    "4. 1 anggota telah menambahkan resep ini ke b\n" ,
            recipePictures = "smoothies_berry",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 145,
            name = "Oseng Sawi Telur",
            calories = 113.0,
            carbs = 9.54,
            fat = 5.15,
            protein = 7.87,
            ingredients = "1 besar telur\n" +
                    "1 siung bawang putih\n" +
                    "1 kecil bawang merah\n" +
                    "50 gram sawi hijau\n" ,
            cookingSteps = "1. Oseng bawang merah dan bawang putih.\n" +
                    "2. Masukkan sawi dan sedikit air.\n" +
                    "3. Tambahkan telur, bumbui dengan garam dan merica.\n" ,
            recipePictures = "oseng_sawi_telur",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 149,
            name = "Terong Balado Rebus",
            calories = 302.0,
            carbs = 44.94,
            fat = 11.22,
            protein = 17.57,
            ingredients = "50 gram cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "250 gram terung\n" +
                    "2 siung bawang putih\n" +
                    "400 ml air\n" +
                    "120 gram tempe\n" +
                    "3 sedang bawang merah\n" +
                    "2 g terasi\n" +
                    "1.75 g magic lezat\n" ,
            cookingSteps = "1. Haluskan semua bahan kecuali tempe dan terong.\n" +
                    "2. Tuang bumbu ke teflon dan tambahkan air secukupnya, tutup teflon dan rebus hingga mendidih.\n" +
                    "3. Iris terong melingkar, susun ke dalam teflon setelah rebusan cabai mendidih, jangan ditutup kambali.\n" +
                    "4. Detelah terong setengah matang, masukkan tempe.\n" +
                    "5. Masak hingga matang dan air surut, jangan lupa koreksi rasa.\n" ,
            recipePictures = "terong_balado_rebus",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 152,
            name = "Shakshuka",
            calories = 283.0,
            carbs = 31.06,
            fat = 11.0,
            protein = 18.65,
            ingredients = "2 besar telur\n" +
                    "1 siung bawang putih\n" +
                    "2 besar utuh tomat merah\n" +
                    "1 sdm saus tomat\n" +
                    "100 gram bawang bombay\n" +
                    "50 gram jamur kancing\n" ,
            cookingSteps = "1. Kupas kulit tomat, lalu di cincang/blender. Cincang bawang putih dan bawang bombay, dan jamur champignon.\n" +
                    "2. Tumis bawang bombay dan bawang putih hingga layu, tambahkan jamur tumis lagi hingga jamur berubah warna, masukkan pure tomat, saos tomat dan air. Tambahkan Garam, lada, dan bumbu pilihan lainnya.\n" +
                    "3. Tambahkan telur, tutup pan hingga telur matang.\n" +
                    "4. Makanan siap dihidangkan.\n" ,
            recipePictures = "shakshuka",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 153,
            name = "Toast Avocado",
            calories = 488.0,
            carbs = 39.95,
            fat = 37.53,
            protein = 6.83,
            ingredients = "1 irisan reguler roti gandum panggang\n" +
                    "1 alpukat\n" +
                    "5 g susu kental manis\n" +
                    "7.5 g mentega\n" +
                    "10 g palm sugar\n" ,
            cookingSteps = "1. Panggang roti dengan mentega.\n" +
                    "2. Keruk alpukat letakkan diatas roti.\n" +
                    "3. Taburi gula palm.\n" +
                    "4. Tuang susu kental manis.\n" ,
            recipePictures = "toast_avocado",
            mealType = 1,
            cookTime = "4 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 154,
            name = "Oat Almond Butter Cookies",
            calories = 46.0,
            carbs = 4.41,
            fat = 2.93,
            protein = 1.42,
            ingredients = "50 gram mentega almond\n" +
                    "50 gram selai kacang\n" +
                    "1 sdt ekstrak vanila\n" +
                    "1 sdt baking soda\n" +
                    "2.6 g stevia sweetener\n" +
                    "15 g original yogurt\n" +
                    "100 g oat\n" +
                    "10 g raw cacao powder\n" +
                    "20 g dark chocolate 70%\n" ,
            cookingSteps = "1. Blender oat menjadi tepung.\n" +
                    "2. Campur semua bahan kecuali choco chips. Aduk rata.\n" +
                    "3. Terakhir tambahkan chocochips dan dibentuk.\n" +
                    "4. Panggang di api 180°C 25menit.\n" ,
            recipePictures = "oat_almond_butter_cookies",
            mealType = 1,
            cookTime = "25 Menit",
            prepTime = "15 Menit",
            portion = 25
        ),
Recipe(
            recipeId = 155,
            name = "Blueband Biskuit Lunak",
            calories = 300.0,
            carbs = 32.54,
            fat = 17.31,
            protein = 3.56,
            ingredients = "1 besar telur\n" +
                    "60 gram gula\n" +
                    "100 gram tepung terigu putih (semua keperluan)\n" +
                    "4 g tepung pati jagung\n" +
                    "125 ml dark compound chocolate\n" +
                    "20 g palm sugar\n" +
                    "100 g cake & cookie\n" +
                    "55 g tepung terigu kunci biru\n" ,
            cookingSteps = "1. Kocok mentega dan gula. Kemudian tambahkan telur dan aduk.\n" +
                    "2. Tambahkan tepung yang sudah diayak dan bahan lainnya. Campur dengan baik.\n" +
                    "3. Bentuk biskuit dan sebarkan di atas loyang. Panggang selama 18 menit pada suhu 180°C-190°C.\n" ,
            recipePictures = "blueband_biskuit_lunak",
            mealType = 2,
            cookTime = "18 Menit",
            prepTime = "20 Menit",
            portion = 8
        ),
Recipe(
            recipeId = 161,
            name = "Oatbanana Cookies",
            calories = 385.0,
            carbs = 71.42,
            fat = 6.7,
            protein = 13.69,
            ingredients = "100 gram pisang\n" +
                    "5 gram chia seed\n" +
                    "70 gram oat\n" ,
            cookingSteps = "1. Penyetkan pisang sampai hancur.\n" +
                    "2. Masukan oatmeal dan chiaseed.\n" +
                    "3. Aduk lalu cetak dan panggang di suhu 200•C selama 15menit.\n" ,
            recipePictures = "oatbanana_cookies",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 167,
            name = "Tumis Pakcoy",
            calories = 30.0,
            carbs = 6.2,
            fat = 0.62,
            protein = 1.22,
            ingredients = "1 sdt bawang putih bubuk\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "1 gelas air\n" +
                    "1/2 sdt, dikemas gula merah\n" +
                    "200 gram pakcoy\n" +
                    "0,5 g bawang merah goreng\n" +
                    "2 g royco ayam\n" +
                    "1,2 g lada putih bubuk\n" ,
            cookingSteps = "1. Masukkan semua bumbu dan air.\n" +
                    "2. Setelah mendidih masukkan sayur masak 2menit.\n" +
                    "3. Sajikan dalam wadah taburi bawang goreng.\n" ,
            recipePictures = "tumis_pakcoy",
            mealType = 1,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 168,
            name = "Banana Oat Cake",
            calories = 322.0,
            carbs = 32.2,
            fat = 16.01,
            protein = 14.4,
            ingredients = "58 gram telur\n" +
                    "69 gram pisang\n" +
                    "20 gram kacang almond\n" +
                    "1 lembar singles\n" +
                    "16 g selai kacang\n" +
                    "2 g classic\n" +
                    "52 ml low fat high calcium rasa coklat (200ml)\n" +
                    "50 g instant whole oats\n" ,
            cookingSteps = "1. Kocok telur menggunakan ballon wishk sampai berbusa, masukan kopi, selai,dan pisang yg sudah dilumatkan terlebih dahulu. Kocok sebentar.\n" +
                    "2. Masukan oat (me: diblender) dan almond cacah aduk rata. Tuang susu aduk kembali.\n" +
                    "3. Tuang ke loyang yg sdh dioles minyak, ratakan, beri topping keju slice.\n" +
                    "4. Panggang di oven yg sdh dipanaskan selama 30m.\n" ,
            recipePictures = "banana_oat_cake",
            mealType = 3,
            cookTime = "30 Menit",
            prepTime = "15 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 173,
            name = "Setup Roti Gandum",
            calories = 183.0,
            carbs = 49.99,
            fat = 6.58,
            protein = 10.4,
            ingredients = "30 gram kismis\n" +
                    "1 besar putih telur\n" +
                    "5 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "250 ml susu uht full cream (250ml)\n" +
                    "6 g sweetener diabtx\n" +
                    "350 g roti gandum\n" ,
            cookingSteps = "1. Campur putih telur dengan gula lalu kocok.\n" +
                    "2. Campurkan 250ml susu UHT/Low Fat lalu aduk.\n" +
                    "3. Potong roti gandum sesuai selera lalu masukan kedalam adonan basah lalu aduk dengan rata.\n" +
                    "4. Tuangkan 1/2 adonan kedalam cetakan taburkan kismis sesuai selera.\n" +
                    "5. Tuangkan sisa adonan ratakan lalu taburkan kismis & kacang Almond sesuai selera.\n" +
                    "6. Kukus 20 menit atau sampai adonan padat (tusuk dengan tusuk gigi untuk test kepadatan).\n" ,
            recipePictures = "setup_roti_gandum",
            mealType = 1,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 174,
            name = "Oat Goreng Special",
            calories = 812.0,
            carbs = 146.95,
            fat = 15.98,
            protein = 40.91,
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
                    "5 g pilus sapi panggang\n" ,
            cookingSteps = "1. Pertama haluskan bawang putih, merah, cabai diberi garam dan merica.\n" +
                    "2. Bumbu yang sudah dihaluskan ditumis dan bawang bombay lalu masukan telur dan oat+ sayur²nya.\n" +
                    "3. Diaduk² lalu masukan 1sdt Saori dan bumbu royco secukupnya dan tambahkan bumbu nasi goreng sajiku sejumput aja!\n" +
                    "4. Ayam dimasak diberi bumbu bawang putih, merica , bon cabe dan ohaiong. Lalu di panggang pake telfon sampai matang.\n" +
                    "5. Sajikan deh! Tambahkan pilus buat topping aja wkwk.\n" ,
            recipePictures = "oat_goreng_special",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 179,
            name = "Omelet Tuna",
            calories = 126.0,
            carbs = 2.22,
            fat = 6.37,
            protein = 14.74,
            ingredients = "30 gram ikan tuna\n" +
                    "1 besar telur\n" +
                    "50 gram bayam dimasak (dari segar)\n" ,
            cookingSteps = "1. Letakkan bayam di teflon. Tutup teflon.\n" +
                    "2. Letakkan tuna diatas bayam.\n" +
                    "3. Tuangkan telur diatasnya. Tutup teflon.\n" +
                    "4. Balikkan dan selesai.\n" ,
            recipePictures = "omelet_tuna",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 181,
            name = "Pizza Oatmeal Teflon",
            calories = 162.0,
            carbs = 21.38,
            fat = 5.7,
            protein = 7.12,
            ingredients = "25 gram keju mozzarella\n" +
                    "40 gram daging kornet\n" +
                    "1 sdt, tumbuk oregano\n" +
                    "100 g instant oatmeal\n" +
                    "2 sdm saus sambal\n" +
                    "1/4 sedang bawang bombay\n" ,
            cookingSteps = "1. Uleni oatmeal dengan sedikit air.\n" +
                    "2. Letakkan ke teflon.\n" +
                    "3. Beri saos lalu beri toping.\n" +
                    "4. Tambahkan oregano dan parutan mozzarella.\n" +
                    "5. Tunggu 30 menit dgn ditutup dan api kecil.\n" ,
            recipePictures = "pizza_oatmeal_teflon",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "15 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 186,
            name = "Oatmeal Goreng dan Omelet",
            calories = 414.0,
            carbs = 57.81,
            fat = 16.42,
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
                    "1/2 sedang bawang bombay\n" ,
            cookingSteps = "1. Sangrai oatmeal sampai kecoklatan.\n" +
                    "2. Tumis bawang merah, beang putih, cabai, dan bawang bombay sampai layu dan harum.\n" +
                    "3. Tuang oatmeal yg sudah di sangrai.\n" +
                    "4. Tambah air sedikit saja.\n" +
                    "5. Tambahkan garam+kaldu jamur secukupnya sambil diaduk.\n" +
                    "6. Hidangkan dengan toping telur dan mentimun.\n" ,
            recipePictures = "oatmeal_goreng_dan_omelet",
            mealType = 3,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 188,
            name = "Gulungan Salad Daging Sapi Asap",
            calories = 180.0,
            carbs = 17.04,
            fat = 6.78,
            protein = 11.72,
            ingredients = "1 sedang rice paper\n" +
                    "20 gram wortel\n" +
                    "20 gram mentimun (kupas)\n" +
                    "30 gram selada air\n" +
                    "50 g smoked beef\n" +
                    "17 g keju slice\n" ,
            cookingSteps = "1. Celupkan rice paper ke air hangat hingga lemas & mudah dibentuk.\n" +
                    "2. Susun semua bahan di atas rice paper lalu gulung.\n" +
                    "3. Salad siap disajikan.\n" ,
            recipePictures = "gulungan_salad_daging_sapi_asap",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 191,
            name = "Mangkuk Smoothie Hijau",
            calories = 281.0,
            carbs = 37.85,
            fat = 14.22,
            protein = 5.05,
            ingredients = "50 gram semangka\n" +
                    "30 gram selada\n" +
                    "80 gram apel\n" +
                    "50 gram alpukat\n" +
                    "50 gram blewah melon\n" +
                    "4 gram kacang almond\n" +
                    "20 g gourmet blend dark chocolate & banana granola\n" ,
            cookingSteps = "1. Blender semua bahan kecuali kacang almond dan granola.\n" +
                    "2. Sajikan ke dalam mangkok.\n" +
                    "3. Letakkan kacang dan granola sebagai topping.\n" ,
            recipePictures = "mangkuk_smoothie_hijau",
            mealType = 3,
            cookTime = "0 menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 192,
            name = "Granola Homemade",
            calories = 266.0,
            carbs = 42.4,
            fat = 8.67,
            protein = 6.45,
            ingredients = "75 gram dry roasted unsalted peanuts\n" +
                    "1 1/2 tbsps honey\n" +
                    "2 sdt extra virgin olive oil\n" +
                    "400 g havermout rolled oat\n" +
                    "45 g kurma\n" +
                    "75 g gula aren\n" ,
            cookingSteps = "1. Sangrai rolled oat, kacang tanah hingga kecoklatan.\n" +
                    "2. Masukkan bahan2 untuk saus, olive oil,gula aren, madu, kurma.\n" +
                    "3. Campur dengan rata siap di sajikan dengan susu atau yogurt.\n" ,
            recipePictures = "granola_homemade",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "15 Menit",
            portion = 10
        ),
Recipe(
            recipeId = 194,
            name = "Chocolate Oatmeal Bars",
            calories = 78.0,
            carbs = 12.03,
            fat = 3.32,
            protein = 1.26,
            ingredients = "128 gram coklat manis atau gelap\n" +
                    "1 sdt ekstrak vanila\n" +
                    "125 gram kismis (tanpa biji)\n" +
                    "14 reguler/biasa marshmallow\n" +
                    "5 sdm mentega\n" +
                    "256 g rolled oats\n" ,
            cookingSteps = "1. Dalam panci besar, lelehkan chocolate chip, mentega ringan, dan marshmallow dengan api kecil, aduk hingga lembut.\n" +
                    "2. Hapus dari panas; agak dingin. Aduk vanilla. Aduk oat dan sisa bahan.\n" +
                    "3. Teteskan satu sendok makan bulat ke atas kertas lilin dan bentuk menjadi lingkaran atau batang. Tutup dan dinginkan 2 sampai 3 jam. Diamkan pada suhu kamar sekitar 15 menit sebelum disajikan. Simpan tertutup rapat di lemari es. Menghasilkan sekitar 3 lusin.\n" +
                    "4. 6 anggota telah menambahkan resep ini ke buku m\n" ,
            recipePictures = "chocolate_oatmeal_bars",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 36
        ),
Recipe(
            recipeId = 195,
            name = "Bola Energi Oatmeal",
            calories = 138.0,
            carbs = 12.03,
            fat = 8.93,
            protein = 4.8,
            ingredients = "1 sdm madu\n" +
                    "80 g creamy peanut butter\n" +
                    "1/2 gelas kering rolled oats\n" +
                    "25 g dark chocolate 70%\n" ,
            cookingSteps = "1. Campurkan semua bahan dalam mangkuk kecil dan aduk hingga tercampur rata.\n" +
                    "2. Dinginkan di lemari es selama 30 menit.\n" +
                    "3. Gunakan sendok atau sendok makan untuk membagi campuran menjadi 6 bola secara merata. Gunakan tangan Anda untuk membentuk bola. Nikmati sekarang dan simpan sisanya untuk nanti dengan menyimpannya dalam wadah tertutup di lemari es hingga 1 minggu.\n" ,
            recipePictures = "bola_energi_oatmeal",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "30 Menit",
            portion = 6
        ),
Recipe(
            recipeId = 202,
            name = "Pancake Labu Sederhana",
            calories = 39.0,
            carbs = 6.33,
            fat = 1.3,
            protein = 1.47,
            ingredients = "3 besar kuning telur\n" +
                    "600 gram labu\n" +
                    "5,2 g stevia sweetener\n" +
                    "30 g tepung tapioka\n" +
                    "30 g segitiga biru\n" ,
            cookingSteps = "1. Kukus Labu Kuning dan haluskan dg garpu sampe hancur.\n" +
                    "2. Masukkan Tepung Terigu, Tapioka, Kuning Telur dan Stevia.\n" +
                    "3. Panggang tanpa minyak di atas teflon dg api kecil per 1 sdm munjung, dibalik sampe matang merata.\n" ,
            recipePictures = "pancake_labu_sederhana",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 12
        ),
Recipe(
            recipeId = 203,
            name = "Yogurt dan Chia Bowl",
            calories = 620.0,
            carbs = 69.11,
            fat = 32.13,
            protein = 20.4,
            ingredients = "100 gram chia seed\n" +
                    "1 buah naga\n" +
                    "80 g blueberry yoghurt\n" ,
            cookingSteps = "1. Potong dadu buah naga.\n" +
                    "2. Siram dengan yoghurt.\n" +
                    "3. Taruh chia seed diatasnya.\n" ,
            recipePictures = "yogurt_dan_chia_bowl",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 209,
            name = "Papaya Yogurt with Almonds",
            calories = 210.0,
            carbs = 42.68,
            fat = 3.17,
            protein = 5.52,
            ingredients = "1 mangkok, potong dadu pepaya\n" +
                    "5 gram kacang almond\n" +
                    "1 sdm madu\n" +
                    "71 ml yogurt plain\n" ,
            cookingSteps = "1. Potong pepaya menjadi ukuran yang lebih kecil, kemudian sisihkan.\n" +
                    "2. Cincang almond menjadi ukuran yang lebih kecil.\n" +
                    "3. Masukkan yogurt ke dalam mangkok.\n" +
                    "4. Tambahkan pepaya dan almond yang sudah dipotong.\n" +
                    "5. Tambahkan juga madu, jika ingin lebih manis.\n" ,
            recipePictures = "papaya_yogurt_with_almonds",
            mealType = 1,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 212,
            name = "Bubur Manis Oat",
            calories = 187.0,
            carbs = 34.09,
            fat = 4.34,
            protein = 3.18,
            ingredients = "5 gram chia seed\n" +
                    "1 gelas air\n" +
                    "1 sejumput garam laut\n" +
                    "1 sdt fiber creme\n" +
                    "21 g oat\n" +
                    "1 ml sweetener\n" +
                    "15 g gula aren\n" ,
            cookingSteps = "1. Masukan semua bahan dalam wajan.\n" +
                    "2. Tambahkan daun pandan agar wangi.\n" +
                    "3. Aduk hingga bubur mengental.\n" +
                    "4. Bubur siap di ajukan.\n" +
                    "5. Tambahkan sejumput chia seed untuk hiasan.\n" ,
            recipePictures = "bubur_manis_oat",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "2 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 213,
            name = "Salad Roll",
            calories = 183.0,
            carbs = 14.96,
            fat = 7.64,
            protein = 15.18,
            ingredients = "1 sedang rice paper\n" +
                    "20 gram wortel\n" +
                    "20 gram mentimun (kupas)\n" +
                    "50 gram selada air\n" +
                    "20 g cheddar cheese\n" +
                    "50 g smoked beef\n" ,
            cookingSteps = "1. Rendam rice paper ke dalam air hangat sebentar.\n" +
                    "2. Setelah rice paper lemas & mudah dibentuk, masukkan bahan2, lalu lipat/gulung.\n" +
                    "3. Makan!\n" ,
            recipePictures = "salad_roll",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 218,
            name = "Smoothie Coklat Pisang",
            calories = 280.0,
            carbs = 60.42,
            fat = 3.97,
            protein = 5.85,
            ingredients = "200 gram pisang\n" +
                    "5 gram chia seed\n" +
                    "10 g mueslix harvest fruit\n" +
                    "10 g coklat bubuk\n" ,
            cookingSteps = "1. Pisang bekuin.\n" +
                    "2. Blender sama bubuk coklat.\n" +
                    "3. Kasih taburan topping.\n" ,
            recipePictures = "smoothie_coklat_pisang",
            mealType = 3,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 219,
            name = "Snack Oat",
            calories = 26.0,
            carbs = 3.44,
            fat = 1.08,
            protein = 0.73,
            ingredients = "1 besar putih telur\n" +
                    "130 gram pisang\n" +
                    "25 gram kacang almond panggang kering (tanpa tambahan garam)\n" +
                    "100 g quick cooking oatmeal\n" +
                    "1 sdm extra virgin olive oil\n" ,
            cookingSteps = "1. Lumatkan pisang.\n" +
                    "2. Masukkan putih telur kedalam pisang yg dilumatkan.\n" +
                    "3. Aduk dan campurkan semua bahan kedalam pisang (oat, almond, olive oil).\n" +
                    "4. Bagi menjadi 30 bagian (10gr).\n" +
                    "5. Panggang 20 menit (suhu 180).\n" +
                    "6. Dingingkan, siap disantap.\n" +
                    "7. 4 anggota telah menambahka\n" ,
            recipePictures = "snack_oat",
            mealType = 3,
            cookTime = "25 Menit",
            prepTime = "10 Menit",
            portion = 30
        ),
Recipe(
            recipeId = 220,
            name = "Salad",
            calories = 475.0,
            carbs = 55.72,
            fat = 26.49,
            protein = 10.37,
            ingredients = "jus dari 1 jeruk nipis\n" +
                    "50 gram tomat\n" +
                    "50 gram selada\n" +
                    "30 gram jamur dimasak (lemak ditambahkan dalam masakan)\n" +
                    "50 gram bawang bombay dimasak\n" +
                    "50 gram telur ceplok\n" +
                    "4 sdt, tumbuk oregano\n" +
                    "1 sdm garam\n" +
                    "50 gram mentimun (kupas)\n" +
                    "2 sdm madu\n" +
                    "1 sdm olive oil\n" +
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Cuci semua bahan untuk salad.\n" +
                    "2. Kupas dan potong tipis bawang bombay lalu tumis dengan sedikit minyak dan garam secukupnya.\n" +
                    "3. Potong potong tipis jamur lalu tumis dengan sedikit minyak dan garam secukupnya.\n" +
                    "4. Kupas mentimun lalu buang bijinya dan potong kecil kecil.\n" +
                    "5. Buang biji tomat dan potong kecil kecil.\n" +
                    "6. Keringkan selada dan potong sesuai selera.\n" +
                    "7. Ceplok telur dengan sedikit minyak dan beri garam secukupnya.\n" +
                    "8. Untuk dressingnya : Campurkan air jeruk nipis, madu, garam, merica, oregano, dan olive oil.\n" ,
            recipePictures = "salad",
            mealType = 3,
            cookTime = "30 Menit",
            prepTime = "30 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 222,
            name = "Salad Selada Nanas Tomat Cerry",
            calories = 233.0,
            carbs = 14.16,
            fat = 19.43,
            protein = 2.17,
            ingredients = "1 iris jeruk nipis\n" +
                    "1/4 sdt bubuk cabai\n" +
                    "50 gram nanas\n" +
                    "50 gram selada daun hijau\n" +
                    "1 sdm minyak zaitun ekstra virgin\n" +
                    "4 tomat anggur\n" +
                    "20 ml saus siram wijen sangrai\n" ,
            cookingSteps = "1. Cuci bersih selada, tomat, nanas.\n" +
                    "2. Rendam dlm air garam selama 15-20 menit.\n" +
                    "3. Bilas bahan tersebut dengan air bersih, potong2 sesuai selera.\n" +
                    "4. Keringkan dengan pengering sayuran.\n" +
                    "5. Campur rata smua bahan salad dressing (kewpie saus siram wijen sangrai+minyak zaitun+sari kurma+bawang putih bubuk+cabe bubuk (optional)+perasan air jeruk nipis.\n" +
                    "6. Siram salad dressing ke salad yg telah disajikan, aduk2, siap utk disantap.\n" ,
            recipePictures = "salad_selada_nanas_tomat_cerry",
            mealType = 2,
            cookTime = "0 menit",
            prepTime = "30 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 228,
            name = "Pancake Naga",
            calories = 334.0,
            carbs = 48.64,
            fat = 9.35,
            protein = 17.07,
            ingredients = "1 besar telur\n" +
                    "4 sendok makan instant oatmeal\n" +
                    "1 buah naga\n" +
                    "35 g susu\n" ,
            cookingSteps = "1. Blender semua bahan jadi satu.\n" +
                    "2. Panggang di atas teflon jadi beberapa bagian.\n" +
                    "3. Angkat dan kasih toping naga.\n" ,
            recipePictures = "pancake_naga",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 229,
            name = "Bolu Pisang Oat Kukus",
            calories = 46.0,
            carbs = 8.25,
            fat = 1.06,
            protein = 1.51,
            ingredients = "1 sedang telur\n" +
                    "2 sedang pisang\n" +
                    "1 sdt baking soda\n" +
                    "6 sendok makan instant oatmeal\n" +
                    "5 g keju cheddar\n" ,
            cookingSteps = "1. Blender oat agak halus.\n" +
                    "2. Campur telor dan oat yang sudah d blender beserta baking powder.\n" +
                    "3. Haluskan pisang.\n" +
                    "4. Tuang pisang yang sudah halus ke adonan.\n" +
                    "5. Siapkan loyang dan Kukusan.\n" +
                    "6. Kukus kurang lebih 30menit.\n" +
                    "7. Sajikan.\n" ,
            recipePictures = "bolu_pisang_oat_kukus",
            mealType = 3,
            cookTime = "45 Menit",
            prepTime = "10 Menit",
            portion = 11
        ),
Recipe(
            recipeId = 231,
            name = "Tumis Sawi Putih",
            calories = 263.0,
            carbs = 22.48,
            fat = 15.78,
            protein = 10.08,
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
                    "35 gram bawang bombay\n" ,
            cookingSteps = "1. Potong Potong Sawi Putih Kemudian Cuci Bersih.\n" +
                    "2. Iris tipis2 bawang putih dan bawang bombay, iris serong cabai merah, iris tomat menjadi dua bagian.\n" +
                    "3. Panaskan minyak, lalu tumis bawang putih dan bawang bombay. Setelah harum masukkan telur dan orak-arik, tambahkan 250ml air, setelah mendidih tambahkan kol, cabai dan tomat, aduk rata. Tambahkan gula, garam, kecap & royco.\n" +
                    "4. Koreksi rasa, tunggu sampai matang.\n" ,
            recipePictures = "tumis_sawi_putih",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 236,
            name = "Orange Smoothie",
            calories = 187.0,
            carbs = 47.88,
            fat = 0.75,
            protein = 2.39,
            ingredients = "1 kecil jeruk keprok\n" +
                    "1 sedang apel (tanpa kulit)\n" +
                    "100 gram nanas\n" +
                    "100 gram wortel\n" +
                    "1/2 gelas air\n" ,
            cookingSteps = "1. Kupas semua buah2an, cuci bersih lalu potong2, kecuali jeruk, peras airnya.\n" +
                    "2. Siapkan blender, masukan semua buah yg sudah di potong kecil2, lalu air perasan jeruk, tambahkan air dan es batu secukupnya.\n" +
                    "3. Blender semua buah kurleb 2-3menit sampai halus sempurna. Siap dinikmati.\n" ,
            recipePictures = "orange_smoothie",
            mealType = 3,
            cookTime = "0 menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 240,
            name = "Cookies Oatmeal",
            calories = 35.0,
            carbs = 9.82,
            fat = 2.65,
            protein = 1.93,
            ingredients = "35 gram kenari\n" +
                    "1/2 sdt kayu manis\n" +
                    "135 gram pisang\n" +
                    "135 g oatmeal\n" ,
            cookingSteps = "1. Haluskan pisang.\n" +
                    "2. Masukkan semua bahan, aduk sampai tercampur rata.\n" +
                    "3. Beratnya sekitar 24-25gr per porsi, hasilnya 13 cookie. Bulatkan lalu ratakan.\n" +
                    "4. Panggang dengan suhu 175° selama 12-15 menit.\n" ,
            recipePictures = "cookies_oatmeal",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 13
        ),
Recipe(
            recipeId = 241,
            name = "Sayur Tumis Sawi Telur",
            calories = 130.0,
            carbs = 13.25,
            fat = 7.25,
            protein = 4.66,
            ingredients = "1 sedang telur\n" +
                    "2 sdt cabai merah atau rawit\n" +
                    "1 kecil wortel\n" +
                    "1 siung bawang putih\n" +
                    "1/2 utuh sedang tomat merah\n" +
                    "80 gram sawi putih\n" +
                    "2 kecil bawang merah\n" +
                    "1 sendok makan minyak goreng\n" ,
            cookingSteps = "1. Iris bawang, cabai, tomat, sawi, wortel.\n" +
                    "2. Panaskan minyak.\n" +
                    "3. Tumis bawang, cabai.\n" +
                    "4. Masukkan wortel dan tomat.\n" +
                    "5. Tumis hingga empuk, tambahkan bumbu sesuai selera.\n" +
                    "6. Masukkan sawi.\n" +
                    "7. Tambahkan sedikit air. Masak sampai matang.\n" ,
            recipePictures = "sayur_tumis_sawi_telur",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 242,
            name = "Bubur Oat",
            calories = 413.0,
            carbs = 38.0,
            fat = 14.16,
            protein = 35.49,
            ingredients = "100 g dada ayam\n" +
                    "100 g bawang bombay dimasak\n" +
                    "100 g wortel\n" +
                    "100 g oatmeal\n" +
                    "100 g sawi hijau\n" ,
            cookingSteps = "1. Tumis bawang bombay sampai harum dengan minyak kelapa.\n" +
                    "2. Setelah harum, masukan sedikit air.\n" +
                    "3. Masukkan ayam, lalu sawi dn terakhir wortel.\n" +
                    "4. Setelah topping agak layu , masukkan oat nya kurang lebih 3-5menit ( bila air diatas habis, boleh ditambah untuk metebus oatnya. Sedikit saja ya).\n" +
                    "5. Tambahkan sedikit garam. Koreksi rasa.\n" ,
            recipePictures = "bubur_oat",
            mealType = 2,
            cookTime = "7 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 249,
            name = "Yellow Smoothies",
            calories = 80.0,
            carbs = 21.0,
            fat = 0.26,
            protein = 0.7,
            ingredients = "100 gram mangga\n" +
                    "150 gram buah pir\n" +
                    "50 gram mentimun (dengan kulit)\n" +
                    "130 gram air\n" ,
            cookingSteps = "1. Masukkan semua bahan ke dalam blender.\n" +
                    "2. Tambahkan air lalu Blender semua bahan sampai halus.\n" +
                    "3. Siap disajikan.\n" ,
            recipePictures = "yellow_smoothies",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "20 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 251,
            name = "Telur Kukus",
            calories = 230.0,
            carbs = 5.56,
            fat = 12.5,
            protein = 22.64,
            ingredients = "63 gram daging sapi\n" +
                    "1 1/2 besar putih telur\n" +
                    "12 gram buncis\n" +
                    "29 gram wortel\n" +
                    "11 gram daun bawang\n" ,
            cookingSteps = "1. Iris cincang wortel, daun bawang, serta buncis. Sisihkan.\n" +
                    "2. Kocok putih telur dengan pengocok telur hingga berbusa.\n" +
                    "3. Masukan wortel, daun bawang, buncis yang telah diiris cincang serta daging sapi yang dipotong kecil-kecil ke dalam kocokan putih telur. Tambahkan garam, merica, dan penyedap. Aduk hingga rata.\n" +
                    "4. Masukan adonan ke dalam wadah anti panas, lalu kukus selama 30 menit.\n" +
                    "5. Setelah 30 menit angkat lalu tiriskan.\n" ,
            recipePictures = "telur_kukus",
            mealType = 2,
            cookTime = "45 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 252,
            name = "Bubur Oatmeal Asin",
            calories = 223.0,
            carbs = 27.49,
            fat = 7.99,
            protein = 12.26,
            ingredients = "1 irisan tipis ayam\n" +
                    "1/2 besar telur rebus\n" +
                    "1/4 mangkok jamur dimasak (lemak tidak ditambahkan dalam masakan)\n" +
                    "1/2 sdt garam\n" +
                    "1/2 sdm seledri\n" +
                    "1 sdm, cincang daun bawang (loncang)\n" +
                    "1/4 sosis ayam\n" +
                    "0,5 g royco ayam\n" +
                    "35 g instant oatmeal grain\n" +
                    "0,75 g lada putih bubuk\n" ,
            cookingSteps = "1. Nyalakan kompor.\n" +
                    "2. Masukkan oatmeal ke teflon dan tambahkan air panas, aduk jangan sampai terlalu encer.\n" +
                    "3. Tambahkan garam, lada, daun bawang, dan penyedap rasa, aduk.\n" +
                    "4. Hias bubur dengan topping.\n" +
                    "5. Koreksi rasa, jika sudah pas, tuang ke mangkuk.\n" +
                    "6. Tambahkan air lagi sedikit demi sedikit sambil diaduk sampai tekstur seperti bubur.\n" +
                    "7. Selamat menikmati.\n" ,
            recipePictures = "bubur_oatmeal_asin",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 258,
            name = "Mini Pancakes",
            calories = 353.0,
            carbs = 62.77,
            fat = 8.04,
            protein = 10.92,
            ingredients = "1 sedang telur\n" +
                    "1/2 buah kiwi\n" +
                    "120 gram pisang\n" +
                    "1 gram biji wijen kering utuh\n" +
                    "1/2 sdm madu\n" +
                    "30 g oats\n" ,
            cookingSteps = "1. Blender oat, pisang, telur, bp sampai halus.\n" +
                    "2. Panggang adonan sedikit-sedikit (pakai sendok teh) pada teflon anti lengket tanpa menambahkan minyak/margarin. Gunakan api kecil.\n" +
                    "3. Balik adonan saat timbul gelembung pada pancake.\n" +
                    "4. Sajikan dengan toping madu, wijen, dan buah.\n" ,
            recipePictures = "mini_pancakes",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 266,
            name = "Manggo Sticky Oats",
            calories = 262.0,
            carbs = 48.62,
            fat = 8.26,
            protein = 2.82,
            ingredients = "95 gram mango\n" +
                    "1/2 tsp sugar\n" +
                    "2 sejumput salt\n" +
                    "50 ml water\n" +
                    "100 ml water\n" +
                    "6 sdt fiber creme\n" +
                    "6 g tepung pati jagung\n" +
                    "20 g quick cook oatmeal\n" ,
            cookingSteps = "1. Kupas mangga terlebih dahulu lalu tata kedalam piring.\n" +
                    "2. Selanjutnya masak oats dengan 200ml air, tambahkan garam secukupnya lalu masak hingga air menyusut. Taruh kedalam piring.\n" +
                    "3. Setelah itu buat saus dengan memanaskan 50ml air, tambahkan 6 sdt fibercrem lalu aduk hingga merata.\n" +
                    "4. Tambahkan sedikit garam dan 1/2 sdt gula, aduk kembali lalu tambahkan 1/2 sdm tepung maizena.\n" +
                    "5. Aduk hingha rata dan mengental.\n" +
                    "6. Siramkan diatas mangga dan oats yang telah di tata.\n" +
                    "7. Selesai.\n" ,
            recipePictures = "manggo_sticky_oats",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "3 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 273,
            name = "Nasi Goreng Oatmeal",
            calories = 336.0,
            carbs = 53.09,
            fat = 12.31,
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
                    "1 sdm kecap manis\n" ,
            cookingSteps = "1. Cincang bawang putih dan potong sayuran yang telah dicuci.\n" +
                    "2. Panaskan teflon lalu masukan margarin hingga mencair.\n" +
                    "3. Tumis baput cincang hingga harum.\n" +
                    "4. Masukan air, disusul dengan sayuran.\n" +
                    "5. Tumis sayuran hingga setengah matang lalu masukan daun bawang dan oatmeal.\n" +
                    "6. Terakhir masukan bumbu seperti kecap,garam,dan lada.\n" ,
            recipePictures = "nasi_goreng_oatmeal",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 277,
            name = "Ayam Crispy Oatmil",
            calories = 284.0,
            carbs = 14.82,
            fat = 16.06,
            protein = 20.48,
            ingredients = "100 gram dada ayam\n" +
                    "1 besar telur\n" +
                    "1 sdm bubuk cabai\n" +
                    "1 sdt, tumbuk oregano\n" +
                    "1 sdt garam\n" +
                    "1 sdm olive oil\n" +
                    "2 sdm oat\n" ,
            cookingSteps = "1. Slice dada ayam ketebalan sesuai selera lalu bumbui dada ayam dgn garam dan bubuk cabai (optional).\n" +
                    "2. Kocok telur.\n" +
                    "3. Tuang quacker oat ke dalam mangkuk lalu bumbui lg dgn bubuk cabai, garlic powder, dan oregano.\n" +
                    "4. Tuang sedikit olive oil ke teflon klo bisa (anti lengket) yg sudah dipanaskan.\n" +
                    "5. Lumuri dada ayam dengan telur lalu balurkan ke oat yg sudah dibumbui. Panggang di dalam teflon dgn api sedang.\n" +
                    "6. Angkat ayam jika sudah kecoklatan.\n" ,
            recipePictures = "ayam_crispy_oatmil",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 279,
            name = "Roti Tawar",
            calories = 134.0,
            carbs = 21.25,
            fat = 3.34,
            protein = 4.17,
            ingredients = "1 besar telur\n" +
                    "3 1/2 gram ragi\n" +
                    "20 gram margarin\n" +
                    "1 sejumput garam\n" +
                    "120 ml air\n" +
                    "20 gram gula bubuk\n" +
                    "180 gram tepung terigu putih (semua keperluan)\n" +
                    "30 g susu bubuk full cream\n" ,
            cookingSteps = "1. Campurkan semua bahan kecuali margarin dan garam.\n" +
                    "2. Setelah bahan tercampur, masukan margarin dan aduk hingga semua bahan tercampur dan adonan menjadi kalis.\n" +
                    "3. Istirahatkan adonan selama 1 jam, biarkan hingga adonan mengembang 2 kali lipat dari ukuran semula.\n" +
                    "4. Panggan adonan menggunakan oven selama 30 menit dengan suhu 180°C.\n" +
                    "5. Setelah roti matang, oleskan margarin pada roti selagi hangat.\n" +
                    "6. Roti siap disajikan.\n" ,
            recipePictures = "roti_tawar",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "1 Jam",
            portion = 8
        ),
Recipe(
            recipeId = 280,
            name = "Sup Sehat",
            calories = 56.0,
            carbs = 3.0,
            fat = 2.92,
            protein = 4.53,
            ingredients = "20 telur puyuh\n" +
                    "100 gram buncis\n" +
                    "50 gram brokoli\n" +
                    "100 gram wortel\n" +
                    "5 siung bawang putih\n" +
                    "5 gram daun bawang\n" +
                    "30 gram jamur putih (ditumis)\n" +
                    "1 g lada putih bubuk\n" +
                    "3 sosis ayam\n" +
                    "50 gram kaldu jamur\n" ,
            cookingSteps = "1. Potong2 semua bahan sesuai selera.\n" +
                    "2. Rebus telur puyuh dan kupas.\n" +
                    "3. Rebus 750mL air.\n" +
                    "4. Geprek bawang putih dan masukkan k air yang telah mendidih.\n" +
                    "5. Masukkan wortel, buncis, brokoli ke dalam air.\n" +
                    "6. Biarkan 1/2 lunak dan masukkan sosis, jamur, telur puyuh.\n" +
                    "7. Bumbui dengan kaldu jamur dan lada putih. Koreksi rasa (bisa d tambahkan gula, garam, dll sesuai keinginan. Kalori perlu d tambahkn untuk modifikasi).\n" +
                    "8. Tambahkan irisan bawang daun (saya pakai yang putih) dan siap d hidangkan.\n" ,
            recipePictures = "sup_sehat",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "20 Menit",
            portion = 10
        ),
Recipe(
            recipeId = 282,
            name = "Salad Ayam",
            calories = 376.0,
            carbs = 41.93,
            fat = 13.98,
            protein = 22.51,
            ingredients = "60 gram chicken breast\n" +
                    "2 mangkok, parut atau cincang lettuce\n" +
                    "90 gram cooked corn\n" +
                    "1/2 sendok makan light mayonnaise\n" +
                    "50 gram boiled potato\n" ,
            cookingSteps = "1. Bumbui ayam dengan garam merica, panggang.\n" +
                    "2. Kukus jagung, kentang.\n" +
                    "3. Potong n cuci lettuce.\n" +
                    "4. Campur smuanya.\n" +
                    "5. Pakein mayo / salad dressing.\n" ,
            recipePictures = "salad_ayam",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 283,
            name = "Bolu Cup",
            calories = 59.0,
            carbs = 9.88,
            fat = 1.3,
            protein = 1.87,
            ingredients = "220 gram telur\n" +
                    "120 gram gula pasir\n" +
                    "150 gram tepung terigu putih (semua keperluan)\n" +
                    "50 g keju serbaguna\n" ,
            cookingSteps = "1. Kocok telur dan gula hingga mengembang.\n" +
                    "2. Tambahkan tepung sedikit demi sedikit.\n" +
                    "3. Masukkan ke cup.\n" +
                    "4. Taburin keju.\n" +
                    "5. Panggang dg suhu 200 C selama 30 menit.\n" ,
            recipePictures = "bolu_cup",
            mealType = 1,
            cookTime = "45 Menit",
            prepTime = "15 Menit",
            portion = 25
        ),
Recipe(
            recipeId = 285,
            name = "Kue Muesli",
            calories = 78.0,
            carbs = 13.85,
            fat = 1.8,
            protein = 2.08,
            ingredients = "1 sedang telur\n" +
                    "2 sdm madu\n" +
                    "100 g tropical muesli\n" ,
            cookingSteps = "1. Campur semua bahan.\n" +
                    "2. Bentuk kue dan sebarkan di atas loyang.\n" +
                    "3. Panggang dalam oven selama 20 menit dengan suhu 170°C.\n" ,
            recipePictures = "kue_muesli",
            mealType = 1,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 7
        ),
Recipe(
            recipeId = 286,
            name = "Sup Baso Ikan",
            calories = 70.0,
            carbs = 7.47,
            fat = 1.26,
            protein = 6.9,
            ingredients = "7 1/2 gram kaldu jamur\n" +
                    "1 sdt garam\n" +
                    "1 gram minyak wijen\n" +
                    "2 gram daun bawang\n" +
                    "20 gram sawi putih\n" +
                    "1 gelas air\n" +
                    "117 gram bakso ikan\n" +
                    "20 gram sawi hijau\n" ,
            cookingSteps = "1. Siapkan bahan, masak air sampai mendidih.\n" +
                    "2. Tambhan minyak wijen, kaldu dan garam, cicipi rasa.\n" +
                    "3. Masukan baso, tekakhir masukan sayuran, siapkan wadah\n" ,
            recipePictures = "sup_baso_ikan",
            mealType = 3,
            cookTime = "30 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 291,
            name = "Oseng Tahu",
            calories = 231.0,
            carbs = 8.76,
            fat = 18.41,
            protein = 10.84,
            ingredients = "43 gram bawang bombay dimasak\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdm minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "51 gram kubis\n" +
                    "9 gram daun bawang\n" +
                    "1 sejumput garam laut\n" +
                    "250 gram tahu\n" +
                    "1 sdm minyak jagung\n" ,
            cookingSteps = "1. Iris bawang bombay, daun bawang, kubis sesuai selera.\n" +
                    "2. Siapkan wajan anti lengket, beri corn oil lalu masukkan daun bawang tumis hingga harum.\n" +
                    "3. Setelah itu masukkan bombay, tumis hingga harum.\n" +
                    "4. Kecilkan api, masukkan tahu lalu lancurkan. Oseng hingga air tahu mengering.\n" +
                    "5. Tambahkan minyak wijen, saus tiram, lada, garam oseng hingga tercampur rata selama 75 detik.\n" ,
            recipePictures = "oseng_tahu",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 293,
            name = "Nasi Goreng",
            calories = 206.0,
            carbs = 31.29,
            fat = 7.51,
            protein = 4.09,
            ingredients = "92 gram kecambah\n" +
                    "1 sdm minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "62 gram kubis\n" +
                    "100 gram wortel\n" +
                    "60 gram nasi merah (butir-sedang, dimasak)\n" +
                    "60 gram nasi putih (butir-sedang, dimasak)\n" +
                    "2 sdm saus sambal\n" ,
            cookingSteps = "1. Tulis wortel selama 1 menit, lalu masukkan gubis tumis kembali.\n" +
                    "2. Masukkan nasi, tumis hingga rata lalu masukkan kecambah.\n" +
                    "3. Tambahkan saus sambal, saus tiram, minyak wijen.\n" ,
            recipePictures = "nasi_goreng",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 301,
            name = "Oat Pisang",
            calories = 162.0,
            carbs = 32.95,
            fat = 3.89,
            protein = 2.28,
            ingredients = "2 sedang pisang\n" +
                    "8 g mentega\n" +
                    "100 g oatmeal\n" ,
            cookingSteps = "1. Hancurkan pisang.\n" +
                    "2. Tambahkan oatmeal dan mentega cair dan aduk rata.\n" +
                    "3. Bentuk kue dan letakkan di atas loyang. Panggang selama 20 menit pada suhu 180°C.\n" ,
            recipePictures = "oat_pisang",
            mealType = 3,
            cookTime = "0 menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 302,
            name = "Brownies Oat",
            calories = 158.0,
            carbs = 18.53,
            fat = 8.67,
            protein = 2.16,
            ingredients = "2 sedang telur\n" +
                    "20 gram cocoa powder (unsweetened)\n" +
                    "130 gram brown sugar\n" +
                    "100 g instant oatmeal\n" +
                    "63 ml minyak kanola\n" +
                    "20 g fiber creme\n" +
                    "10 g tepung pati jagung\n" +
                    "104 ml dark compound chocolate\n" ,
            cookingSteps = "1. Lelehkan dark chocolate dengan minyak. Menyisihkan.\n" +
                    "2. Campur bahan yang tersisa.\n" +
                    "3. Terakhir, tambahkan campuran cokelat leleh. Campur dengan baik.\n" +
                    "4. Tempatkan adonan ke dalam cetakan dan hiasi sesuai keinginan. Panggang selama 20 menit pada suhu 180°C.\n" ,
            recipePictures = "brownies_oat",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "20 Menit",
            portion = 16
        ),
Recipe(
            recipeId = 303,
            name = "Muffins Pisang",
            calories = 113.0,
            carbs = 19.5,
            fat = 0.9,
            protein = 7.86,
            ingredients = "1 besar putih telur\n" +
                    "2 gram kayu manis\n" +
                    "1 sdt ekstrak vanila\n" +
                    "1 sedang pisang\n" +
                    "1 gram baking soda\n" +
                    "1 sdm madu\n" +
                    "1 bungkus stevia sweetener\n" +
                    "1 scoop impact whey isolate\n" +
                    "1/2 gelas kering rolled oats\n" ,
            cookingSteps = "1. Pisahkan telur dan kocok putih telurnya.\n" +
                    "2. Hancurkan pisang lalu tambahkan putih telur, vanila, dan madu. Campur dengan baik.\n" +
                    "3. Aduk sisa bahan dan aduk rata.\n" +
                    "4. Bagi menjadi cangkir muffin dan hiasi sesuai keinginan. Kukus selama 20 menit.\n" ,
            recipePictures = "muffins_pisang",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "15 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 304,
            name = "Kue Chiffon",
            calories = 181.0,
            carbs = 17.28,
            fat = 9.88,
            protein = 5.67,
            ingredients = "80 gram keju cheddar\n" +
                    "6 besar putih telur\n" +
                    "6 besar kuning telur\n" +
                    "70 ml air\n" +
                    "125 gram gula pasir\n" +
                    "100 gram tepung terigu putih (semua keperluan)\n" +
                    "70 ml minyak goreng\n" ,
            cookingSteps = "1. Kocok putih telur dengan 100 gr gula hingga kaku.\n" +
                    "2. Di wadah lain, kocok kuning telur dengan 25 gr gula hingga berbusa. Kemudian tambahkan air dan minyak, aduk rata. Terakhir, tambahkan tepung dan aduk.\n" +
                    "3. Campurkan 2 campuran dan tempatkan dalam loyang kue. Hiasi keju cheddar di atasnya.\n" +
                    "4. Panggang dalam oven dengan suhu 160°C selama 50-55 menit.\n" +
                    "5. Tunggu dingin baru keluarkan dari loyang.\n" ,
            recipePictures = "kue_chiffon",
            mealType = 1,
            cookTime = "55 Menit",
            prepTime = "20 Menit",
            portion = 12
        ),
Recipe(
            recipeId = 307,
            name = "Banana Oatmeal Pancake",
            calories = 120.0,
            carbs = 18.8,
            fat = 4.11,
            protein = 3.22,
            ingredients = "1 besar putih telur\n" +
                    "2 sdt minyak zaitun\n" +
                    "1 sedang pisang\n" +
                    "50 ml air\n" +
                    "70 g oatmeal\n" ,
            cookingSteps = "1. Masukan pisang, oatmeal, dan air ke dalam blender.\n" +
                    "2. Blender hingga halus, kemudian pindah ke dalam mangkuk dan masukan putih telur kemudian di kocok dengan menggunakan sendok hingga tercampur rata.\n" +
                    "3. Masak dengan menggunakan teflon dan beri minyak zaitun, masak hingga matang.\n" ,
            recipePictures = "banana_oatmeal_pancake",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 308,
            name = "Pancake Oat",
            calories = 203.0,
            carbs = 28.83,
            fat = 1.07,
            protein = 14.69,
            ingredients = "20 g susu rendah lemak\n" +
                    "100 g putih telur\n" +
                    "35 g gandum\n" ,
            cookingSteps = "1. Panaskan pan dengan api sedang, jika dikira sudah panas kecilkan api.\n" +
                    "2. Campur rata semua bahan-bahan, jika mau tambahkan sweetener untuk pengganti gula jika ingin manis jika tidak juga gapapa.\n" +
                    "3. Jika sudah tercampur rata semua bahan, tuang adonan ke pan yang sudah dipanaskan ingat dengan api kecil, tunggu sampai matang. Pancake oat siap di hidangkan. Bisa di tambahkan topping tapi yang sehat.\n" ,
            recipePictures = "pancake_oat",
            mealType = 1,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 310,
            name = "Tumis Oatmeal Dengan Sayur",
            calories = 273.0,
            carbs = 59.94,
            fat = 0.5,
            protein = 7.1,
            ingredients = "1 sejumput lada\n" +
                    "1 sdt garam\n" +
                    "1 mangkok, dicincang wortel\n" +
                    "15 gram bawang daun\n" +
                    "1 mangkok bayam\n" +
                    "14 g bawang merah\n" +
                    "3 g bawang putih\n" +
                    "53 g oatmeal\n" ,
            cookingSteps = "1. Sangrai oatmeal, kurang lebih 5 menit, sampai tercium bau dan tekstur nya lebih garing. Sisihkan.\n" +
                    "2. Tumis Bawang merah, bawang putih, bawang daun sampai harum, masukkan garam dan lada putih, tambah air 1/4 cangkir. masak kurang lebih 5 menit.\n" +
                    "3. Pada saat sayur matang, masukkan oatmeal yang sudah disangrai tadi. Kalau kental masukkan air secukupnya sampai air terserap semuanya. Hidangkan.\n" ,
            recipePictures = "tumis_oatmeal_dengan_sayur",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 311,
            name = "Sup Labu Energi",
            calories = 249.0,
            carbs = 52.28,
            fat = 0.8,
            protein = 14.29,
            ingredients = "1 gelas susu tanpa lemak\n" +
                    "500 gram labu kuning\n" +
                    "10 gram tepung\n" ,
            cookingSteps = "1. Potong labu hingga berbentuk bulan sabit seperti semangka potong, kulit dan biji jangan dulu dikupas. Kukus hingga setengah matang +/- 10 menit. Tujuan agar labu cukup empuk untuk diblender.\n" +
                    "2. Keluarkan labu dari kukusan biarkan sebentar agar dingin. Lalu kupas kulit dan buang bijinya. Potong dadu dan blender (bisa ditambah air secukupnya).\n" +
                    "3. Labu yang sudah diblender, lalu tuang ke panci. Lalu masak dengan api kecil-sedang. Perlahan tambahkan susu (kekentalan tergantung selera). Terus tambahkan 1 sdm tepung maizena untuk mengentalkan sup. Penyajian bisa dimakan selagi hangat atau dingin (simpan di kulkas dulu).\n" ,
            recipePictures = "sup_labu_energi",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 313,
            name = "Sup Pembakar Lemak",
            calories = 48.0,
            carbs = 9.66,
            fat = 0.85,
            protein = 2.27,
            ingredients = "2 mangkok kembang kol\n" +
                    "1 sedang paprika\n" +
                    "3 utuh sedang tomat\n" +
                    "2 ons bawang bombay\n" ,
            cookingSteps = "1. Potong semua bahan.\n" +
                    "2. Rebus air hingga mendidih lalu masak ayam. Sisihkan saat Anda selesai.\n" +
                    "3. Dalam wajan yang sudah dipanaskan, tumis bawang bombay lalu tambahkan cabai dan lengkuas. Aduk dan tambahkan sisa bumbu dan rempah-rempah.\n" +
                    "4. Masukkan ayam dan tambahkan sedikit air.\n" +
                    "5. Tambahkan daun bawang cincang halus. Aduk rata.\n" ,
            recipePictures = "sup_pembakar_lemak",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 7,
            name = "Mie Shirataki Goreng",
            calories = 175.0,
            carbs = 19.04,
            fat = 5.92,
            protein = 9.13,
            ingredients = "1 sejumput salt\n" +
                    "1 buah utuh portabella mushrooms\n" +
                    "30 gram cherry tomatoes\n" +
                    "3 ml minyak goreng\n" +
                    "1 sdm kecap manis\n" +
                    "75 g mie kering shirataki\n" +
                    "8 g sambal bawang\n" +
                    "45 g dada ayam\n" +
                    "65 g telur omega\n" ,
            cookingSteps = "1. Panaskan wajan dengan minyak dan tumis irisan dada ayam dan jamur.\n" +
                    "2. Tambahkan telur orak-arik sebelumnya, irisan tomat, dan bahan lainnya, masak hingga matang.\n" +
                    "3. Pindahkan ke piring dan sajikan. Menikmati.\n" ,
            recipePictures = "mie_shirataki_goreng",
            mealType = 2,
            cookTime = "10 menit",
            prepTime = "15 menit",
            portion = 2
        ),
Recipe(
            recipeId = 17,
            name = "Ayam dengan Jagung dan Kacang",
            calories = 200.0,
            carbs = 18.28,
            fat = 4.5,
            protein = 24.91,
            ingredients = "8 gram cabai merah atau rawit\n" +
                    "69 gram daging dada ayam (ayam pedaging, dipanggang, dimasak)\n" +
                    "27 gram buncis\n" +
                    "50 gram jagung manis kuning\n" +
                    "7 gram bawang putih\n" ,
            cookingSteps = "1. Panaskan tempel dalam wajan antilengket lalu tambahkan ayam. Tambahkan air sesuai kebutuhan.\n" +
                    "2. Masukkan jagung dan buncis. Didihkan selama 5 menit.\n" +
                    "3. Sajikan di atas piring.\n" ,
            recipePictures = "ayam_dengan_jagung_dan_kacang",
            mealType = 2,
            cookTime = "10 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 18,
            name = "Sup Sosis dengan Sayuran",
            calories = 256.0,
            carbs = 21.09,
            fat = 11.33,
            protein = 19.04,
            ingredients = "1 besar telur\n" +
                    "100 gram buncis\n" +
                    "100 gram wortel\n" +
                    "65 g sosis sapi & ayam\n" ,
            cookingSteps = "1. Rebus air secukupnya lalu masukkan irisan sayuran.\n" +
                    "2. Masukkan irisan sosis dan telur kocok.\n" +
                    "3. Bumbui sesuai keinginan dan masak selama 5 menit.\n" ,
            recipePictures = "sup_sosis_dengan_sayuran",
            mealType = 2,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 20,
            name = "Sop Tulang Iga Sapi",
            calories = 156.0,
            carbs = 6.75,
            fat = 9.75,
            protein = 10.57,
            ingredients = "100 gram tomat\n" +
                    "100 gram wortel\n" +
                    "100 gram iga sapi (potongan kecil, diiris hingga 0.3 cm lemak)\n" ,
            cookingSteps = "1. Rebus daging sapi dengan air secukupnya selama 30 menit.\n" +
                    "2. Tambahkan irisan sayuran dan isi air sesuai kebutuhan.\n" +
                    "3. Bumbui sesuai selera dan masak hingga empuk.\n" +
                    "4. Sajikan dalam mangkuk dan hiasi sesuai keinginan.\n" ,
            recipePictures = "sop_tulang_iga_sapi",
            mealType = 2,
            cookTime = "40 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 21,
            name = "Ikan Tongkol Asam Pedas",
            calories = 100.0,
            carbs = 18.39,
            fat = 0.63,
            protein = 7.04,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "100 gram asam jawa\n" +
                    "1 siung bawang putih\n" +
                    "1 kecil bawang merah\n" +
                    "100 gram ikan tongkol\n" ,
            cookingSteps = "1. Rebus tuna dalam air hingga empuk. Tiriskan dan potong.\n" +
                    "2. Dalam wajan yang sudah diolesi sedikit minyak, tumis bahan lainnya hingga harum.\n" +
                    "3. Tambahkan tuna dan aduk.\n" +
                    "4. Didihkan selama 5 menit.\n" ,
            recipePictures = "ikan_tongkol_asam_pedas",
            mealType = 2,
            cookTime = "45 menit",
            prepTime = "25 menit",
            portion = 4
        ),
Recipe(
            recipeId = 23,
            name = "Rebusan Tahu dengan Kecambah",
            calories = 61.0,
            carbs = 3.65,
            fat = 3.83,
            protein = 4.44,
            ingredients = "120 gram kecambah\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "1/2 utuh sedang tomat merah\n" +
                    "500 ml air\n" +
                    "157 gram tahu\n" +
                    "7 ml minyak goreng\n" +
                    "7 g saos tiram\n" ,
            cookingSteps = "1. Potong tahu dan sayuran.\n" +
                    "2. Panaskan minyak dalam wajan dan tumis bawang putih sampai keemasan.\n" +
                    "3. Tambahkan air dan biarkan mendidih.\n" +
                    "4. Masukkan sisa bahan dan bumbui. Masak sampai empuk.\n" ,
            recipePictures = "rebusan_tahu_dengan_kecambah",
            mealType = 2,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 4
        ),
Recipe(
            recipeId = 29,
            name = "Mangkuk Sushi Salmon",
            calories = 792.0,
            carbs = 7.56,
            fat = 67.38,
            protein = 22.58,
            ingredients = "87 gram ikan salmon panggang\n" +
                    "5 ml kecap\n" +
                    "2 g nori\n" +
                    "180 g wijen panggang mayones\n" +
                    "40 g nasi porang\n" ,
            cookingSteps = "1. Suwir salmon yang sudah matang menggunakan garpu.\n" +
                    "2. Masukkan nasi ke dalam mangkuk.\n" +
                    "3. Tambahkan semua bahan dan hiasi dengan nori.\n" ,
            recipePictures = "mangkuk_sushi_salmon",
            mealType = 2,
            cookTime = "0 menit",
            prepTime = "15 menit",
            portion = 1
        ),
Recipe(
            recipeId = 32,
            name = "Nasi Pizza dengan Ayam dan Sayuran",
            calories = 234.0,
            carbs = 19.4,
            fat = 11.46,
            protein = 13.29,
            ingredients = "100 gram dada ayam\n" +
                    "1 kecil telur\n" +
                    "200 gram nasi putih\n" +
                    "10 gram wortel dimasak\n" +
                    "10 gram buncis\n" +
                    "2 sdm mentega\n" +
                    "3 g kaldu jamur\n" ,
            cookingSteps = "1. Potong dadu dada ayam dan wortel.\n" +
                    "2. Rebus dalam air bersama dengan buncis selama 10 menit.\n" +
                    "3. Giling semuanya dalam blender (kecuali mentega).\n" +
                    "4. Panaskan wajan dengan mentega dan masak campuran sampai berwarna keemasan.\n" ,
            recipePictures = "nasi_pizza_dengan_ayam_dan_sayuran",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 3
        ),
Recipe(
            recipeId = 33,
            name = "Kari Tuna",
            calories = 76.0,
            carbs = 2.24,
            fat = 1.36,
            protein = 13.63,
            ingredients = "400 gram ikan tuna\n" +
                    "6 gram cabai merah atau rawit\n" +
                    "2 gram kunyit\n" +
                    "3 g stevia sweetener\n" +
                    "10 g santan bubuk\n" +
                    "9 g bumbu gulai\n" ,
            cookingSteps = "1. Campur bubuk dan larutkan dengan air secukupnya.\n" +
                    "2. Masukkan ke dalam panci dan didihkan sambil diaduk.\n" +
                    "3. Tambahkan tuna dan merica. Masak hingga empuk.\n" ,
            recipePictures = "kari_tuna",
            mealType = 2,
            cookTime = "30 menit",
            prepTime = "10 menit",
            portion = 7
        ),
Recipe(
            recipeId = 34,
            name = "Toge Telur",
            calories = 252.0,
            carbs = 26.73,
            fat = 6.84,
            protein = 21.9,
            ingredients = "1 besar egg\n" +
                    "150 gram sprouts\n" +
                    "100 ml water\n" +
                    "50 g bakso daging sapi\n" +
                    "2 sdm saos tiram\n" +
                    "1 bungkus level 30\n" +
                    "1 sdm kecap manis sedaap\n" ,
            cookingSteps = "1. Rebus air, masukkan toge.\n" +
                    "2. Potong potong bakso, kocok telur lalu masukkan ke rebusan air.\n" +
                    "3. Tambah kecap, saori dan sedikit bubuk cabai. Rebus hingga matang.\n" ,
            recipePictures = "toge_telur",
            mealType = 1,
            cookTime = "15 menit",
            prepTime = "5 menit",
            portion = 1
        ),
Recipe(
            recipeId = 38,
            name = "Ayam Rica Rica",
            calories = 219.0,
            carbs = 5.88,
            fat = 14.4,
            protein = 17.83,
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
                    "5 gram lengkuas\n" ,
            cookingSteps = "1. Cuci bersih ayam lumuri garam dan royco diamkan 30 menit lalu kukus hingga matang.\n" +
                    "2. Haluskan cabai, bawang merah, bawang putih, jahe, kemiri, dan lengkuas.\n" +
                    "3. Panaskan minyak, tumis bumbu halus masukan gula pasir, tumis hingga harum tambahkan sedikit air, masukan ayam masak hingga air susut.\n" +
                    "4. Terakhir masukan daun kemangi, masak hingga matang angkat sajikan.\n" ,
            recipePictures = "ayam_rica_rica",
            mealType = 1,
            cookTime = "20 menit",
            prepTime = "30 menit",
            portion = 5
        ),
Recipe(
            recipeId = 40,
            name = "Sawi Isi Ayam Udang",
            calories = 131.0,
            carbs = 9.82,
            fat = 4.94,
            protein = 12.51,
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
                    "7 g sajiku tepung bumbu serbaguna\n" ,
            cookingSteps = "1. Kukus sawi hingga lunak, diamkan.\n" +
                    "2. Giling semua bahan (selain sawi) menggunakan chopper.\n" +
                    "3. Lebarkan lembaran sawi, beri adonan hasil gilingan, gulung.\n" +
                    "4. Kukus selama 20 menit.\n" ,
            recipePictures = "sawi_isi_ayam_udang",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "20 menit",
            portion = 2
        ),
Recipe(
            recipeId = 43,
            name = "Tumis Buncis Tempe",
            calories = 111.0,
            carbs = 9.9,
            fat = 4.89,
            protein = 9.5,
            ingredients = "5 gram kaldu jamur\n" +
                    "225 gram buncis\n" +
                    "18 gram bawang putih\n" +
                    "200 ml air\n" +
                    "223 gram tempe\n" +
                    "39 gram bawang bombay\n" +
                    "10 g saus tiram\n" ,
            cookingSteps = "1. Potong-potong buncis dan tempe. Cincang bawang putih dan bawang bombay.\n" +
                    "2. Didihkan air, tambahkan bawang bombay, bawang putih, saus tiram dan kaldu jamur.\n" +
                    "3. Masukkan tempe dan buncis. Masak sampai buncis empuk.\n" +
                    "4. Siap disajikan.\n" ,
            recipePictures = "tumis_buncis_tempe",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 5
        ),
Recipe(
            recipeId = 47,
            name = "Sandwich",
            calories = 467.0,
            carbs = 38.12,
            fat = 23.82,
            protein = 20.7,
            ingredients = "1 daun sedang selada\n" +
                    "100 gram telur ceplok\n" +
                    "2 lembar roti tawar gandum\n" +
                    "1 sachet saus sambal\n" +
                    "1 slice keju slice\n" ,
            cookingSteps = "1. Siap kan bahan.\n" +
                    "2. Panggang roti di pan tanpa minyak hingga mencoklat rata.\n" +
                    "3. Ceplok telur, tambahkan garam sejumput, kocok telur dan goreng.\n" +
                    "4. Susun roti dengan bahan bahan yang sudah disiapkan.\n" ,
            recipePictures = "sandwich",
            mealType = 1,
            cookTime = "5 menit",
            prepTime = "5 menit",
            portion = 1
        ),
Recipe(
            recipeId = 48,
            name = "Tumis Tempe Labu Kacang Panjang",
            calories = 90.0,
            carbs = 7.73,
            fat = 4.04,
            protein = 7.69,
            ingredients = "216 gram kacang panjang hijau\n" +
                    "5 gram kaldu jamur\n" +
                    "2 gram garam\n" +
                    "350 gram labu siam\n" +
                    "21 gram bawang putih\n" +
                    "200 ml air\n" +
                    "366 gram tempe\n" +
                    "1 g lada putih bubuk\n" +
                    "33 gram bawang bombay\n" +
                    "11 g saus tiram\n" ,
            cookingSteps = "1. Potong potong tempe, labu, kacang panjang, dan bawang bombay sesuai selera. Cincang bawang putih. Sisihkan.\n" +
                    "2. Didihkan air. Tambahkan bawang bombay, bawang putih, saus tiram, kaldu jamur, garam, dan lada.\n" +
                    "3. Masukkan tempe. Aduk rata. Diamkan sebentar sampai bumbu meresap ke tempe.\n" +
                    "4. Masukkan kacang panjang dan labu siam. Aduk rata, diamkan sebentar sampai sayuran matang.\n" +
                    "5. Siap disajikan.\n" ,
            recipePictures = "tumis_tempe_labu_kacang_panjang",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "15 menit",
            portion = 10
        ),
Recipe(
            recipeId = 49,
            name = "Tumis Kacang Panjang dan Tauge",
            calories = 90.0,
            carbs = 6.11,
            fat = 7.31,
            protein = 2.03,
            ingredients = "50 gram kecambah\n" +
                    "100 gram kacang panjang hijau\n" +
                    "1/4 sdm cabai merah atau rawit\n" +
                    "1 sdm minyak sayur canola\n" +
                    "1 siung bawang putih\n" +
                    "1/2 gelas air\n" +
                    "10 gram bawang merah\n" ,
            cookingSteps = "1. Potong semua sayuran yang dibutuhkan.\n" +
                    "2. Tumis cabai, bawang merah, bawang putih dengan minyak canola.\n" +
                    "3. Masukkan tauge dan kacang pang, beri air sedikit dan diamkan hingga sedikit layu.\n" ,
            recipePictures = "tumis_kacang_panjang_dan_tauge",
            mealType = 2,
            cookTime = "15 menit",
            prepTime = "5 menit",
            portion = 2
        ),
Recipe(
            recipeId = 50,
            name = "Beef Patty",
            calories = 230.0,
            carbs = 6.67,
            fat = 13.69,
            protein = 18.88,
            ingredients = "500 gram daging sapi\n" +
                    "2 sedang telur\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "1 sdt garam\n" +
                    "74 g roti tawar\n" +
                    "6 g lada putih bubuk\n" +
                    "100 gram bawang bombay\n" ,
            cookingSteps = "1. Haluskan daging bersama roti tawar.\n" +
                    "2. Dalam mangkuk besar, campurkan daging giling dengan bumbu-bumbu dan bawang bombay yang sudah dicincang halus.\n" +
                    "3. Masukkan telur dan aduk rata.\n" +
                    "4. Bagi adonan menjadi 8 bagian, bentuk bulat pipih.\n" +
                    "5. Grill di pan anti lengket.\n" ,
            recipePictures = "beef_patty",
            mealType = 1,
            cookTime = "30 menit",
            prepTime = "15 menit",
            portion = 8
        ),
Recipe(
            recipeId = 52,
            name = "Sawi Tumis",
            calories = 53.0,
            carbs = 9.09,
            fat = 0.69,
            protein = 3.75,
            ingredients = "12 gram bawang putih\n" +
                    "5 g kaldu jamur\n" +
                    "189 gram sawi hijau\n" ,
            cookingSteps = "1. Potong sawi dan bawang.\n" +
                    "2. Tumis bawang tanpa minyak, tbahkan sedikit air.\n" +
                    "3. Tambahkan kaldu jamur dan tunggu sampai matang.\n" ,
            recipePictures = "sawi_tumis",
            mealType = 3,
            cookTime = "10 menit",
            prepTime = "5 menit",
            portion = 1
        ),
Recipe(
            recipeId = 54,
            name = "Tumis Labu Siam",
            calories = 61.0,
            carbs = 4.15,
            fat = 5.07,
            protein = 0.79,
            ingredients = "5 gram cabai merah atau rawit\n" +
                    "329 gram labu siam\n" +
                    "5 gram bawang putih\n" +
                    "5 gram daun bawang\n" +
                    "500 ml air\n" +
                    "27 gram bawang merah\n" +
                    "27 ml minyak goreng\n" ,
            cookingSteps = "1. Tumis bawang merah, bawang putih, cabe rawit dan daun bawang dengan minyak goreng.\n" +
                    "2. Masukkan labu siam yang sudah dipotong-potong.\n" +
                    "3. Masukkan air.\n" +
                    "4. Tambahkan bumbu yang disukai (garam dan merica) secukupnya.\n" ,
            recipePictures = "tumis_labu_siam",
            mealType = 1,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 5
        ),
Recipe(
            recipeId = 57,
            name = "Dimsum Ayam Udang",
            calories = 50.0,
            carbs = 4.26,
            fat = 1.89,
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
                    "6 ml kecap asin\n" ,
            cookingSteps = "1. Cuci bersih udang, potong kecil-kecil, sisihkan.\n" +
                    "2. Masukkan semua bahan ke food proccesor kecuali udang dan kulit pangsit.\n" +
                    "3. Blender sampai halus.\n" +
                    "4. Setelah adonan halus tambahkan udang potong kemudian diaduk.\n" +
                    "5. Masukan ke dalam kulit lumpia, kukus 20 menit.\n" ,
            recipePictures = "dimsum_ayam_udang",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "15 menit",
            portion = 27
        ),
Recipe(
            recipeId = 61,
            name = "Dada Ayam Suwir",
            calories = 283.0,
            carbs = 22.34,
            fat = 14.3,
            protein = 18.37,
            ingredients = "55 gram dada ayam\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 sdt gula pasir\n" +
                    "2 kecil bawang merah\n" +
                    "2 g royco ayam\n" +
                    "10 ml minyak goreng\n" ,
            cookingSteps = "1. Rebus dada ayam, setelah matang suwir sesuai selera.\n" +
                    "2. Tumis cabe rawit, bawang merah, bawant putih yg sudah di haluskan sampai wangi dengan minyak.\n" +
                    "3. Masukkan dada ayam yg sudah di suwir lalu aduk rata.\n" +
                    "4. Tambahkan kaldu ayam dan gula.\n" +
                    "5. Siap di hidangkan.\n" ,
            recipePictures = "dada_ayam_suwir",
            mealType = 2,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 62,
            name = "Tumis Jamur Daging Brokoli",
            calories = 79.0,
            carbs = 7.38,
            fat = 2.11,
            protein = 8.39,
            ingredients = "100 gram mushrooms\n" +
                    "1/2 sdt sugar\n" +
                    "1/2 sdt salt\n" +
                    "60 gram broccoli\n" +
                    "20 gram onions\n" +
                    "70 gram beef thin-sliced (cured)\n" +
                    "3 ml minyak goreng\n" +
                    "10 g chili\n" +
                    "50 g saus lada hitam\n" ,
            cookingSteps = "1. Iris tipis jamur dan potong potong brokoli.\n" +
                    "2. Iris tipis bawang bombai dan cabe.\n" +
                    "3. Tumis bawang bombai dengan minyak goreng.\n" +
                    "4. Tambahkan daging, jamur, dan brokoli untuk dimasak lalu cabai.\n" +
                    "5. Tuangkan saus dan garam untuk membumbui.\n" +
                    "6. Masak hingga matang. Sajikan.\n" ,
            recipePictures = "tumis_jamur_daging_brokoli",
            mealType = 1,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 3
        ),
Recipe(
            recipeId = 63,
            name = "Kimchi Jiggae",
            calories = 210.0,
            carbs = 17.03,
            fat = 5.72,
            protein = 26.12,
            ingredients = "50 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "1 besar putih telur\n" +
                    "1 sdm bubuk cabai\n" +
                    "1 siung bawang putih\n" +
                    "15 gram daun bawang\n" +
                    "50 gram kimchi\n" +
                    "50 gram tahu\n" +
                    "50 gram bawang bombay\n" +
                    "50 gram jamur kancing\n" ,
            cookingSteps = "1. Oseng bawang putih dan bawang bombay tanpa minyak sampai layu.\n" +
                    "2. Masukin air kurleb 300ml.\n" +
                    "3. Masukin ayam dan tahu setelah ayam matang masukan kimchi dan jamur...\n" +
                    "4. Setelah semua layu masukan putih telur, bubuk cabai dan daun bawang aduk aduk. Taburi dengan nori dan biji wijen (opsional).\n" ,
            recipePictures = "kimchi_jiggae",
            mealType = 3,
            cookTime = "10 menit",
            prepTime = "5 menit",
            portion = 1
        ),
Recipe(
            recipeId = 64,
            name = "Bakso Kentang Fettuccine",
            calories = 343.0,
            carbs = 60.41,
            fat = 7.65,
            protein = 11.64,
            ingredients = "10 gram cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "50 gram kentang (daging, dengan garam, direbus)\n" +
                    "1 kecil bawang merah\n" +
                    "2 kecil bakso daging sapi\n" +
                    "30 g fettuccine\n" +
                    "45 g bolognese sauce\n" ,
            cookingSteps = "1. Rebus kentang dan fettuccine selama 5-10 menit.\n" +
                    "2. Tumis bakso, bawang putih, bawang merah, cabai yg sudah di potong.\n" +
                    "3. Masukan bolognese sauce dan tumis hingga harum bersama bakso.\n" ,
            recipePictures = "bakso_kentang_fettuccine",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 66,
            name = "Oseng Cabe Tahu Tempe",
            calories = 330.0,
            carbs = 40.29,
            fat = 18.81,
            protein = 14.3,
            ingredients = "200 gram cabai merah atau rawit\n" +
                    "10 buah tahu goreng\n" +
                    "8 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "10 buah tempe goreng\n" ,
            cookingSteps = "1. Goreng tahu dan tempe dengan minyak, sampai setengah matang, kemudian angkat tiriskan.\n" +
                    "2. Tumis bawang merah dengan sisa minyak goreng tadi, masak sampai setengah makan.\n" +
                    "3. Masukkan irisan cabe, masak sampai setengah matang,.\n" +
                    "4. Masukkan gorengan tempe dan tahu, tambahakan garam secukupnya. Siap disajikan.\n" ,
            recipePictures = "oseng_cabe_tahu_tempe",
            mealType = 2,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 5
        ),
Recipe(
            recipeId = 68,
            name = "Teriyaki Beef",
            calories = 366.0,
            carbs = 15.14,
            fat = 20.94,
            protein = 26.71,
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
                    "1 sdm minyak wijen\n" ,
            cookingSteps = "1. Marinasi Daging dengan kecap asin, kecap manis, minyak wijen, saos tiram, garam, gula, bawang putih, lada putih, jahe. Kurang lebih 15 - 30 menit.\n" +
                    "2. Masak Daging tanpa minyak. Sampai matang. Setelah itu beri air sedikit, kemudian jika masukkan bawang Bombay. Masak hingga layu.\n" +
                    "3. Hidangkan.\n" ,
            recipePictures = "teriyaki_beef",
            mealType = 1,
            cookTime = "35 menit",
            prepTime = "20 menit",
            portion = 2
        ),
Recipe(
            recipeId = 69,
            name = "Tumis Sayur dengan Telur",
            calories = 379.0,
            carbs = 38.57,
            fat = 20.92,
            protein = 13.47,
            ingredients = "1 besar telur\n" +
                    "100 gram kecambah\n" +
                    "1/2 sdt garam\n" +
                    "100 gram kubis\n" +
                    "1 mentimun (dengan kulit)\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm kecap manis\n" +
                    "15 ml minyak goreng\n" +
                    "1 sdm saos tiram\n" ,
            cookingSteps = "1. Cuci bersih toge, kol, timun. Potong sesuai selera. Kupas dan cincang 1 siung bawang putih.\n" +
                    "2. Panaskan 1 sdm minyak, masukan telur kemudian diorak arik, masukin bawang putih, tumis sebentar, masukan sayuran.\n" +
                    "3. Beri sedikit air, masukan garam, saus tiram dan kecap manis. Masak sampai matang. Sajikan.\n" ,
            recipePictures = "tumis_sayur_dengan_telur",
            mealType = 1,
            cookTime = "15 menit",
            prepTime = "5 menit",
            portion = 1
        ),
Recipe(
            recipeId = 71,
            name = "Aglio Olio",
            calories = 292.0,
            carbs = 33.46,
            fat = 8.5,
            protein = 19.2,
            ingredients = "1/2 kecil chicken breast (skin not eaten)\n" +
                    "1 medium carrot\n" +
                    "1 mangkok green string beans\n" +
                    "1 sdt, tumbuk oregano\n" +
                    "1 chicken sausage\n" +
                    "2 sdt minced garlic\n" +
                    "11 ml minyak goreng\n" +
                    "60 g spaghetti\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm saos tiram\n" +
                    "2,5 g boncabe\n" +
                    "1,5 g garam\n" ,
            cookingSteps = "1. Rebus spageti sampai al dente.\n" +
                    "2. Tumis bawang putih, masukan dada ayam, sosis, sayuran, lalu saus tiram dan bumbu.\n" +
                    "3. Masukan spageti yg sudah direbus, lada garam, oregano, dan boncabe.\n" +
                    "4. Siap disajikan.\n" ,
            recipePictures = "aglio_olio",
            mealType = 1,
            cookTime = "30 menit",
            prepTime = "15 menit",
            portion = 2
        ),
Recipe(
            recipeId = 72,
            name = "Sawi Tumis Tauge",
            calories = 91.0,
            carbs = 9.38,
            fat = 5.16,
            protein = 4.84,
            ingredients = "200 gram kecambah\n" +
                    "3 sejumput garam\n" +
                    "67 gram sawi\n" +
                    "11 gram bawang putih\n" +
                    "19 gram bawang merah\n" +
                    "2 g msg\n" +
                    "10 ml minyak goreng sawit\n" ,
            cookingSteps = "1. Potong sawi, cuci bersih kecambah sisihkan.\n" +
                    "2. Iris duo bawang merah dan bawang putih, lalu tumis dengan minyak.\n" +
                    "3. Masukkan sawi tumis dan bumbui hingga matang siap disajikan.\n" ,
            recipePictures = "sawi_tumis_tauge",
            mealType = 1,
            cookTime = "10 menit",
            prepTime = "5 menit",
            portion = 2
        ),
Recipe(
            recipeId = 74,
            name = "Bola Ikan Udang Wortel",
            calories = 30.0,
            carbs = 1.12,
            fat = 0.9,
            protein = 4.08,
            ingredients = "100 gram udang\n" +
                    "102 gram telur (utuh)\n" +
                    "77 gram wortel\n" +
                    "1 1/2 sendok kaldu jamur\n" +
                    "5 g lada putih bubuk\n" +
                    "34 gram bawang bombay\n" +
                    "15 ml saus rasa tiram\n" +
                    "300 gram ikan tenggiri\n" ,
            cookingSteps = "1. Kupas udang dan buang tulang di tenggiri.\n" +
                    "2. Campur semua bahan dalam food processor dan bumbui sesuai keinginan.\n" +
                    "3. Buat bola-bola (masing-masing sekitar 25 g).\n" +
                    "4. Masukkan ke dalam kukusan dan masak selama 20 menit.\n" ,
            recipePictures = "bola_ikan_udang_wortel",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "20 menit",
            portion = 26
        ),
Recipe(
            recipeId = 75,
            name = "Rebusan Ayam Saus Tiram",
            calories = 329.0,
            carbs = 21.29,
            fat = 12.76,
            protein = 29.87,
            ingredients = "100 gram dada ayam\n" +
                    "1 sejumput lada hitam\n" +
                    "1 siung bawang putih\n" +
                    "2 gram daun bawang\n" +
                    "1 sejumput garam laut\n" +
                    "1/2 sdm minyak goreng\n" +
                    "1 1/2 sdm kecap manis\n" +
                    "1 sdm saos tiram\n" +
                    "10 gram bawang bombay\n" ,
            cookingSteps = "1. Campurkan saus tiram, kecap asin, merica, dan sedikit air dalam mangkuk.\n" +
                    "2. Potong ayam menjadi kubus dan rendam dalam saus.\n" +
                    "3. Tumis bawang putih, daun bawang, dan bawang bombay dalam wajan dengan minyak. Aduk hingga harum.\n" +
                    "4. Masukkan ayam yang sudah dimarinasi dengan saus dan garam. Rebus sampai daging berwarna cokelat muda.\n" ,
            recipePictures = "rebusan_ayam_saus_tiram",
            mealType = 2,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 79,
            name = "Tumis Udang dan Bakso",
            calories = 164.0,
            carbs = 14.18,
            fat = 3.98,
            protein = 17.68,
            ingredients = "80 gram udang\n" +
                    "118 gram wortel\n" +
                    "120 gram kembang kol\n" +
                    "1 siung bawang putih\n" +
                    "30 gram bakso daging sapi\n" +
                    "30 gram bakso ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "2 sdm saos tiram\n" +
                    "5 gram cabai merah besar\n" ,
            cookingSteps = "1. Cuci udang dan kupas. Potong sayuran.\n" +
                    "2. Tumis bawang putih di wajan yang sudah diolesi sedikit minyak sampai harum lalu tambahkan saus tiram.\n" +
                    "3. Masukkan udang, bakso, dan sayuran.\n" +
                    "4. Bumbui dan didihkan sampai matang.\n" ,
            recipePictures = "tumis_udang_dan_bakso",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 81,
            name = "Tumis Sayuran dengan Ayam",
            calories = 197.0,
            carbs = 114.19,
            fat = 1.54,
            protein = 6.04,
            ingredients = "1 irisan tipis ayam\n" +
                    "100 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "1 kecil bawang merah\n" +
                    "1 bonggol jagung kecil\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm saos tiram\n" +
                    "100 gram sawi hijau\n" +
                    "20 g tepung maizena\n" ,
            cookingSteps = "1. Potong-potong ayam dan sayuran.\n" +
                    "2. Tumis bawang putih dan bawang bombay dalam wajan sampai harum.\n" +
                    "3. Masukkan ayam, aduk sebentar hingga daging berubah warna menjadi putih.\n" +
                    "4. Masukkan sisa sayur, bumbu, dan tepung maizena yang sudah dilarutkan dalam air.\n" +
                    "5. Masak hingga matang lalu sajikan.\n" ,
            recipePictures = "tumis_sayuran_dengan_ayam",
            mealType = 2,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 82,
            name = "Ayam Goreng Bumbu Kuning",
            calories = 486.0,
            carbs = 2.5,
            fat = 26.98,
            protein = 54.14,
            ingredients = "800 gram ayam\n" +
                    "300 ml air\n" +
                    "25 g bumbu racik ayam goreng\n" ,
            cookingSteps = "1. Bilas dan potong ayam.\n" +
                    "2. Masukkan air ke dalam panci, tambahkan bumbu ayam goreng dan didihkan.\n" +
                    "3. Masukkan potongan ayam, lalu masak dengan api kecil hingga empuk dan air menyusut.\n" +
                    "4. Goreng ayam di air fryer tanpa menggunakan minyak hingga matang.\n" ,
            recipePictures = "ayam_goreng_bumbu_kuning",
            mealType = 1,
            cookTime = "1 jam",
            prepTime = "10 menit",
            portion = 4
        ),
Recipe(
            recipeId = 84,
            name = "Ayam Teriyaki",
            calories = 204.0,
            carbs = 26.2,
            fat = 2.78,
            protein = 15.89,
            ingredients = "25 gram bawang bombay\n" +
                    "1 sejumput garam\n" +
                    "50 gram daging dada ayam (ayam pedaging, dipanggang, dimasak)\n" +
                    "100 ml air\n" +
                    "1 sdm kecap manis\n" +
                    "15 g saus sambal\n" +
                    "1 jumput msg\n" +
                    "10 ml saori saus teriyaki\n" ,
            cookingSteps = "1. Dalam wajan panas dengan sedikit air, tumis bawang bombay dan cabai yang sudah dicincang.\n" +
                    "2. Tambahkan air dan potongan ayam ke dalam wajan. Aduk selama beberapa menit.\n" +
                    "3. Tambahkan semua bahan yang tersisa. Aduk rata dan pindahkan ke piring setelah matang.\n" ,
            recipePictures = "ayam_teriyaki",
            mealType = 2,
            cookTime = "10 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 85,
            name = "Sup Daging Selada Air",
            calories = 193.0,
            carbs = 8.91,
            fat = 10.9,
            protein = 17.07,
            ingredients = "200 gram daging sapi\n" +
                    "20 gram cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "15 gram bawang putih\n" +
                    "300 gram selada air\n" +
                    "150 gram jamur tiram\n" +
                    "2 g royco ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "40 gram bawang bombay\n" ,
            cookingSteps = "1. Rebus daging buang airnya, direbus kembali dengan bawang putih, bawang bombay dan cabe, bumbui dengan garam, merica, dan royco ayam.\n" +
                    "2. Masukan jamur, tunggu beberapa saat.\n" +
                    "3. Masukan selada air tunggu sampe selada air matang sebelum disajikan.\n" ,
            recipePictures = "sup_daging_selada_air",
            mealType = 3,
            cookTime = "30 menit",
            prepTime = "10 menit",
            portion = 4
        ),
Recipe(
            recipeId = 86,
            name = "Brokoli Bawang Putih",
            calories = 190.0,
            carbs = 15.3,
            fat = 11.67,
            protein = 8.03,
            ingredients = "1 sdt garam\n" +
                    "100 gram brokoli\n" +
                    "3 siung bawang putih\n" +
                    "1/2 sdt gula pasir\n" +
                    "5 ml olive oil\n" +
                    "5 g kaldu jamur\n" +
                    "30 g sosis\n" +
                    "6 g saus tiram\n" ,
            cookingSteps = "1. Bersihkan brokoli, potong lalu rendam dalam larutan garam 5-10menit.\n" +
                    "2. Cincang bawang putih. Goreng bawang putih menggunakan ollive oil sampe kecoklatan, sisihkan.\n" +
                    "3. Tumis sedikit bawang putih.\n" +
                    "4. Masukan brokoli dan sosis. Tambahkan air kemudian tutup, tunghu sampai brokoli agak layu.\n" +
                    "5. Tambahkan saus tiram, gula pasir, garam, dan kaldu jamur.\n" +
                    "6. Hidangkan brokoli kemudian siramkan bawang putih goreng diatasnya.\n" ,
            recipePictures = "brokoli_bawang_putih",
            mealType = 3,
            cookTime = "10 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 88,
            name = "Ayam Suwir Kemangi",
            calories = 314.0,
            carbs = 15.16,
            fat = 14.41,
            protein = 32.93,
            ingredients = "100 gram dada ayam\n" +
                    "1 utuh sedang tomat\n" +
                    "5 gram cabai merah atau rawit\n" +
                    "3 gram kunyit\n" +
                    "40 gram kemangi\n" +
                    "1 siung bawang putih\n" +
                    "28 g bawang merah\n" +
                    "5 ml olive oil\n" ,
            cookingSteps = "1. Rebus ayam dan suwir.\n" +
                    "2. Tumis bawang merah, bawang putih dan tomat dengan minyak zaitun.\n" +
                    "3. Tambahkan sedikit air, suwiran ayam, kemangi, kunyit, cabai, garam dan merica.\n" +
                    "4. Siap disajikan.\n" ,
            recipePictures = "ayam_suwir_kemangi",
            mealType = 2,
            cookTime = "20 menit",
            prepTime = "15 menit",
            portion = 1
        ),
Recipe(
            recipeId = 89,
            name = "Ayam Suwir Kecap",
            calories = 492.0,
            carbs = 22.89,
            fat = 23.02,
            protein = 45.52,
            ingredients = "150 gram dada ayam\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 sdm minyak goreng\n" +
                    "1 sdm kecap manis\n" +
                    "10 g kaldu jamur\n" +
                    "30 gram bawang bombay\n" ,
            cookingSteps = "1. Rebus/kukus ayam sampai matang.\n" +
                    "2. Jika sudah matang, angkat lalu robek-robek daging ayam menjadi ukuran kecil.\n" +
                    "3. Cincang bawang bombay. Haluskan cabe rawit.\n" +
                    "4. Panasan minyak diatas wajan. Tumis bawang bombay dan cabe rawit. Tambahkan kecap, kaldu jamur dan garam dan merica.\n" +
                    "5. Masukkan ayam yang telah disuwir.\n" +
                    "6. Aduk hingga semua bumbu tercampur rata.\n" +
                    "7. Sajikan.\n" ,
            recipePictures = "ayam_suwir_kecap",
            mealType = 3,
            cookTime = "30 menit",
            prepTime = "10 menit",
            portion = 1
        ),
Recipe(
            recipeId = 90,
            name = "Suwir Ayam Creamy",
            calories = 319.0,
            carbs = 9.5,
            fat = 9.89,
            protein = 44.93,
            ingredients = "280 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "20 gram kaldu jamur\n" +
                    "10 gram keju cheddar\n" +
                    "10 gram bawang putih\n" +
                    "10 gram tomat merah\n" +
                    "1 sejumput garam laut\n" +
                    "200 ml non fat milk\n" +
                    "6 g lada putih bubuk\n" +
                    "9 ml minyak goreng tropical\n" ,
            cookingSteps = "1. Rebus dada ayam kemudian suwir.\n" +
                    "2. Panaskan minyak. Tumis bawang putih hingga kecoklatan.\n" +
                    "3. Masukan tomat cincang, aduk sebentar.\n" +
                    "4. Masukan dada ayam suwir, aduk rata.\n" +
                    "5. Tuangkan susu non fat.\n" +
                    "6. Bumbui dengan lada, garam, dan kaldu jamur.\n" +
                    "7. Masukan keju, aduk kembali.\n" ,
            recipePictures = "suwir_ayam_creamy",
            mealType = 3,
            cookTime = "30 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 91,
            name = "Sup Daging Sapi dengan Kacang Panjang",
            calories = 246.0,
            carbs = 19.89,
            fat = 9.66,
            protein = 20.84,
            ingredients = "100 gram tomat\n" +
                    "200 gram kacang panjang hijau (dari segar)\n" +
                    "3 siung bawang putih\n" +
                    "1 sdt jahe\n" +
                    "1 mangkok daun bawang\n" +
                    "100 gram iga sapi (potongan kecil, diiris hingga 0.3 cm lemak)\n" +
                    "250 gram daging sapi (95% tanpa lemak / 5% lemak)\n" +
                    "4 sedang bawang merah\n" ,
            cookingSteps = "1. Cuci bersih daging sapi dan iga sapi, potong kecil-kecil daging dan masak dengan cara 5-30-7 (rebus 5 menit, matikan api, diamkan di atas kompor selama 30 menit, dan lanjutkan proses memasak dengan merebusnya selama 7 menit lagi).\n" +
                    "2. Haluskan bawang merah, bawang putih dan jahe.\n" +
                    "3. Iris kacang panjang, tomat dan daun bawang.\n" +
                    "4. Tumis bumbu halus dengan minyak hingga harum, tambahkan air lalu daging sapi dan iga. Biarkan mendidih.\n" +
                    "5. Setelah mendidih, masukkan kacang panjang dan tomat, bumbui dengan garam dan kaldu jamur secukupnya.\n" +
                    "6. Jika sudah matang, matikan api dan masukkan daun bawang yang sudah dipotong-potong.\n" ,
            recipePictures = "sup_daging_sapi_dengan_kacang_panjang",
            mealType = 1,
            cookTime = "1 jam",
            prepTime = "20 menit",
            portion = 4
        ),
Recipe(
            recipeId = 93,
            name = "Tumis Brokoli Jamur",
            calories = 282.0,
            carbs = 8.54,
            fat = 17.9,
            protein = 22.3,
            ingredients = "150 gram daging sapi giling\n" +
                    "70 gram brokoli\n" +
                    "13 gram bawang putih\n" +
                    "3 g margarin\n" +
                    "113 gram jamur shimeji\n" +
                    "5 g saos tiram\n" +
                    "5 ml minyak wijen\n" ,
            cookingSteps = "1. Tumis bawang putih cincang pakai minyak wijen.\n" +
                    "2. Masukan udang hingga berubah warna.\n" +
                    "3. Tambahkan air secukupnya untuk membuat sup.\n" +
                    "4. Masukkan tofu hingga semua matang.\n" +
                    "5. Masukan garam saus tiram kaldu jamur.\n" +
                    "6. Masukan Kocokan telur aduk hingga berserabut. Tambahkan daun bawang (opsional).\n" ,
            recipePictures = "tumis_brokoli_jamur",
            mealType = 3,
            cookTime = "15 menit",
            prepTime = "5 menit",
            portion = 2
        ),
Recipe(
            recipeId = 94,
            name = "Sop",
            calories = 353.0,
            carbs = 26.47,
            fat = 14.89,
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
                    "2 1/2 ons, tanpa tulang dada ayam rebus\n" ,
            cookingSteps = "1. Tumis bawang merah, bawang putih, cabai (optional kalau ingin pedas) menggunakan minyak hingga wangi.\n" +
                    "2. Masukkan air kedalam tumisan tadi, biarkan sampai air mendidih.\n" +
                    "3. Masukan ayam, udang, dan sayur-sayuran kedalam air.\n" +
                    "4. Tambahkan garam, gula, dan kaldu, racik sayur sop sesuai selera lalu tunggu hingga matang.\n" ,
            recipePictures = "sop",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 98,
            name = "Chicken Ramen Shirataki",
            calories = 426.0,
            carbs = 60.77,
            fat = 4.95,
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
                    "1 ml minyak goreng\n" ,
            cookingSteps = "1. Iris ayam hingga tipis tipis.\n" +
                    "2. Potong-potong tomat dan wortel sesuai keinginan.\n" +
                    "3. Cincang bawang merah dan bawang putih.\n" +
                    "4. Panaskan minyak kemudian tumis bawang merah, bawang putih, dan tomat.\n" +
                    "5. Masukkan air dan tunggu hingga hampir mendidih.\n" +
                    "6. Masukkan ayam dan royco perlahan.\n" +
                    "7. Ketika ayam sudah matang, keluarkan ayam dari rebusan.\n" +
                    "8. Masukkan sayuran dan mie shirataki. Tunggu hingga matang!\n" ,
            recipePictures = "chicken_ramen_shirataki",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 106,
            name = "Pokcoy Ayam Saus Tiram",
            calories = 327.0,
            carbs = 25.38,
            fat = 2.66,
            protein = 47.66,
            ingredients = "1 sejumput lada hitam\n" +
                    "200 gram daging dada ayam (ayam pedaging)\n" +
                    "1 siung bawang putih\n" +
                    "1 sdt jahe\n" +
                    "100 ml air\n" +
                    "150 gram pakcoy\n" +
                    "15 ml kecap manis light\n" +
                    "10 g tepung pati jagung\n" +
                    "15 g saos tiram\n" +
                    "1 bawang bombay\n" ,
            cookingSteps = "1. Larutkan maizena dengan sedikit air.\n" +
                    "2. Didihkan air dan rebus pokcoy selama 2 menit. Hapus dari panas dan tiriskan.\n" +
                    "3. Dalam wajan panas, tumis bawang putih, jahe, dan bawang bombay hingga harum.\n" +
                    "4. Masukkan ayam yang sudah dipotong-potong ke dalam wajan dan masak hingga berubah warna.\n" +
                    "5. Tambahkan saus tiram, kecap asin, merica, air, dan tepung maizena yang sudah dilarutkan.\n" +
                    "6. Aduk, biarkan bumbu meresap dan angkat dari api.\n" +
                    "7. Tata pokcoy di piring dan tuangkan di atas ayam yang sudah dimasak.\n" ,
            recipePictures = "pokcoy_ayam_saus_tiram",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 107,
            name = "Chicken Deopbap",
            calories = 428.0,
            carbs = 26.96,
            fat = 25.95,
            protein = 26.48,
            ingredients = "1 kecil chicken thigh\n" +
                    "1 sedang egg\n" +
                    "1 sdm soy sauce\n" +
                    "1 kecil young green onions\n" +
                    "1 1/2 cups cooked young green onion\n" ,
            cookingSteps = "1. Tumis ayam tanpa minyak sampai kecoklatan.\n" +
                    "2. Masukan bawang bombay, tumis sampai layu dgn ayam td, masukan air secukupnya tunggu mendidih.\n" +
                    "3. Masukan telur yg sudah dikocok lepas, lalu masukan kecap asin dan daun bawang. Bumbui dengan garam dan merica sesuai selera. Tutup wajan sampai telur matang.\n" ,
            recipePictures = "chicken_deopbap",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 108,
            name = "Kwetiau Ayam",
            calories = 322.0,
            carbs = 48.97,
            fat = 9.43,
            protein = 9.9,
            ingredients = "15 gram dada ayam\n" +
                    "2 gram bawang bombay dimasak\n" +
                    "1/2 sdt garam\n" +
                    "2 ml minyak goreng\n" +
                    "100 gram kwetiau\n" +
                    "1 sendok saus sambal\n" +
                    "2 sdm kecap manis\n" ,
            cookingSteps = "1. Panaskan minyak dalam wajan dan tumis bawang bombay.\n" +
                    "2. Masukkan ayam yg sudah di potong, lalu tumis sebentar.\n" +
                    "3. Tambahkan kwetiau dan bumbunya.\n" +
                    "4. Campur dengan baik. Angkat dari api setelah selesai. Tambahkan lauk yang diinginkan.\n" ,
            recipePictures = "kwetiau_ayam",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 111,
            name = "Semur Tahu",
            calories = 212.0,
            carbs = 32.8,
            fat = 7.22,
            protein = 5.84,
            ingredients = "2 siung bawang putih\n" +
                    "1 utuh sedang tomat merah\n" +
                    "1 gelas air\n" +
                    "3 kecil bawang merah\n" +
                    "100 gram tahu\n" +
                    "2 sdm kecap manis\n" +
                    "2 sendok saus sambal\n" +
                    "1 takaran royco ayam\n" +
                    "1 sendok makan minyak goreng sawit\n" +
                    "20 gram cabai merah besar\n" ,
            cookingSteps = "1. Dalam wajan panas dengan minyak, tumis bawang putih, bawang merah, dan cabai merah.\n" +
                    "2. Tambahkan tomat cincang, air dan kecap.\n" +
                    "3. Terakhir masukkan tahu dan saus sambal. Campur dengan baik.\n" +
                    "4. Masak hingga kental dan tambahkan royco. Aduk dan pindahkan ke dalam mangkuk.\n" ,
            recipePictures = "semur_tahu",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "15 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 112,
            name = "Tumis Tahu Telur Wortel",
            calories = 252.0,
            carbs = 39.01,
            fat = 5.75,
            protein = 16.88,
            ingredients = "100 gram kacang panjang hijau\n" +
                    "1 besar putih telur\n" +
                    "100 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "1 utuh sedang tomat merah\n" +
                    "2 kecil bawang merah\n" +
                    "100 gram tahu\n" ,
            cookingSteps = "1. Tumis bawang putih bawang merah dan bawang putih yang sudah dicincang halus.\n" +
                    "2. Masukkan tomat yang sudah di iris.\n" +
                    "3. Tumis sampai layu dan wangi tambahkan, garam secukupnya.\n" +
                    "4. Kemuadian masukkan air sedikit lalu masukkan tahu dan putuh telur.\n" +
                    "5. Setelah itu masukkan kacang dan wortel tumis sampai matang.\n" ,
            recipePictures = "tumis_tahu_telur_wortel",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 114,
            name = "Tahu Goreng Telur",
            calories = 188.0,
            carbs = 6.92,
            fat = 16.69,
            protein = 4.51,
            ingredients = "2 sedang telur\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "5 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "4 sedang bawang merah\n" +
                    "250 gram tahu\n" +
                    "1 takaran royco ayam\n" +
                    "125 ml minyak goreng 2x penyaringan\n" ,
            cookingSteps = "1. Haluskan bawang merah, bawang putih, dan cabai. Campur mereka dengan baik.\n" +
                    "2. Tahu dihaluskan lalu masukkan semua bahan kecuali minyak.\n" +
                    "3. Panaskan minyak dalam wajan dan goreng sampai berwarna cokelat keemasan.\n" ,
            recipePictures = "tahu_goreng_telur",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 8
        ),
Recipe(
            recipeId = 116,
            name = "Cumi Lada Hitam",
            calories = 107.0,
            carbs = 6.46,
            fat = 3.36,
            protein = 12.17,
            ingredients = "300 gram cumi-cumi\n" +
                    "50 ml air\n" +
                    "2 tomat ceri\n" +
                    "1 sendok makan minyak goreng\n" +
                    "100 gram bawang bombay\n" +
                    "1 sendok saus lada hitam\n" +
                    "50 gram cabai merah besar\n" ,
            cookingSteps = "1. Belah bawang bombay menjadi dua dan Iris memanjang setengahnya dan tumis dengan minyak sampai harum.\n" +
                    "2. Masukan cumi, cabai, saos lada hitam dan air.\n" +
                    "3. Kemudian masukan tomat tumis semuanya hingga masak.\n" ,
            recipePictures = "cumi_lada_hitam",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 118,
            name = "Tumis Tahu dan Tuna",
            calories = 313.0,
            carbs = 23.0,
            fat = 18.11,
            protein = 17.9,
            ingredients = "10 gram cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "2 kecil bawang merah\n" +
                    "57 1/2 gram tahu\n" +
                    "43,8 gram ikan tongkol\n" +
                    "1 sdm minyak kanola\n" +
                    "1 takaran royco ayam\n" ,
            cookingSteps = "1. Iris semua cabai, bawang merah dan bawa putih. Lalu tumis dengan minyak kanola.\n" +
                    "2. Potong dadu tahu dan suwir ikan tongkol. Lalu, masukan ke bumbu yang sudah di tumis.\n" +
                    "3. Tumis dan masukan garam serta royco secukupnya. Lalu hidangkan.\n" ,
            recipePictures = "tumis_tahu_dan_tuna",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 119,
            name = "Bulgogi",
            calories = 704.0,
            carbs = 65.6,
            fat = 33.82,
            protein = 35.52,
            ingredients = "250 gram daging sapi\n" +
                    "1 sejumput lada hitam\n" +
                    "3 sdm saus tiram\n" +
                    "10 sdm saus tomat\n" +
                    "70 ml kecap manis\n" +
                    "3 g lada putih bubuk\n" +
                    "20 ml minyak goreng\n" +
                    "100 gram bawang bombay\n" +
                    "15 ml kecap asin\n" ,
            cookingSteps = "1. Iris tipis daging sapi dan bawang bombay. Masukkan ke dalam mangkuk dan tambahkan semua bumbu.\n" +
                    "2. Marinasi selama 30 menit atau semalaman.\n" +
                    "3. Dalam wajan yang sudah dipanaskan sebelumnya, tumis daging sapi yang sudah diasinkan, sering diaduk atau dibalik sesering yang Anda suka sampai matang.\n" +
                    "4. Sajikan dengan telur dan beberapa sayuran.\n" ,
            recipePictures = "bulgogi",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "40 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 124,
            name = "Spaghetti Gandum dengan Saus Tomat",
            calories = 252.0,
            carbs = 34.01,
            fat = 7.95,
            protein = 12.42,
            ingredients = "1 besar telur\n" +
                    "50 gram spageti gandum\n" +
                    "100 gram saus spageti tanpa daging\n" +
                    "1 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "1 kecil bawang merah\n" +
                    "20 ml susu uht low fat\n" ,
            cookingSteps = "1. Rebus air hingga mendidih beri sedikit garam. Masak spaghetti gandum dan tiriskan dalam saringan.\n" +
                    "2. Tumis bawang bombay di atas granite teflon, tambahkan saus spageti tomat dan aduk rata.\n" +
                    "3. Tambahkan spageti dan aduk. Tambahkan air sedikit demi sedikit.\n" +
                    "4. Tambahkan telur dan susu rendah lemak dan aduk sampai sedikit mengental.\n" +
                    "5. Pindahkan ke piring dan sajikan dengan mentimun, opsional.\n" ,
            recipePictures = "spaghetti_gandum_dengan_saus_tomat",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 128,
            name = "Cumi Hitam",
            calories = 278.0,
            carbs = 16.89,
            fat = 12.43,
            protein = 25.76,
            ingredients = "457 gram cumi-cumi\n" +
                    "21 gram cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "37 gram bawang putih\n" +
                    "47 gram bawang merah\n" +
                    "1 sachet sweetener classic\n" +
                    "3 sdm saos tiram\n" +
                    "30 ml minyak goreng\n" +
                    "51 gram cabai merah besar\n" ,
            cookingSteps = "1. Potong potong cumi sesuai selera, iris bawang merah, bawang putih dan cabai.\n" +
                    "2. Panaskan minyak lalu tumis bawang hingga layu, masukkan cabai beri garam, gula dan saus tiram.\n" +
                    "3. Masukkan cumi aduk aduk hingga rata dan beri air.\n" +
                    "4. Masak selama 5 menit lalu hidangkan.\n" ,
            recipePictures = "cumi_hitam",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 3
        ),
Recipe(
            recipeId = 130,
            name = "Pakcoy dengan Bawang Putih",
            calories = 193.0,
            carbs = 14.22,
            fat = 14.68,
            protein = 1.72,
            ingredients = "1 sdt garam\n" +
                    "1 sdt minyak wijen\n" +
                    "4 siung bawang putih\n" +
                    "1 sdt gula pasir\n" +
                    "1 sdm minyak goreng\n" +
                    "150 gram pakcoy\n" +
                    "3 g lada putih bubuk\n" +
                    "5.8 g saos tiram\n" ,
            cookingSteps = "1. Tumis bawang putih dengan minyak goreng.\n" +
                    "2. Tambahkan saus tiram, garam, merica. Rebus pakcoy.\n" +
                    "3. Tiriskan pakcoy, siram dengan kuah.\n" ,
            recipePictures = "pakcoy_dengan_bawang_putih",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 134,
            name = "Chicken Teriyaki",
            calories = 304.0,
            carbs = 32.81,
            fat = 3.69,
            protein = 31.57,
            ingredients = "86 gram chicken breast (skin not eaten)\n" +
                    "1 cup chopped broccoli\n" +
                    "1 siung garlic\n" +
                    "1 sedang onions\n" +
                    "1 kecil scallions or spring onions\n" +
                    "50 gram enoki mushrooms\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdt boncabe level 15\n" +
                    "22 ml saus teriyaki\n" ,
            cookingSteps = "1. Panggang ayam sampai setengah matang, masukkan bawang merah, bawang putih, tumis.\n" +
                    "2. Masukkan brocoli dan teriyaki saus, boncabe, lada dan aduk sampai layu.\n" +
                    "3. Masukkan jamur enoki dan scallions, masak.\n" ,
            recipePictures = "chicken_teriyaki",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 137,
            name = "Bihun Jamur",
            calories = 433.0,
            carbs = 55.15,
            fat = 12.2,
            protein = 25.45,
            ingredients = "1 besar egg\n" +
                    "50 gram chives\n" +
                    "1 sdm, cincang scallions or spring onions\n" +
                    "25 g sosis ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm canola oil\n" ,
            cookingSteps = "1. Tumis bawang putih.\n" +
                    "2. Masukkan sosis, jamur. Angkat.\n" +
                    "3. Orek telor, angkat.\n" +
                    "4. Tumis bayam, angkat.\n" +
                    "5. Rebus bihun, tiriskan.\n" +
                    "6. Tumis bihun, masukkan kecap asin, kecap manis, dan kaldu jamur Masukkan sosis, jamur, telor, bayam.\n" +
                    "7. Masukkan sosis, jamur, telor, bayam.\n" +
                    "8. Bumbui dengan garam dan merica sesuai selera.\n" ,
            recipePictures = "bihun_jamur",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 142,
            name = "Tempe Teriyaki",
            calories = 484.0,
            carbs = 51.74,
            fat = 19.87,
            protein = 18.94,
            ingredients = "1 siung bawang putih\n" +
                    "1/2 iris daun bawang\n" +
                    "10 gram lada pisang\n" +
                    "100 gram tempe\n" +
                    "1 sendok saus sambal\n" +
                    "1 sendok makan minyak goreng\n" +
                    "100 ml saus teriyaki\n" ,
            cookingSteps = "1. Potong tempe lalu panggang tempe di api kecil tanpa minyak.\n" +
                    "2. Lalu tumis bawang putih dengan minyak, masukan saos teriyaki dan saos sambal makan dan masukkan sejumput lad.\n" +
                    "3. Tambahkan air secukupnya lalu masukkan tempe yg sudah dipanggamg tadi, diamkan sampai bumbu meresap dengan api kecil.\n" +
                    "4. Jika bumbu sudah meresap dan mengering taruh tempe di atas piring dan taburi daun bawang. Tempe siap di sajikan.\n" ,
            recipePictures = "tempe_teriyaki",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 143,
            name = "Tumis Ayam Tahu Telur",
            calories = 461.0,
            carbs = 4.41,
            fat = 27.12,
            protein = 47.59,
            ingredients = "125 gram dada ayam\n" +
                    "1 besar telur\n" +
                    "2 siung bawang putih\n" +
                    "1 sejumput garam laut\n" +
                    "1 sdm minyak goreng\n" +
                    "50 gram tahu\n" +
                    "50 ml aqua\n" +
                    "1 sdm saos tiram\n" ,
            cookingSteps = "1. Potong-potong dadu ayam dan tahu.\n" +
                    "2. Geprek bawang putih, rajang.\n" +
                    "3. Tumis bawang putih dengan minyak goreng sampai harum.\n" +
                    "4. Masukkan ayam, tahu dan air, masak sampai setengah matang, masukkan saus tiram.\n" +
                    "5. Tumis ayam dan tahu sampai matang, masukkan telur, tumis sampai telur setengah matang, tes rasa, kalau kurang asin bisa tambah garam sedikit, tumis sampai matang, sajikan.\n" ,
            recipePictures = "tumis_ayam_tahu_telur",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 144,
            name = "Oseng Tahu Buncis Telur",
            calories = 77.0,
            carbs = 4.68,
            fat = 4.9,
            protein = 4.89,
            ingredients = "1 sedang telur\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "200 gram buncis\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm minyak goreng\n" +
                    "181 gram tahu\n" +
                    "4 g royco ayam\n" +
                    "3 ml saori saus teriyaki\n" ,
            cookingSteps = "1. Panaskan minyak tumis bawang putih.\n" +
                    "2. Masukan ayam dan air.\n" +
                    "3. Tambahkan royco, saus teriyaki, dan cabai dan tunggu hingga matang.\n" ,
            recipePictures = "oseng_tahu_buncis_telur",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 5
        ),
Recipe(
            recipeId = 146,
            name = "Capcay Toge",
            calories = 200.0,
            carbs = 23.42,
            fat = 5.51,
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
                    "10 ml minyak goreng\n" ,
            cookingSteps = "1. Tumis bawang merah yang sudah diiris-iris dengan minyak panas.\n" +
                    "2. Lalu masukkan wortel,toge, jagung, dan kacang panjang. Tambahkan air sekitar 500 ml.\n" +
                    "3. Lalu tambahkan bumbu-bumbu penyebab seperti garam, gula dan royco (sesuai selera).\n" +
                    "4. Lalu tunggu air menyusut menjadi setengah.\n" +
                    "5. Setelah itu siap dihidangkan.\n" ,
            recipePictures = "capcay_toge",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 148,
            name = "Spaghetti",
            calories = 693.0,
            carbs = 71.94,
            fat = 26.37,
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
                    "30 gram bawang bombay\n" ,
            cookingSteps = "1. Siapkan bahan bawang puith,bombai dan cabe rawit. Cuci bersih lalu di chop.\n" +
                    "2. Rebus spagheti tambahkan sejumput garam dan sedikit minyak. Rebus spagheti hingga aldente kira-kira 8 menit.\n" +
                    "3. Bersihkan udang hingga bersih dan tdk amis lagi.\n" +
                    "4. Iris wortel berbentuk korek api dan bersihkan jamur.\n" +
                    "5. Tumis semua bahan bawang putih, bombai dan rawit lalu masukkan udang, tumis hingga setengah matang lalu masukkan wortel dan setelah itu jamur.\n" +
                    "6. Tambahkan air rebusan spagheti lalu di tumis dan masukkan spagheti tadi.\n" +
                    "7. Masak sebentar lalu tambahkan keju yang sudah diparut.\n" +
                    "8. Siap disajikan.\n" ,
            recipePictures = "spaghetti",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 150,
            name = "Tempe Tahu Orek",
            calories = 5.0,
            carbs = 0.39,
            fat = 0.29,
            protein = 0.18,
            ingredients = "16 gram bawang putih\n" +
                    "132 gram tempe\n" +
                    "166 gram tahu goreng\n" +
                    "25 gram bawang merah\n" +
                    "45 ml minyak goreng\n" +
                    "90 ml kecap manis\n" +
                    "30 g saus sambal\n" ,
            cookingSteps = "1. Potong potong tempe, dan tahu goreng.\n" +
                    "2. Iris bawang merah dan bawang putih.\n" +
                    "3. Panaskan minyak kemudian tumis bawang merah dan bawang putih yang sudah di iris tipis.\n" +
                    "4. Setelah bawang merah dan bawang putih setengah matang masukkan tempe kemudian goreng hingga tempe setengah matang.\n" +
                    "5. Masukkan tahu, kemudian aduk dan masukkan kecap dan sambal masak hingga matang.\n" ,
            recipePictures = "tempe_tahu_orek",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 305
        ),
Recipe(
            recipeId = 151,
            name = "Tumis Sawi Tahu",
            calories = 450.0,
            carbs = 13.87,
            fat = 38.64,
            protein = 14.88,
            ingredients = "200 gram greens\n" +
                    "4 sdm vegetable oil\n" +
                    "2 siung garlic\n" +
                    "1 sedang onions\n" +
                    "250 g tahu kuning\n" ,
            cookingSteps = "1. Goreng tahu menang minyak, sisihkan.\n" +
                    "2. Tumis bawang bombay dan bawang putih sampai harum, tambahkan garam dan merica.\n" +
                    "3. Masukkan sawi hijau yg sudah dipotong, aduk sampai sawi layu.\n" +
                    "4. Masukan tahu goreng yang sudah dipotong.\n" ,
            recipePictures = "tumis_sawi_tahu",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 156,
            name = "Udang Tumis Brokoli",
            calories = 334.0,
            carbs = 8.87,
            fat = 5.72,
            protein = 58.79,
            ingredients = "250 sedang udang\n" +
                    "3 sdt bubuk cabai\n" +
                    "1 sdm bawang putih bubuk\n" +
                    "1 sdt garam\n" +
                    "150 gram brokoli\n" +
                    "10 g bawang merah goreng\n" +
                    "2 g royco ayam\n" +
                    "1 sdm kecap manis\n" +
                    "10 gram bawang bombay\n" ,
            cookingSteps = "1. Rebus udang sampai setengah matang dgn air.\n" +
                    "2. Masukkan brokoli dan bumbu.\n" +
                    "3. Rebus sehingga cukup air.\n" ,
            recipePictures = "udang_tumis_brokoli",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "20 Menit",
            portion = 6
        ),
Recipe(
            recipeId = 157,
            name = "Sup Tahu Wortel",
            calories = 189.0,
            carbs = 17.51,
            fat = 9.43,
            protein = 14.65,
            ingredients = "2 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "50 gram wortel\n" +
                    "30 gram kembang kol\n" +
                    "2 siung bawang putih\n" +
                    "150 gram tahu\n" ,
            cookingSteps = "1. Potong kembang kol, wortel dan tahu sesuai kreasi.\n" +
                    "2. Potong bawang putih dan cabai.\n" +
                    "3. Masak menggunakan api sedang selama 10 menit.\n" ,
            recipePictures = "sup_tahu_wortel",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 164,
            name = "Bihun Rebus",
            calories = 186.0,
            carbs = 31.14,
            fat = 1.77,
            protein = 1.38,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "1 porsi bawang merah\n" +
                    "2,75 ml minyak jagung\n" +
                    "60 g bihun jagung\n" +
                    "2 g royco ayam\n" +
                    "3 g lada putih bubuk\n" +
                    "40 gram sawi hijau\n" ,
            cookingSteps = "1. Rebus bihun setengah matang dengan sedikit minyak.\n" +
                    "2. Cuci dan potong bawang merah, bawang putih, cabai rawit, sawi hijau, dan seledri.\n" +
                    "3. Oseng bawang merah, bawang putih, dan cabai rawit dengan sedikit minyak cco.\n" +
                    "4. Tambahkan air secukupnya.\n" +
                    "5. Bila sudah mendidih masukkan sawi hijau, dan seledri. Setelah 1 menit masukkan bihun yang sudah setengah matang.\n" +
                    "6. Masukkan bumbu : garam, lada, dan royco secukupnya. Hidangkan.\n" ,
            recipePictures = "bihun_rebus",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 166,
            name = "Stim Ikan Ala Chinese",
            calories = 134.0,
            carbs = 8.14,
            fat = 1.27,
            protein = 23.49,
            ingredients = "120 gram ikan\n" +
                    "1/4 hot chili pepper\n" +
                    "1 siung bawang putih\n" +
                    "1/4 mangkok, dicincang daun bawang (loncang)\n" +
                    "0,65 g stevia sweetener\n" +
                    "23 g saus tiram\n" +
                    "15 ml kecap asin\n" ,
            cookingSteps = "1. Masak saos ala chinese dengan cara pertama oseng bawang putih dan jahe.\n" +
                    "2. Tambahkan air secukupnya.\n" +
                    "3. Tambahkan kecap asin\n" +
                    "4. Tambahkan saos tiram.\n" +
                    "5. Tunggu hingga mendidih.\n" +
                    "6. Jika ikan sudah masak siram dengan saos ala chinese.\n" +
                    "7. Tunggu hingga mendidih.\n" +
                    "8. Stim ikan senangin dengan daun bawang, cabai merah dan cabai hijau di atasnya.\n" ,
            recipePictures = "stim_ikan_ala_chinese",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "3 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 169,
            name = "Pan Sarden Pizza",
            calories = 1244.0,
            carbs = 159.81,
            fat = 44.59,
            protein = 54.05,
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
                    "125 g tepung segitiga biru\n" ,
            cookingSteps = "1. Aduk air hangat, ragi, dan gula pasir hingga rata lalu diamkan hingga berbuih (2-3 menit).\n" +
                    "2. Masukkan garam, madu, dan 1.5 sdm minyak dan aduk rata.\n" +
                    "3. Tuang adukan ragi ke 250 gram tepung terigu, aduk hingga semua meyatu.\n" +
                    "4. Alasi permukaan meja dengan minyak. Uleni adonan hingga, tidak perlu sampai kalis.\n" +
                    "5. Pipihkan adonan dengan rolling pin seukuran pan yang digunakan.\n" +
                    "6. Untuk topping pizza, tumis sosis dan smoked beef yang sudah dipotong sesuai selera. Kemudian sisihkan.\n" +
                    "7. Tumis bawang putih dan bawang bombai yang sudah diiris hingga wangi. Masukkan sarden, irisan cabai rawit, saus sambal dan saus tomat. Masak hingga mengental.\n" +
                    "8. Panaskan teflon baru di api sedang, olesi dengan minyak lalu panggang satu sisi pizza hingga matang (agak kecoklatan di beberapa bagian).\n" ,
            recipePictures = "pan_sarden_pizza",
            mealType = 1,
            cookTime = "8 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 170,
            name = "Bibimbap",
            calories = 438.0,
            carbs = 67.95,
            fat = 11.72,
            protein = 15.39,
            ingredients = "84 gram telur dadar atau telur orak-arik\n" +
                    "128 gram nasi merah\n" +
                    "10 gram kubis cina (bok-choy, pak-choi)\n" +
                    "53 gram wortel\n" +
                    "2 siung bawang putih\n" +
                    "59 gram bawang merah\n" +
                    "40 g saus topokki hot spicy\n" ,
            cookingSteps = "1. Rebus kubis, wortel, bw merah, bw putih selama 15 menit.\n" +
                    "2. Tempatkan beras merah kukus dalam mangkuk.\n" +
                    "3. Susun bahan yg direbus tdi di atas nasi merah.\n" +
                    "4. Orak arik telur ,lalu letakan di atas.\n" +
                    "5. Tambahkan saus gocujhang atau saus tteokbokki.\n" +
                    "6. Campur semua makanan.\n" +
                    "7. Siap disajikan.\n" ,
            recipePictures = "bibimbap",
            mealType = 2,
            cookTime = "45 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 171,
            name = "Sesame Toowoomba Pasta",
            calories = 588.0,
            carbs = 57.91,
            fat = 30.61,
            protein = 11.83,
            ingredients = "1 siung bawang putih dimasak\n" +
                    "20 gram bawang bombay dimasak\n" +
                    "60 g shin ramyun\n" +
                    "200 ml susu uht full cream (250ml)\n" +
                    "3 sdm roasted sesame dressing\n" +
                    "10 g smoked beef\n" ,
            cookingSteps = "1. Rebus Shin Ramyun atau mi instan lainnya hingga setengah matang.\n" +
                    "2. Tumis bawang putih yang sudah dicincang halus hingga harum.\n" +
                    "3. Masukkan daging lembu salai (secukup rasa) dan tumis dengan bawang.\n" +
                    "4. Masukkan 200ml susu UHT ke tumisan.\n" +
                    "5. Jika susu mulai mendidih masukan bumbu mi instan dan 3 sdm sesame dressing.\n" +
                    "6. Masukan mie dan tunggu hingga susu agak meresap.\n" +
                    "7. Jika susu mulai mengental, matikan api dan Sesame Toowoomba Pasta sudah siap disajikan.\n" ,
            recipePictures = "sesame_toowoomba_pasta",
            mealType = 1,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 172,
            name = "Ayam Saos Mentega",
            calories = 597.0,
            carbs = 71.63,
            fat = 11.7,
            protein = 62.51,
            ingredients = "200 gram daging dada ayam (ayam pedaging, dipanggang, dimasak)\n" +
                    "2 sdm madu\n" +
                    "30 ml madu\n" +
                    "5 ml minyak jagung\n" +
                    "25 g saus tiram\n" ,
            cookingSteps = "1. Siapkan 200 gr ayam cuci bersih , potong sesuai selera.\n" +
                    "2. Marinasi dengan 2 bawang puth halus , penyedam , lada , garam , saus tiram 2 sdm , madu 3 sdm , tepung maizena 1 sdm.\n" +
                    "3. Simpan dalam kulkas selama minimal 15 menit.\n" +
                    "4. Siapkan bumbu tumis nya , bawang bombay , minyak tropicana 1sdt ,.\n" +
                    "5. Tumis bumbu tumis lalu masukan bumbu marinasi.\n" ,
            recipePictures = "ayam_saos_mentega",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "30 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 175,
            name = "Mapo Tofu",
            calories = 261.0,
            carbs = 12.61,
            fat = 16.34,
            protein = 18.16,
            ingredients = "1 sdm soy sauce\n" +
                    "2 tbsps olive oil\n" +
                    "4 siung garlic\n" +
                    "1/2 sedang onions\n" +
                    "200 gram tempeh (cooked)\n" +
                    "100 g jamur enoki\n" +
                    "65 g bakso daging sapi\n" +
                    "2 sdm gochujang\n" +
                    "200 g tahu original\n" ,
            cookingSteps = "1. Tumis bawang putih dan bawang bombay.\n" +
                    "2. Masukan tempe dan bakso.\n" +
                    "3. Masukan gochujang dan kecap.\n" +
                    "4. Tambahkan air dan garam, tunggu sampai mendidih dan reduced.\n" +
                    "5. Masukan tahu, masak kurang lebih 2 menit.\n" ,
            recipePictures = "mapo_tofu",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 178,
            name = "Mie Shirataki Soto",
            calories = 307.0,
            carbs = 34.29,
            fat = 12.68,
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
                    "15 g kerupuk seblak\n" ,
            cookingSteps = "1. Haluskan bawang putih, merah, cabai dan tambahkan garam serta lada.\n" +
                    "2. Masak ayam dipanggang kasih bumbu bawang putih, merica dan ohaiong. Lalu masak telur gausah pake minyak pake telfon aja.\n" +
                    "3. Tambahkan bumbu halus dan tambahkan air jeruk nipis lalu tambahkan sayuran, shirataki, dan paprika. Kemudian tambahkan bumbu Soto Indomie secukupnya. Menyajikan.\n" ,
            recipePictures = "mie_shirataki_soto",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "25 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 180,
            name = "Japchae Shirataki",
            calories = 56.0,
            carbs = 10.54,
            fat = 1.44,
            protein = 1.6,
            ingredients = "1 sedang paprika merah manis\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdt garam\n" +
                    "40 gram wortel\n" +
                    "1 mangkok, dicincang daun bawang (loncang)\n" +
                    "1 sdt gula pasir\n" +
                    "1 sdt bawang putih cincang\n" +
                    "1/2 sdm kecap manis\n" +
                    "200 g mie basah matang\n" +
                    "10 g saus tiram\n" +
                    "20 gram jamur kancing\n" +
                    "10 ml pengganti kecap asin\n" +
                    "5,50 ml minyak goreng\n" ,
            cookingSteps = "1. Rebua mie shirataki sampe matang.\n" +
                    "2. Tumis jamur dan wortel hingga masak, tambahkan garam.\n" +
                    "3. Masukan mie yang sudah matang, aduk hingga tercampur.\n" +
                    "4. Masukan saus yang sudah dibuat. Masak hingga saus surut.\n" +
                    "5. Setelah surut, tambahkan paprika dan daun bawang. Aduk hingga tercampur rata.\n" +
                    "6. Mie siap di sajikan.\n" ,
            recipePictures = "japchae_shirataki",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 185,
            name = "Cah Kangkung Ala",
            calories = 260.0,
            carbs = 21.22,
            fat = 16.17,
            protein = 10.04,
            ingredients = "1 besar telur\n" +
                    "5 gram cabai merah atau rawit\n" +
                    "2 siung bawang putih\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "59 gram kangkung\n" ,
            cookingSteps = "1. Panaskan minyak, masukkan bamer baput.\n" +
                    "2. Tunggu hingga harum, masukkan kangkung dan cabe rawit.\n" +
                    "3. Tambahkan sedikit air.\n" +
                    "4. Tumis kangkung hingga masak dan tidak layu.\n" ,
            recipePictures = "cah_kangkung_ala",
            mealType = 1,
            cookTime = "5 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 187,
            name = "Tahu Gejrot Cirebon",
            calories = 200.0,
            carbs = 25.47,
            fat = 8.87,
            protein = 7.2,
            ingredients = "6 buah tahu goreng\n" +
                    "3 sdt, bongkahan gula merah\n" +
                    "2 sdm kecap manis\n" +
                    "20 g sambal pedas uleg\n" ,
            cookingSteps = "1. Tahu dipotong2 taruh di atas piring.\n" +
                    "2. Ulek cabe, bawang merah, bawang putih, garam.\n" +
                    "3. Didihkan air masukan gula jawa dan kecap sampai mendidih.\n" +
                    "4. Siram tahu dengan larutan gula dan cabe ulek ke atas piring yg berisi tahu.\n" ,
            recipePictures = "tahu_gejrot_cirebon",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 190,
            name = "Tumis Shau",
            calories = 177.0,
            carbs = 27.09,
            fat = 7.16,
            protein = 3.05,
            ingredients = "30 gram broccoli\n" +
                    "75 gram carrots\n" +
                    "1/2 tbsp sesame oil\n" +
                    "15 gram green snap beans\n" +
                    "1 sdm soy sauce (shoyu)\n" +
                    "1/2 sdm honey\n" +
                    "15 g saus tiram\n" +
                    "10 ml kecap pedas\n" ,
            cookingSteps = "1. Cuci dang potong semua sayuran.\n" +
                    "2. Tumis bawang putih dengan minyak wijen.\n" +
                    "3. Masukan semua sayuran dan bumbu lalu tambahkan air.\n" +
                    "4. Siap disajikan.\n" ,
            recipePictures = "tumis_shau",
            mealType = 1,
            cookTime = "5 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 199,
            name = "Rolade Sehat",
            calories = 24.0,
            carbs = 1.37,
            fat = 1.21,
            protein = 1.84,
            ingredients = "100 gram dada ayam\n" +
                    "4 besar telur\n" +
                    "1 sdt minyak wijen\n" +
                    "200 gram wortel\n" +
                    "1/2 sdm minyak goreng\n" +
                    "130 gram tahu\n" +
                    "50 ml susu uht full cream\n" +
                    "40 g rolled oat\n" +
                    "15 ml kecap asin\n" ,
            cookingSteps = "1. Campur semua bahan dengan chopper, kecuali 3 telur dan susu. Beri penyedap rasa, campur sampai rata.\n" +
                    "2. Kocok 3 telur dengan susu, dadar di teflon anti lengket.\n" +
                    "3. Lalu di bentuk seperti rolade dan kukus. Sudah siap disajikan.\n" ,
            recipePictures = "rolade_sehat",
            mealType = 2,
            cookTime = "30 Menit",
            prepTime = "30 Menit",
            portion = 40
        ),
Recipe(
            recipeId = 200,
            name = "Sate Mix Ayam dan Jamur",
            calories = 446.0,
            carbs = 55.41,
            fat = 9.58,
            protein = 37.34,
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
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Rebus jamur tiram , setelah itu tiriskan dan suwir sesuai dengan ukuran yang diinginkan.\n" +
                    "2. Belender bumbu untuk dipanggang setelah itu di tumis.\n" +
                    "3. Marinasi jamur dan dada ayam selama 30 menit.\n" +
                    "4. Tusuk jamur tiram dan dada ayam yg sudah di marinasikan pada tusuk sate.\n" +
                    "5. Panggang sate jamur dada ayam di teflon anti lengket atau panggangan anti lengket.\n" ,
            recipePictures = "sate_mix_ayam_dan_jamur",
            mealType = 3,
            cookTime = "30 Menit",
            prepTime = "45 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 205,
            name = "Ayam Lada Hitam",
            calories = 362.0,
            carbs = 20.2,
            fat = 15.82,
            protein = 36.28,
            ingredients = "230 gram chicken breast\n" +
                    "1 gram young green onions\n" +
                    "30 gram paprika\n" +
                    "16 gram onions\n" +
                    "1 sdm minyak goreng\n" +
                    "3 g lada putih bubuk\n" +
                    "60 g saus teriyaki lada hitam\n" ,
            cookingSteps = "1. Panfry ayam.\n" +
                    "2. Tumis bahan kering.\n" +
                    "3. Masukkan bumbu.\n" ,
            recipePictures = "ayam_lada_hitam",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "1 Jam",
            portion = 2
        ),
Recipe(
            recipeId = 207,
            name = "NasGor Receh",
            calories = 140.0,
            carbs = 22.44,
            fat = 4.08,
            protein = 5.16,
            ingredients = "50 gram nasi putih\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "50 g mie basah matang\n" +
                    "25 g sosis ayam kombinasi\n" +
                    "Cara memasak\n" ,
            cookingSteps = "1. Tumis bawang putih sampai harum.\n" +
                    "2. Masukkan cabai yang telah di haluskan.\n" +
                    "3. Tambahkan sosis dan mie shirataki.\n" +
                    "4. Tambahkan nasi tunggu sampai di rasa sudah cukup matang.\n" +
                    "5. Jika suka bisa di tambahkan telur ceplok,telur rebus atau bakso.\n" ,
            recipePictures = "nasgor_receh",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "3 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 217,
            name = "Tongseng Bango dan Fiber Chreme",
            calories = 360.0,
            carbs = 34.11,
            fat = 10.68,
            protein = 36.39,
            ingredients = "145 gram dada ayam (kulit tidak dimakan)\n" +
                    "120 gram tomat\n" +
                    "15 gram cabai merah atau rawit\n" +
                    "300 gram kubis\n" +
                    "20 gram daun bawang\n" +
                    "20 gram sereh\n" +
                    "95 gram daging sapi (95% tanpa lemak / 5% lemak)\n" +
                    "1 sdm pengganti kecap manis\n" +
                    "5 sdt fiber creme\n" +
                    "26 g tongseng khas solo\n" ,
            cookingSteps = "1. Tumis daging dan bumbu bango.\n" +
                    "2. Masukan air fiber chreme dan bumbu lainnya sampai menyusut.\n" +
                    "3. Masukan kubis dan cabai.\n" +
                    "4. Matikan api kemudian masukan faun bawang dan potongan tomat.\n" ,
            recipePictures = "tongseng_bango_dan_fiber_chreme",
            mealType = 2,
            cookTime = "40 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 227,
            name = "Bakso di Soup",
            calories = 242.0,
            carbs = 9.99,
            fat = 13.64,
            protein = 20.65,
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
                    "40 gram bawang bombay\n" ,
            cookingSteps = "1. Buat adonan bakso dengan cara campurkan tapioka, daging sapi giling, bombay dan bubuk bawang putih serta lada garam. Aduk hingga rata. Dan bulatkan dengan tangan satu per satu. Masukan satu per satu ke dalam air mendidih.\n" +
                    "2. Cincang bawang putih dan daun bawang. Masukan ke dalam kuah. Serta lada garam dan kaldu jamur.\n" +
                    "3. Rebus sayur pokcoy dan toge dalam panci terpisah.\n" +
                    "4. Tunggu hingga bakso matang dan mengapung ke atas. Masukan tahu. Tunggu sampai smua matang. Sajikan bersama toge dan pokcoy rebus sera tambahkan cabai rawit jika suka.\n" ,
            recipePictures = "bakso_di_soup",
            mealType = 3,
            cookTime = "30 Menit",
            prepTime = "20 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 232,
            name = "Cauliflower Nasi Goreng",
            calories = 184.0,
            carbs = 23.66,
            fat = 6.56,
            protein = 11.36,
            ingredients = "1 irisan tebal chicken breast\n" +
                    "1 besar egg\n" +
                    "1 mangkok chinese cabbage\n" +
                    "3 cups cauliflower\n" +
                    "1 sdt olive oil\n" +
                    "1 mangkok, irisan red onions\n" +
                    "3 sdt minced garlic\n" +
                    "4 1/2 sdt chili\n" +
                    "10 ml kecap inggris\n" +
                    "1 sdm kecap manis\n" +
                    "3 g kaldu rasa jamur\n" ,
            cookingSteps = "1. Sangrai di wajan kembang kol sampai berubah warna dan agak kering, lalu sisihkan.\n" +
                    "2. Tumis pakai olive oil bawang merah dan bawang putih sampi berubah warna dan harum lalu masukan cabai, ayam, tambahkan air masak sampai ayam matang sempurna lalu masukan telor aduk aduk sampai matang dan tambah kan air sedikit, kecap, kecap asin, kaldu jamur dll masak sampai semua bumbu meresap.\n" +
                    "3. Masukan kembanh kol, aduk aduk dengan semua bahan diatas, tambahkan lagi sedikit air dengan tujuan supaya semua rasa meresap kedalam kembang kol, masak sampai kembanh kok agak berubah warna dan rasa tekstur sudah mirip seperti nasi goreng.\n" ,
            recipePictures = "cauliflower_nasi_goreng",
            mealType = 3,
            cookTime = "30 Menit",
            prepTime = "30 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 234,
            name = "Chicken Tandoori",
            calories = 218.0,
            carbs = 9.05,
            fat = 12.81,
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
                    "50 g greek yogurt\n" ,
            cookingSteps = "1. Bersihkan ayam. Boleh pakai kulit boleh tidak. Bumbui ayam dengan lada dan garam.\n" +
                    "2. Untuk bumbu marinasi, pertama-tama masukkan greek yoghurt ke mangkuk, campur dengan bumbu bubuk ketumbar, jinten, kunyit, paprika, garam menyesuaikan.\n" +
                    "3. Haluskan 2 butir bawang putih dengan 1 ruas jahe. Kalau suka pedas bisa tambahkan cabe, tapi sebisa mungkin gunakan cabe kering yang sudah jadi bubuk. Tambahkan bumbu halus ke mangkuk dan aduk hingga merata bersama dengan yoghurt dan bumbu bubuk lainnya.\n" +
                    "4. Buat guratan di daging ayam hingga menyentuh tulang.\n" +
                    "5. Lumurkan bumbu marinasi ke ayam hingga merata. Biarkan dahulu di dalam kulkas setidaknya 6 jam atau diamkan semalaman. Fungsinya untuk melunakkan daging ayam dan agar bumbunya meresap.\n" +
                    "6. Setelah didiamkan, keluarkan ayam dari kulkas. Panaskan wajan (boleh pakai minyak 1 sdm).\n" +
                    "7. Masukkan ayam ke wajan dengan kulit menghadap bawah. Bolak balik ayam hingga matang, sekitar 7 menit sambil sedikit-sedikit dilumuri lagi dengan sisa bumbu marinasi.\n" ,
            recipePictures = "chicken_tandoori",
            mealType = 1,
            cookTime = "7 Menit",
            prepTime = "30 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 237,
            name = "Sayur Pokcoy Tahu Telur",
            calories = 194.0,
            carbs = 15.36,
            fat = 11.9,
            protein = 8.65,
            ingredients = "1 sdm minyak kelapa\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "1/4 gelas air\n" +
                    "2 sedang bawang merah\n" +
                    "100 gram tahu\n" +
                    "100 gram pakcoy\n" +
                    "3 g lada putih bubuk\n" +
                    "1,5 g kaldu rasa jamur\n" ,
            cookingSteps = "1. Di atas wajan, tambahkan minyak kelapa, tumis bawang merah dan bawang putih hingga kecoklatan.\n" +
                    "2. Masukkan telur, diorak arik lalu masukkan tahu, aduk aduk selama 5 menit lalu masukkan air putih. Diamkan hingga tahu matang.\n" +
                    "3. Masukkan pakcoy, garam, lada bubuk dan kaldu jamur lalu di aduk aduk hingga pakcoy agak layu dan bumbu tercampur rata.\n" +
                    "4. Siap disajikan.\n" ,
            recipePictures = "sayur_pokcoy_tahu_telur",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 238,
            name = "Bakmi Goreng Shirataki",
            calories = 140.0,
            carbs = 23.8,
            fat = 2.77,
            protein = 8.94,
            ingredients = "25 gram dada ayam rebus (kulit tidak dimakan)\n" +
                    "1/4 mangkok, segar, potongan batang brokoli dimasak (dari segar)\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "25 g mie kering\n" +
                    "3 g lada putih bubuk\n" +
                    "1 sdm kecap manis\n" ,
            cookingSteps = "1. Rebus mie ,brokoli, dada ayam.\n" +
                    "2. Bawang putih dan cabe cincang.\n" +
                    "3. Tumis baput cabe lada dengan air.\n" +
                    "4. Masuk mie brokoli dada ayam.\n" +
                    "5. Koreksi rasa dg tambahkan himsalt dan kecap.\n" +
                    "6. 2 anggota telah menambahkan resep ini ke buku \n" ,
            recipePictures = "bakmi_goreng_shirataki",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 243,
            name = "Nasi Goreng China",
            calories = 450.0,
            carbs = 67.2,
            fat = 14.27,
            protein = 14.04,
            ingredients = "1 besar telur\n" +
                    "1 sdm kecap asin (kedelai)\n" +
                    "2 mangkok, dimasak nasi putih\n" +
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
                    "100 gram sawi hijau\n" ,
            cookingSteps = "1. Panaskan margarin, tumis bawang hingga harum kemudian masukan telur dan sayuran.\n" +
                    "2. Masukan nasi putih, aduk2 jangan lupa beri kecap asin, minyak wijen, saos tiram dan garam(opstional ya kalo kurang asin) dan lada.\n" +
                    "3. Aduk hingga tercampur rata. Nikmati.\n" ,
            recipePictures = "nasi_goreng_china",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 244,
            name = "Tumis Wortel Buncis",
            calories = 295.0,
            carbs = 53.6,
            fat = 5.85,
            protein = 10.99,
            ingredients = "4 telur puyuh\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "44 gram buncis\n" +
                    "68 gram wortel\n" +
                    "3 siung bawang putih\n" +
                    "50 gram nasi merah (butir-sedang, dimasak)\n" +
                    "2 sedang bawang merah\n" +
                    "35 g saus tiram\n" ,
            cookingSteps = "1. Cuci lalu iris wortel dan buncis.\n" +
                    "2. Iris bawang merah, bawang putih, serta cabai lalu tumis dg air secukupnya.\n" +
                    "3. Masukkan wortel dan buncis serta tambahkan sedikit merica bubuk dan saos tiram.\n" +
                    "4. Pindahkan ke piring dengan nasi.\n" ,
            recipePictures = "tumis_wortel_buncis",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 245,
            name = "Tumis Ayam Jamur Tempe",
            calories = 662.0,
            carbs = 62.9,
            fat = 24.51,
            protein = 56.15,
            ingredients = "1/2 kecil chicken breast\n" +
                    "3 kecil cooked mushrooms (fat added in cooking)\n" +
                    "1 medium onion\n" +
                    "2 green hot chili peppers\n" +
                    "1 cup pieces mushrooms (with salt, drained, cooked, boiled)\n" +
                    "100 gram tempeh (cooked)\n" +
                    "1 small red onion\n" +
                    "6 tbsps chili\n" +
                    "100 g saus tiram\n" ,
            cookingSteps = "1. Tumis bawang dan cabe (tanpa minyak).\n" +
                    "2. Masukkan ayam dan tempe. Masak sampai matang.\n" +
                    "3. Masukkan jamur + beri sedikit air.\n" +
                    "4. Beri saori saus tiram, dan koreksi rasa.\n" ,
            recipePictures = "tumis_ayam_jamur_tempe",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 246,
            name = "Ayam Suwir Balado",
            calories = 708.0,
            carbs = 112.94,
            fat = 18.19,
            protein = 43.14,
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
                    "20 ml gula jawa\n" ,
            cookingSteps = "1. Haluskan bawang merah, bawang putih, cabai, kemiri, tomat.\n" +
                    "2. Suwir-suwir ayam rebus dengan garpu / tangan.\n" +
                    "3. Tumis bumbu halus diatas teflon anti lengket. Sampai harum. Tambahkan garam.\n" +
                    "4. Masukkan ayam suwir kedalam tumisan bumbu, tambahkan kaldu jamur, kecap asin, gula jawa.\n" +
                    "5. Aduk hingga rata. Tes rasa. Siap disajikan.\n" ,
            recipePictures = "ayam_suwir_balado",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 250,
            name = "Capcay Ayam Kuah tanpa Minyak",
            calories = 91.0,
            carbs = 5.52,
            fat = 1.6,
            protein = 13.65,
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
                    "1,5 g kaldu rasa jamur\n" ,
            cookingSteps = "1. Cuci sayuran dan ayam.\n" +
                    "2. Geprak bawang putih hingga halus.\n" +
                    "3. Tumis bawang putih dan bombay dan wortel di teflon tanpa minyak.\n" +
                    "4. Masukan air secukupnya,.\n" +
                    "5. Jika sudah mendidih masukan bumbu sesuai selera (garam,saus tiram,kaldu jamur).\n" +
                    "6. Masukan ayam masak hingga matang,.\n" +
                    "7. Masukan sayuran.\n" +
                    "8. Koreksi rasa , selesai.\n" ,
            recipePictures = "capcay_ayam_kuah_tanpa_minyak",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 253,
            name = "Kekian Udang",
            calories = 46.0,
            carbs = 1.93,
            fat = 1.05,
            protein = 7.19,
            ingredients = "640 gram udang\n" +
                    "1 sedang telur\n" +
                    "123 gram wortel\n" +
                    "21 gram bawang putih\n" +
                    "30 gram daun bawang (loncang)\n" +
                    "60 g tepung tapioka\n" +
                    "75 g kembang tahu\n" ,
            cookingSteps = "1. Haluskan udang.\n" +
                    "2. Haluskan bawang putih garam dan merica.\n" +
                    "3. Parut wortel dan iris kecil-kecil daun bawang.\n" +
                    "4. Masukkan wortel daun bawang telur dan bumbu halus kedalam udang yang telah dihaluskan.\n" +
                    "5. Aduk sampai rata kemudian masukkan tepung tapioka.\n" +
                    "6. Taruh di dalam kulit tahu yang telah di rendam air.\n" +
                    "7. Kukus kurang lebih 10 menit.\n" +
                    "8. 5 anggota telah menambahkan resep\n" ,
            recipePictures = "kekian_udang",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 31
        ),
Recipe(
            recipeId = 254,
            name = "Cah Sayur",
            calories = 240.0,
            carbs = 36.44,
            fat = 1.46,
            protein = 20.94,
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
                    "1.5 g kaldu rasa jamur\n" ,
            cookingSteps = "1. Tumis bawput dan cabe.\n" +
                    "2. Masukan air, ayam dan kentang.\n" +
                    "3. Campur semua bahan jadi.\n" +
                    "4. Tambahkan bumbu perasa.\n" +
                    "5. Sajikan selagi hangat.\n" ,
            recipePictures = "cah_sayur",
            mealType = 3,
            cookTime = "6  Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 255,
            name = "Spaghetti Aglio Olio Bandeng tanpa Minyak",
            calories = 462.0,
            carbs = 65.74,
            fat = 9.1,
            protein = 31.09,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "30 gram brokoli\n" +
                    "1 utuh sedang tomat merah\n" +
                    "100 gram ikan bandeng\n" +
                    "1 kecil bawang merah\n" +
                    "60 g spaghetti\n" +
                    "40 gram bawang bombay\n" +
                    "1,5 g kaldu rasa jamur\n" ,
            cookingSteps = "1. Rebus spaghetti wholemeal selama 10 menit.\n" +
                    "2. Haluskan tomat dan bawang merah bisa di uleg atau di blender beri sedikit air.\n" +
                    "3. Panaskan telflon anti lengket, masukan bawang bombai sangrai sampai harum (tanpa minyak).\n" +
                    "4. Setelah itu masukan bumbu yg sudah di haluskan.\n" +
                    "5. Tambahkan bandeng suwir, irisan cabai, brokoli, garam, dan kaldu jamur.\n" +
                    "6. Kemudian masukan spaghetti wholemeal yg sudah di rebus dan beri sedikit seledri, aduk sampai rata.\n" ,
            recipePictures = "spaghetti_aglio_olio_bandeng_tanpa_minyak",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "20 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 256,
            name = "Cah Brokoli Ayam",
            calories = 183.0,
            carbs = 4.84,
            fat = 8.64,
            protein = 20.91,
            ingredients = "200 gram dada ayam\n" +
                    "1 sdt garam\n" +
                    "1 sdm saus tiram\n" +
                    "100 gram brokoli\n" +
                    "20 gram wortel\n" +
                    "2 siung bawang putih\n" +
                    "150 ml air\n" +
                    "1 sdm minyak goreng\n" +
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Haluskan bawang putih dan lada.\n" +
                    "2. Panaskan minyak.\n" +
                    "3. Tumis bumbu sampai harum.\n" +
                    "4. Masukkan ayam tumis sampai matang.\n" +
                    "5. Tambahkan air matang secukupnya.\n" +
                    "6. Masukkan brokoli.\n" +
                    "7. Tambahkan garam.\n" +
                    "8. Pindahkan ke piring saat sudah matang.\n" ,
            recipePictures = "cah_brokoli_ayam",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 3
        ),
Recipe(
            recipeId = 257,
            name = "Nasi Goreng Tapi Bohong",
            calories = 291.0,
            carbs = 40.05,
            fat = 12.34,
            protein = 6.81,
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
                    "1 sendok makan minyak goreng\n" ,
            cookingSteps = "1. Sangrai oatmeal sampai berubah warna kecoklatan.\n" +
                    "2. Tambahkan air sedikit demi sedikit, sisihkan.\n" +
                    "3. Tumis bawang bombay dan bawang putih hingga harum, lalu tambahkan tauge wortel, tumis hingga layu. Tambahkan garam, penyedap rasa & saus tiram.\n" +
                    "4. Masukkan oatmeal yg sudah disangrai tadi.\n" +
                    "5. Tumis sebentar hingga tercampur rata. Test rasa, sajikan\n" ,
            recipePictures = "nasi_goreng_tapi_bohong",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 259,
            name = "Steak Tempe",
            calories = 135.0,
            carbs = 29.52,
            fat = 3.96,
            protein = 6.21,
            ingredients = "1 mangkok, irisan cooked carrots\n" +
                    "2 sdm tomato catsup\n" +
                    "2 sdm salt\n" +
                    "1 mangkok chayote (fruit)\n" +
                    "4 siung garlic\n" +
                    "1 mangkok, dicincang onions\n" +
                    "30 ml bottled water\n" +
                    "100 gram tempeh (cooked)\n" +
                    "1 sdm saus sambal\n" +
                    "100 g saus tiram\n" +
                    "3 g lada putih bubuk\n" +
                    "10 g tepung maizena\n" ,
            cookingSteps = "1. Potong tempe, lalu kukus hingga matang.\n" +
                    "2. Haluskan bawang putih hingga halus lalu tambahkan tempe kukus lalu haluskan. Tambahkan tepung terigu, garam dan merica, aduk hingga merata, lalu bentuk adonan bulat pipih.\n" +
                    "3. Panaskan pan, tambahkan margarin dan minyak zaitun, lalu grill tempe sampai matang, jgn sering2 dibolak balik agar tdk hancur, masak jg pinggirannya.\n" +
                    "4. Saus: lelehkan margarin, lalu tumis bawang bombay sampai layu kmudian masukkan bawang putih tumis sampai harum, lalu masukkan semua bahan saus dan lada, lalu tambahkan air, cek rasa, terakhir masukkan larutan maizena, aduk2 hingga mengental, sajikan.\n" ,
            recipePictures = "steak_tempe",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 260,
            name = "Shirataki Tomato Soup",
            calories = 163.0,
            carbs = 21.74,
            fat = 6.37,
            protein = 6.29,
            ingredients = "2 chicken nugget\n" +
                    "1 sedang telur\n" +
                    "1 mangkok, segar sawi dimasak (dari segar)\n" +
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
                    "2 g royco ayam\n" ,
            cookingSteps = "1. Panaskan minyak ½ sendok masukkan nugget yg dipotong dadu.\n" +
                    "2. Masukkan batang sawi, wortel dan buncis tambahkan air 250ml.\n" +
                    "3. Sisihkan tumisan ke pinggir wajan lalu masukkan telur, beri garam dan orak arik bersama sayur.\n" +
                    "4. Masukkan irisan tomat, sawi, tambahkan air 500ml masukkan mie shirataki.\n" +
                    "5. Tambahkan royko/kaldu ayam, garam, irisan cabe, saus teriyaki dan kecap.\n" +
                    "6. Diamkan hingga mendidih.\n" +
                    "7. Ready to serving.\n" ,
            recipePictures = "shirataki_tomato_soup",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 3
        ),
Recipe(
            recipeId = 261,
            name = "Capcay",
            calories = 363.0,
            carbs = 47.46,
            fat = 12.39,
            protein = 17.07,
            ingredients = "1 besar telur\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1 mangkok, dicincang sawi\n" +
                    "3 siung bawang putih\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm kecap manis\n" +
                    "50 g sosis sapi\n" +
                    "9 g saus sambal\n" ,
            cookingSteps = "1. Tumis bawang putih, bawang merah, dan cabai hingga harum.\n" +
                    "2. Tuang air secukupnya tunggu sampai mendidih.\n" +
                    "3. Masukkan sawi, sosis, dan telur.\n" +
                    "4. Tambahkan garam, lada, kecap secukupnya.\n" +
                    "5. Tunggu hingga matang.\n" ,
            recipePictures = "capcay",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 262,
            name = "Sohun Goreng",
            calories = 392.0,
            carbs = 44.58,
            fat = 13.1,
            protein = 27.31,
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
                    "10 g bumbu racik nasi goreng\n" ,
            cookingSteps = "1. Rendam sohun dngn air panas 5 menit kemudian tiriskan.\n" +
                    "2. Iris bawang merah dan bawang putih. Kemudian potong dadu dada ayam dan tempe.\n" +
                    "3. Tumis bawang merah dan bawang putih sebentar. Kemudian masukkan dada ayam dan tempe.\n" +
                    "4. Masukkan toge dan sawi aduk2. Jngan lupa bumbu nasi goreng instannya ya Terakhir masukkan sohun nya tumis hingga matang.\n" +
                    "5. Sajikan dengan irisan mentimun dan tomat.\n" ,
            recipePictures = "sohun_goreng",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 264,
            name = "Tumis Jamur Kacang Panjang",
            calories = 91.0,
            carbs = 19.07,
            fat = 1.78,
            protein = 3.39,
            ingredients = "100 gram kacang panjang hijau\n" +
                    "10 gram kaldu jamur\n" +
                    "5 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm kecap manis\n" +
                    "100 g saus tiram\n" +
                    "50 gram bawang bombay\n" +
                    "100 gram jamur kancing\n" ,
            cookingSteps = "1. Tumis bawang putih , bawang bombay dan cabai rawit yang sudah di iris tipis.\n" +
                    "2. Masukan jamur dan kacang panjang yang sudah di potong-potong.\n" +
                    "3. Tambahkan kaldu jamur , saos tiram , garam , dan kecap sesuai selera.\n" ,
            recipePictures = "tumis_jamur_kacang_panjang",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "15 Menit",
            portion = 3
        ),
Recipe(
            recipeId = 265,
            name = "Telur Sutera",
            calories = 195.0,
            carbs = 22.75,
            fat = 6.81,
            protein = 12.76,
            ingredients = "1 besar telur\n" +
                    "50 gram wortel dimasak\n" +
                    "1 besar putih telur\n" +
                    "1 sdm saus tiram\n" +
                    "1 potong jagung rebus\n" +
                    "1 siung bawang putih\n" +
                    "1 gram daun bawang\n" +
                    "10 gram bawang bombay\n" ,
            cookingSteps = "1. Kocok telur dengan irisan daun bawang, dengan menambahkan 100ml air. Masukkan garam dan merica sesuai selera. Lalu kukus.\n" +
                    "2. Rebus jagung dan wortel, tumis bawang bombay dan bawang putih dengan 1 sendok olive oil.\n" +
                    "3. Masukkan sayuran kedalam tumisan bawang, lalu tambahkan garam, merica dan 1 sendok saus tiram.\n" +
                    "4. Yang terakhir koreksi rasa.\n" +
                    "5. 1 anggota telah menambah\n" ,
            recipePictures = "telur_sutera",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "30 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 269,
            name = "Ayam Geprek Low Calori",
            calories = 329.0,
            carbs = 18.1,
            fat = 16.74,
            protein = 26.56,
            ingredients = "137 gram dada ayam\n" +
                    "1 sedang telur\n" +
                    "1 1/2 sdm cabai merah atau rawit\n" +
                    "1 sdm minyak sayur canola\n" +
                    "4 siung bawang putih\n" +
                    "40 g rolled oats\n" ,
            cookingSteps = "1. Iris tipis” dada ayam.\n" +
                    "2. Baluri telur.\n" +
                    "3. Blender rolled oat untuk jadi tepung.\n" +
                    "4. Balurkan ayam dengan tepung rolled oat.\n" +
                    "5. Goreng ayam pakai minyak canola 1 sdm.\n" +
                    "6. Uleg cabai dan bawang putih sesuai selera.\n" ,
            recipePictures = "ayam_geprek_low_calori",
            mealType = 2,
            cookTime = "30 Menit",
            prepTime = "50 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 272,
            name = "Oseng Buncis Tempe",
            calories = 213.0,
            carbs = 36.32,
            fat = 6.32,
            protein = 7.92,
            ingredients = "16 gram cabai merah atau rawit\n" +
                    "1/4 sdt garam\n" +
                    "1 sdm saus tiram\n" +
                    "90 gram buncis\n" +
                    "3 siung bawang putih\n" +
                    "3 sedang bawang merah\n" +
                    "62 gram tempe goreng\n" +
                    "1 sdm kecap manis\n" ,
            cookingSteps = "1. Tempe yang sudah digoreng dipotong kotak-kotak,Buncis potong sesuai selera.\n" +
                    "2. Tumis bamer , baput & cabe hingga harum.\n" +
                    "3. Masukan tempe yang sudah digoreng & buncis.\n" +
                    "4. Tambahkan kecap, saus tiram, garam , dan air seckupnya. Lalu tunggu hingga matang & air menyusut.\n" ,
            recipePictures = "oseng_buncis_tempe",
            mealType = 2,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 274,
            name = "Nasgor Merah",
            calories = 322.0,
            carbs = 41.06,
            fat = 11.69,
            protein = 15.67,
            ingredients = "78 gram telur\n" +
                    "130 gram nasi merah\n" +
                    "10 gram cabai merah atau rawit\n" +
                    "10 gram bawang putih\n" +
                    "10 gram bawang merah\n" +
                    "1 ml minyak goreng\n" +
                    "40 gram sawi hijau\n" ,
            cookingSteps = "1. Potong bawang cabe lalu tumis masukan telur hingga matang masukan sawi hingga layu masukan nasi dan tambahkan garam.\n" +
                    "2. Angkat nasgor jika sudah matang.\n" +
                    "3. Sajikan d piring.\n" ,
            recipePictures = "nasgor_merah",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 275,
            name = "Mentai Shirataki",
            calories = 352.0,
            carbs = 24.27,
            fat = 27.76,
            protein = 2.2,
            ingredients = "3 sdm mayonnaise\n" +
                    "100 g mie kering\n" +
                    "18 ml saus sambal\n" +
                    "64 g crab stick\n" +
                    "3 tbsps sesame oil\n" ,
            cookingSteps = "1. Rebus mie shirataki.\n" +
                    "2. Campurkan mie shirataki dengan sejumput garam dan minyak wijen.\n" +
                    "3. Tata di ramekin, tambahkan crab stick rebus, dan beri mentai.\n" +
                    "4. Bakar menggunakan gas torch.\n" ,
            recipePictures = "mentai_shirataki",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 284,
            name = "Pokcoy Siram Jamur Enoki",
            calories = 117.0,
            carbs = 5.65,
            fat = 7.38,
            protein = 8.06,
            ingredients = "80 gram daging babi\n" +
                    "1 sdm margarin\n" +
                    "10 gram kaldu jamur\n" +
                    "1 sejumput lada hitam\n" +
                    "480 gram pakcoy\n" +
                    "80 g jamur enoki\n" +
                    "100 g egg tofu\n" +
                    "1/2 sedang bawang bombay\n" ,
            cookingSteps = "1. Rebus pokcoy d air mendidih yang d tambah garam.\n" +
                    "2. Lelehkan margarin dan tumus bawang bombay hingga layu.\n" +
                    "3. Masukkan daging babi dan tumis hingga berubah warna.\n" +
                    "4. Beri sedikit air bumbui dengan lada hitam dan kaldu jamur aduk rata.\n" +
                    "5. Masukkan jamur enoki, aduk rata hingga layu koreksi rasa.\n" +
                    "6. Tata pokcoy yg sudah d rebus d atas piring. Siram dengan tumisan jamur.\n" ,
            recipePictures = "pokcoy_siram_jamur_enoki",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "15 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 287,
            name = "Mie Shirataki",
            calories = 371.0,
            carbs = 0.0,
            fat = 11.44,
            protein = 32.95,
            ingredients = "75 gram udang\n" +
                    "1 sdm saus tiram\n" +
                    "82 gram brokoli\n" +
                    "50 gram kubis\n" +
                    "1 siung bawang putih\n" +
                    "7 gram bawang merah\n" +
                    "1/2 sdm kecap manis\n" +
                    "200 g mie kering shirataki\n" +
                    "94 gram sawi dimasak (dari segar)\n" +
                    "1 telur dadar atau telur orak-arik\n" ,
            cookingSteps = "1. Cuci mie shirataki basah dan tiriskan.\n" +
                    "2. Rebus mie 3menit, sambil potong2 sayuran kemudian cuci bersih.\n" +
                    "3. Siapkan teflon anti lengket, tumis bawang merah dan bawang putih sampai harum, masukkan telur lalu orak arik. Masukkan udang kemudian tambahkan sedikit air. Masak hingga udang matang, kemudian masukkan sayur2an.\n" +
                    "4. Tambahkan saus tiram dan kecap manis, masukkan mie shirataki. Aduk rata, masak sampai semua matang.\n" +
                    "5. Angkat dan sajikan.\n" ,
            recipePictures = "mie_shirataki",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 290,
            name = "Bakso Tempe",
            calories = 32.0,
            carbs = 4.02,
            fat = 1.18,
            protein = 1.42,
            ingredients = "100 gram telur rebus\n" +
                    "5 gram kaldu jamur\n" +
                    "1 sdt lada hitam\n" +
                    "3 sdt garam\n" +
                    "22 gram bawang putih\n" +
                    "200 g tepung tapioka\n" +
                    "252 gram tempe kukus\n" ,
            cookingSteps = "1. Kukus tempe 15 menit.\n" +
                    "2. Hancurkan tempe dan masukkan semua bahan, aduk sampai kalis.\n" +
                    "3. Bentuk bulat tempe dan rebus 15 menit sampai mengambang.\n" ,
            recipePictures = "bakso_tempe",
            mealType = 3,
            cookTime = "45 Menit",
            prepTime = "15 Menit",
            portion = 20
        ),
Recipe(
            recipeId = 292,
            name = "Makaroni ayam",
            calories = 147.0,
            carbs = 18.83,
            fat = 3.8,
            protein = 8.92,
            ingredients = "100 gram dada ayam\n" +
                    "1 mangkok,masak makaroni\n" +
                    "1 mangkok saus spageti tanpa daging\n" +
                    "1 siung bawang putih dimasak\n" +
                    "100 gram bawang bombay dimasak\n" +
                    "1 sdt bawang putih bubuk\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sejumput garam laut\n" +
                    "1 sdm saus sambal\n" ,
            cookingSteps = "1. Potong bawang putih (1 siung), bawang bombay setengah aja.\n" +
                    "2. Marinasi ayam dengan lada, bawang putih bubuk dan garam ayamnya di potong dadu ya.\n" +
                    "3. Tumis bawang putih dan bawang bombay.\n" +
                    "4. Masukkan ayam yang sudah dimarinasi.\n" +
                    "5. Beri sedikit air.\n" +
                    "6. Kemudian masukkan saus bolognese dan saus sambal 1 sendok.\n" +
                    "7. Kemudian masak hingga air menyusut.\n" +
                    "8. Tambah sedikit keju quick melt untuk hiasan aja sih hehe.\n" ,
            recipePictures = "makaroni_ayam",
            mealType = 2,
            cookTime = "30 Menit",
            prepTime = "20 Menit",
            portion = 5
        ),
Recipe(
            recipeId = 294,
            name = "Nasi Goreng Spesial (Nasi Merah)",
            calories = 509.0,
            carbs = 42.35,
            fat = 17.59,
            protein = 43.34,
            ingredients = "2 besar telur\n" +
                    "2 sdt garam\n" +
                    "100 gram daging ayam (panggang, bakar, dimasak)\n" +
                    "3 siung bawang putih\n" +
                    "100 gram sayur campuran\n" +
                    "100 gram nasi merah (butir-sedang, dimasak)\n" +
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Haluskan bawang putih. Oseng sebentar, lalu masukkan sayuran dan daging ayam rebus.\n" +
                    "2. Pecahkan satu telur, buat scramble egg. Lalu masukkan nasi merah.\n" +
                    "3. Tambahkan garam dan lada. Campur sampai rata dan matang.\n" +
                    "4. Buat satu telur mata sapi. Hidangkan dipiring.\n" ,
            recipePictures = "nasi_goreng_spesial_(nasi_merah)",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 295,
            name = "Omlate Bayam",
            calories = 179.0,
            carbs = 6.31,
            fat = 13.65,
            protein = 8.0,
            ingredients = "55 gram telur\n" +
                    "30 gram wortel\n" +
                    "28 gram bayam\n" +
                    "1 takaran royco ayam\n" +
                    "9 ml minyak goreng filma\n" +
                    "1 takaran lada putih bubuk\n" ,
            cookingSteps = "1. Rebus bayam, parut wortel.\n" +
                    "2. Kocok 1 butir telur, campurkan dengan parutan wortel, bayar rebus.\n" +
                    "3. Tambahkan bumbu penyedap, lalu bisa tambahkan cabai/saus sambal bila suka pedas.\n" +
                    "4. Panaskan minyak goreng secukupnya lalu masak kurang lebih selama 6mnt.\n" ,
            recipePictures = "omlate_bayam",
            mealType = 2,
            cookTime = "6 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 296,
            name = "Seblak Mie Shirataki",
            calories = 114.0,
            carbs = 11.65,
            fat = 5.38,
            protein = 7.13,
            ingredients = "1 besar telur\n" +
                    "2 gram cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "4 gram bawang putih\n" +
                    "10 gram bawang merah\n" +
                    "2 g kencur\n" +
                    "1 bendak wangi sweetener diabtx\n" +
                    "40 gram pakcoy\n" +
                    "100 g mie basah matang\n" ,
            cookingSteps = "1. Blender bawang merah, bawang putih, cengek, kencur/cikur dan air, atau bisa di uleg.\n" +
                    "2. Tumis bumbu (di teflon anti lengket) sampai airnya surut, (biar gak pait), terus jangan tambah minyak.\n" +
                    "3. Lalu masukan air dan telur, tunggu sampai matang. Bisa tambah daun jeruk biar wangi.\n" +
                    "4. Tambah mie shirataki dan pakcoy.\n" +
                    "5. Masukan gula tropicana dan garam sesuai selera.\n" +
                    "6. Cicipi, tunggu sampai airnya agak surut.\n" +
                    "7. Selesai.\n" ,
            recipePictures = "seblak_mie_shirataki",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 297,
            name = "Kangkung Tumis",
            calories = 450.0,
            carbs = 35.27,
            fat = 32.4,
            protein = 10.93,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 siung bawang putih\n" +
                    "1 irisan tomat merah\n" +
                    "1 kecil bawang merah\n" +
                    "3 sdm minyak goreng\n" +
                    "300 gram kangkung\n" +
                    "100 g saus tiram\n" +
                    "23 g saus tiram\n" ,
            cookingSteps = "1. Iris kangkung dan bahan lain nya pisahkan.\n" +
                    "2. Tumis bahan.\n" +
                    "3. Lalu masukan kangkung, Saori dan sedikit air.\n" +
                    "4. Tunggu sampai kangkung masak dan siap disajikan.\n" ,
            recipePictures = "kangkung_tumis",
            mealType = 1,
            cookTime = "5 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 298,
            name = "Steam Fish",
            calories = 310.0,
            carbs = 21.32,
            fat = 8.18,
            protein = 38.45,
            ingredients = "2 sdm kecap asin (kedelai)\n" +
                    "8 g jeruk nipis\n" +
                    "1 sdm cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "1 sdt minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "1 siung bawang putih\n" +
                    "2 iris diameter 2,5 cm jahe\n" +
                    "30 gram daun bawang\n" +
                    "20 gram sereh\n" +
                    "200 gram ikan bawal\n" +
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Bumbui ikan dengan garam, lada, dan jeruk nipis, diamkan 10 menit.\n" +
                    "2. Letakkan ikan pada wadah tahan panas, Kukus bersama irisan jahe, bawang putih dan sereh selama 10 menit. Kemudian buang airnya ( ikan berair saat dikukus).\n" +
                    "3. Siap kan saus, (5 sdm air matang, minyak wijen, kecap asin, saus tiram, lada. Jika kurang asin tambahkan sedikit garam).\n" +
                    "4. Setelah 10 menit angkat ikan, masukkan saus. Kemudian kukus lagi selama 10 menit.\n" +
                    "5. Tambahkan daun bawang dan cabai merah. Kukus lagi sebentar kemudian angkat.\n" ,
            recipePictures = "steam_fish",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 299,
            name = "Sayur Bayam Bening",
            calories = 55.0,
            carbs = 9.35,
            fat = 1.51,
            protein = 5.01,
            ingredients = "1 sdm cabai merah atau rawit\n" +
                    "1 iris, tipis bawang\n" +
                    "150 gram bayam\n" ,
            cookingSteps = "1. Bersihkan sayur.\n" +
                    "2. Rebus air sampai mendidih.\n" +
                    "3. Masukkan kembali bayam sampai tidak keras lagi. Menyajikan.\n" ,
            recipePictures = "sayur_bayam_bening",
            mealType = 1,
            cookTime = "3 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 300,
            name = "Tumis Tempe Sayur",
            calories = 180.0,
            carbs = 24.44,
            fat = 5.66,
            protein = 11.96,
            ingredients = "1 sdm kecap asin (kedelai)\n" +
                    "100 gram buncis\n" +
                    "100 gram kubis\n" +
                    "100 gram wortel\n" +
                    "1 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "100 gram tempe\n" +
                    "1 sdm kecap manis\n" +
                    "2 g royco ayam\n" ,
            cookingSteps = "1. Iris bawang putih, wortel, buncis, kubis dan potong tempe sesuai selera.\n" +
                    "2. Tumis bawang putih dengan sedikit air sampai layu dan harum.\n" +
                    "3. Masukan wortel, buncis dan tempe. Lalu masukan kecap asin 1 sdm, kecap manis 1 sdm dan royco rasa ayam secukupnya.\n" +
                    "4. Masukan air secukupnya sedikit demi sedikit agar sayuran dan tempe matang. Aduk rata. Tunggu sampai air menyusut, jika dirasa kurang matang tambahkan lagi air dan biarkan menyusut.\n" +
                    "5. Terakhir masukan kol, aduk dan tunggu sampai kol matang.\n" +
                    "6. Siap disajikan.\n" ,
            recipePictures = "tumis_tempe_sayur",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 309,
            name = "Tumis Brokoli Udang",
            calories = 329.0,
            carbs = 14.31,
            fat = 16.54,
            protein = 32.91,
            ingredients = "100 gram udang\n" +
                    "2 mangkok, dicincang brokoli\n" +
                    "1 siung bawang putih\n" +
                    "1 sdm minyak zaitun\n" ,
            cookingSteps = "1. Tumis bawang putih menggunakan minyak zaitun hingga harum.\n" +
                    "2. Masukan udang, masak hingga berubah warna, beri sedikit air, masukan garam dan lada.\n" +
                    "3. Masukan brokoli, tumis hingga matang.\n" ,
            recipePictures = "tumis_brokoli_udang",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 312,
            name = "Tumis Kacang Panjang",
            calories = 280.0,
            carbs = 8.47,
            fat = 13.31,
            protein = 31.12,
            ingredients = "100 gram dada ayam\n" +
                    "55 g kacang panjang\n" +
                    "25 gram bombay\n" +
                    "1 sdt minyak wijen\n" +
                    "1 siung bawang putih\n" ,
            cookingSteps = "1. Potong kacang panjang, dada ayam, bawang bombay sesuai selera.\n" +
                    "2. Panaskan sebentar minyak, masukkan bawang bombay dan bawang putih hingga harum lalu masukkan ayam hingga berubah warna, terakhir masukkan kacang panjang.\n" +
                    "3. Tambahkan sedikit air, penyedap/garam secukupnya, masak hingga matang, taburkan bawang goreng. Siap dihidangkan.\n" ,
            recipePictures = "tumis_kacang_panjang",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 6,
            name = "Kue Pisang",
            calories = 118.0,
            carbs = 19.31,
            fat = 2.76,
            protein = 6.3,
            ingredients = "1 besar telur\n" +
                    "1 sdt kayu manis\n" +
                    "1/4 sdt garam\n" +
                    "1 ekstra besar pisang\n" +
                    "1 sdt baking soda\n" +
                    "50 g instant oatmeal\n" +
                    "3 g stevia sweetener\n" +
                    "15 g wpro milk tea\n" ,
            cookingSteps = "1. Hancurkan pisang.\n" +
                    "2. Aduk telur sampai berbusa, masukkan kedalam pisang.\n" +
                    "3. Tambahkan sisa bahan dan aduk rata.\n" +
                    "4. Bagi adonan ke dalam cetakan dan panggang dalam oven dengan suhu 210°C selama 15-20 menit.\n" ,
            recipePictures = "kue_pisang",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "15 menit",
            portion = 4
        ),
Recipe(
            recipeId = 13,
            name = "Tumis Kubis",
            calories = 89.0,
            carbs = 19.17,
            fat = 1.55,
            protein = 3.4,
            ingredients = "400 gram kubis hijau\n" +
                    "30 gram cabai merah atau rawit\n" +
                    "20 gram bawang putih\n" +
                    "1 kecil utuh tomat merah\n" +
                    "20 gram gula merah\n" +
                    "45 gram bawang merah\n" +
                    "3 g kaldu jamur\n" +
                    "1/2 sdm saus tiram\n" +
                    "2 gram udang rebon\n" ,
            cookingSteps = "1. Potong sayuran sesuai ukuran yang diinginkan.\n" +
                    "2. Tumis bawang merah dan bawang putih dalam wajan yang diolesi sedikit minyak.\n" +
                    "3. Tambahkan bahan lainnya satu per satu.\n" +
                    "4. Tuang air secukupnya dan tutup. Masak hingga matang.\n" ,
            recipePictures = "tumis_kubis",
            mealType = 3,
            cookTime = "15 menit",
            prepTime = "15 menit",
            portion = 4
        ),
Recipe(
            recipeId = 58,
            name = "Brownies Mocaf",
            calories = 213.0,
            carbs = 25.52,
            fat = 12.1,
            protein = 2.37,
            ingredients = "2 besar telur\n" +
                    "100 gram coklat manis atau gelap\n" +
                    "50 gram mentega\n" +
                    "15 gram kacang almond\n" +
                    "100 gram gula merah\n" +
                    "13 g coklat bubuk\n" +
                    "30 ml extra virgin olive oil\n" +
                    "100 g tepung mocaf\n" ,
            cookingSteps = "1. Lelehkan coklat manis gelap dengan minyak zaitun dan mentega.\n" +
                    "2. Kocok telur dan gula merah semut dengan whisk.\n" +
                    "3. Masukkan lelehan coklat tadi,aduk rata.\n" +
                    "4. Masukan tepung mocaf aduk rata.\n" +
                    "5. Masukan lagi coklat bubuk, aduk rata kembali.\n" +
                    "6. Panaskan oven 180°C selama 10 menit.Masukan adonan kedalam loyang panggang dan tambahkan almond sebagai topping selama 20-25m.\n" +
                    "7. Brownis siap dihidangkan.\n" ,
            recipePictures = "brownies_mocaf",
            mealType = 1,
            cookTime = "25 menit",
            prepTime = "10 menit",
            portion = 10
        ),
Recipe(
            recipeId = 76,
            name = "Enoki Pedas Manis",
            calories = 134.0,
            carbs = 14.56,
            fat = 8.0,
            protein = 3.61,
            ingredients = "1 sdt bubuk cabai\n" +
                    "1 sdt garam\n" +
                    "1 sdm minyak wijen\n" +
                    "2 siung bawang putih\n" +
                    "2 sdm saus tomat\n" +
                    "1 sdt gula pasir\n" +
                    "2 ml minyak goreng\n" +
                    "200 g jamur enoki\n" +
                    "2 sdm saos tiram\n" ,
            cookingSteps = "1. Potong akar jamur dan cuci.\n" +
                    "2. Panaskan minyak dalam wajan dan tumis bawang putih hingga harum.\n" +
                    "3. Tambahkan sisa bahan secara bertahap.\n" +
                    "4. Masak selama 10 menit atau sampai empuk.\n" ,
            recipePictures = "enoki_pedas_manis",
            mealType = 3,
            cookTime = "20 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 77,
            name = "Tumis Kecambah",
            calories = 95.0,
            carbs = 8.14,
            fat = 3.48,
            protein = 8.26,
            ingredients = "62 gram telur\n" +
                    "71 gram tomat\n" +
                    "100 gram kecambah\n" +
                    "33 gram putih telur\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdt garam\n" +
                    "6 gram bawang putih\n" +
                    "25 gram daun bawang\n" +
                    "15 gram bawang merah\n" +
                    "1 sdm sambal tabur\n" ,
            cookingSteps = "1. Cuci dan potong sayuran.\n" +
                    "2. Masukkan tomat ke dalam wajan panas dan aduk sampai jusnya keluar.\n" +
                    "3. Tambahkan bawang putih dan bawang bombay. Aduk hingga harum.\n" +
                    "4. Masukkan sisa bahan satu per satu. Tambahkan air sesuai kebutuhan dan didihkan selama 3 menit.\n" ,
            recipePictures = "tumis_kecambah",
            mealType = 1,
            cookTime = "15 menit",
            prepTime = "15 menit",
            portion = 2
        ),
Recipe(
            recipeId = 78,
            name = "Tumis Sayuran",
            calories = 80.0,
            carbs = 18.12,
            fat = 0.31,
            protein = 3.48,
            ingredients = "1 sejumput lada hitam\n" +
                    "140 gram buncis\n" +
                    "100 gram wortel\n" +
                    "120 gram kembang kol\n" +
                    "3 siung bawang putih\n" +
                    "1 sejumput garam laut\n" +
                    "1 1/2 sdm saos tiram\n" +
                    "60 gram bawang bombay\n" ,
            cookingSteps = "1. Potong sayuran menjadi potongan-potongan kecil.\n" +
                    "2. Tumis bawang putih dan bawang bombay dalam wajan antilengket.\n" +
                    "3. Perlahan tambahkan sisa bahan. Masak hingga empuk.\n" ,
            recipePictures = "tumis_sayuran",
            mealType = 2,
            cookTime = "15 menit",
            prepTime = "10 menit",
            portion = 2
        ),
Recipe(
            recipeId = 87,
            name = "Spring Roll Ayam",
            calories = 119.0,
            carbs = 16.86,
            fat = 3.09,
            protein = 4.11,
            ingredients = "1 sedang rice paper\n" +
                    "10 gram daging dada ayam (ayam pedaging, dipanggang, dimasak)\n" +
                    "10 gram wortel\n" +
                    "10 gram mentimun (dengan kulit)\n" +
                    "15 gram bihun (dimasak)\n" +
                    "8 ml roasted sesame dressing\n" ,
            cookingSteps = "1. Rebus bihun, tiriskan.\n" +
                    "2. Potong sayur.\n" +
                    "3. Celup rice paper 2 detik di air.\n" +
                    "4. Siapkan dan gulung kertas nasi dengan bihun, ayam dan sayuran di dalamnya. Gunakan saus wijen sebagai saus.\n" ,
            recipePictures = "spring_roll_ayam",
            mealType = 2,
            cookTime = "8 menit",
            prepTime = "15 menit",
            portion = 1
        ),
Recipe(
            recipeId = 97,
            name = "Udang Nori",
            calories = 47.0,
            carbs = 1.61,
            fat = 2.56,
            protein = 4.17,
            ingredients = "70 gram udang\n" +
                    "1/2 sdt garam\n" +
                    "2 sdt minyak wijen\n" +
                    "20 gram wortel\n" +
                    "20 gram daun bawang\n" +
                    "1 takaran stevia sweetener\n" +
                    "1 bungkus rumput laut panggang (nori)\n" +
                    "1 porsi kaldu jamur\n" +
                    "1 takaran lada putih bubuk\n" ,
            cookingSteps = "1. Siapkan bahan, cincang udang dan bawang daun serta parut wortel dengan parutan keju.\n" +
                    "2. Masukan semua bahan kedalam wadah beri minyak jijen dan bumbu penyedap( garam, merica, kaldu jamur, pemanis stevia secukupnya ).\n" +
                    "3. Aduk hingga semua tercampur rata.\n" +
                    "4. Potong nori segi panjang menjadi beberapa bagian, disisi lain panaskan teflon dengan olesan sedikit minyak wijen.\n" +
                    "5. Taruh nori diatas teflon, lapisi dengan adonan hingga nori tertutup. Tutup teflon, Panggang dengan api yg sangat kecil.\n" +
                    "6. Jika sudah hampir matang, balikkan perlahan.\n" +
                    "7. Sajikan di piring dengan nasi dan sayuran pilihan.\n" ,
            recipePictures = "udang_nori",
            mealType = 3,
            cookTime = "25 menit",
            prepTime = "10 menit",
            portion = 5
        ),
Recipe(
            recipeId = 99,
            name = "Stir Fry Asparagus",
            calories = 22.0,
            carbs = 2.82,
            fat = 0.97,
            protein = 1.42,
            ingredients = "120 gram asparagus\n" +
                    "1 siung bawang putih\n" +
                    "2 ml minyak goreng tropical\n" ,
            cookingSteps = "1. Rebus asparagus hingga agak lunak.\n" +
                    "2. Tumis bawang putih dengan sedikit minyak.\n" +
                    "3. Masukkan asparagus yang telah direbus.\n" +
                    "4. Tumis asparagus dengan bawang putih dan tunggu hingga matang.\n" +
                    "5. Hidangan siap disantap.\n" ,
            recipePictures = "stir_fry_asparagus",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 101,
            name = "Pizza",
            calories = 144.0,
            carbs = 21.91,
            fat = 3.81,
            protein = 6.25,
            ingredients = "5 gram ragi\n" +
                    "1 sdm margarin\n" +
                    "1 sdm mayones ringan\n" +
                    "20 gram gula pasir\n" +
                    "100 gram bawang bombay\n" +
                    "1/2 cup spaghetti sauce\n" +
                    "150 g tepung segitiga biru\n" +
                    "3 porsi beef frank\n" +
                    "1 porsi sosis bratwurst\n" ,
            cookingSteps = "1. Campur air hangat dengan ragi dan gula.\n" +
                    "2. Tambahkan tepung terigu dan margarin. Aduk rata dan uleni adonan hingga kalis. Diamkan selama 1 jam hingga mengembang.\n" +
                    "3. Gulung adonan, taruh di atas loyang dan tusuk dengan garpu.\n" +
                    "4. Oleskan saus spageti. Tambahkan irisan sosis, daging sapi, mayones dan bawang bombay. Taburi dengan peterseli dan oregano, jika diinginkan.\n" +
                    "5. Diamkan selama 15 menit selagi oven dipanaskan terlebih dahulu.\n" +
                    "6. Panggang dalam oven selama 30 menit dengan suhu 140°C.\n" ,
            recipePictures = "pizza",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "1 Jam 30 Menit",
            portion = 8
        ),
Recipe(
            recipeId = 103,
            name = "Oseng Toge Tahu Telur",
            calories = 487.0,
            carbs = 29.12,
            fat = 29.7,
            protein = 29.79,
            ingredients = "118 gram telur\n" +
                    "65 gram kecambah\n" +
                    "5 gram cabai merah atau rawit\n" +
                    "20 gram bawang putih\n" +
                    "3 gram jahe\n" +
                    "1 sdm minyak goreng\n" +
                    "130 gram tahu\n" +
                    "5 g kaldu jamur\n" +
                    "1 sdm kecap manis\n" ,
            cookingSteps = "1. Potong dadu tahu, masukkan dalam wadah.\n" +
                    "2. Kocok lepas telur, tuang kedalam tahu.\n" +
                    "3. Panaskan minyak, tumis bawang putih, cabe rawit dan jahe hingga harum.\n" +
                    "4. Tuang tahu dan telur, aduk-aduk hingga telur matang.\n" +
                    "5. Masukkan toge, aduk aduk.\n" +
                    "6. Bumbui dengan kecap manis dan kaldu jamur, tambahkan air sedikit.\n" +
                    "7. Masak sampai bumbu meresap.\n" +
                    "8. Oseng toge tahu telur siap disajikan.\n" ,
            recipePictures = "oseng_toge_tahu_telur",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 104,
            name = "Tumis Pokcoy Saus Tiram",
            calories = 195.0,
            carbs = 22.77,
            fat = 11.94,
            protein = 3.13,
            ingredients = "30 gram cabai merah atau rawit\n" +
                    "100 gram saus tiram\n" +
                    "3 siung bawang putih\n" +
                    "5 kecil bawang merah\n" +
                    "3 sdm minyak goreng\n" +
                    "100 gram pakcoy\n" ,
            cookingSteps = "1. Cuci bersih pokcoy, potong potong sesuai selesai.\n" +
                    "2. Kupas bawang merah, bawang putih, dan cabai. Cuci bersih, lalu iris tipis dan memanjang untuk cabai.\n" +
                    "3. Panaskan minyak dalam wajan, masukan bawang merah dan bawang putih tunggu hingga harum, masukan cabai tunggu wangi.\n" +
                    "4. Masukan pokcoy aduk aduk.\n" +
                    "5. Masukan saus tiram, tambahkan air sedikit.\n" +
                    "6. Tunggu hingga matang, makanan siap disajikan.\n" ,
            recipePictures = "tumis_pokcoy_saus_tiram",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "8 Menit",
            portion = 3
        ),
Recipe(
            recipeId = 109,
            name = "Tumis Toge",
            calories = 38.0,
            carbs = 6.67,
            fat = 0.51,
            protein = 3.97,
            ingredients = "100 gram kecambah\n" +
                    "2 siung bawang putih\n" +
                    "1 takaran royco ayam\n" ,
            cookingSteps = "1. Iris bawang putih dan tumis di atas api yang panas tanpa minyak.\n" +
                    "2. Tambahkan taoge dan penyedap rasa sesuai selera. Campur dengan baik.\n" +
                    "3. Tumis hingga matang.\n" ,
            recipePictures = "tumis_toge",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 110,
            name = "Ampyang",
            calories = 132.0,
            carbs = 14.47,
            fat = 7.47,
            protein = 3.56,
            ingredients = "300 gram kacang sangrai kering (tanpa garam)\n" +
                    "166 gram gula merah\n" +
                    "63 gram gula pasir\n" ,
            cookingSteps = "1. Masukkan gula merah, gula pasir dan air ke dalam panci. Aduk hingga mengental dan gula larut.\n" +
                    "2. Buang kulit kacang panggang dan masukkan ke dalam wajan.\n" +
                    "3. Matikan api. Aduk hingga adonan ampyang benar-benar mengental dan matang.\n" +
                    "4. Ambil satu sendok makan campuran ampyang. Tuang di atas kertas lalu bentuk menjadi bentuk datar atau bulat. Lakukan hingga adonan habis.\n" +
                    "5. Biarkan dingin dan mengeras lalu simpan di toples kedap udara.\n" ,
            recipePictures = "ampyang",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "20 Menit",
            portion = 20
        ),
Recipe(
            recipeId = 120,
            name = "Tumis Timun dan Ayam",
            calories = 236.0,
            carbs = 8.93,
            fat = 17.43,
            protein = 10.16,
            ingredients = "1 sejumput garam\n" +
                    "50 gram daging paha dan kulit ayam (ayam pedaging)\n" +
                    "80 gram mentimun (dengan kulit)\n" +
                    "5 ml kecap manis\n" +
                    "1 sdm minyak kanola\n" +
                    "1/2 takaran royco ayam\n" +
                    "1 sejumput lada putih bubuk\n" ,
            cookingSteps = "1. Goreng ayam dalam minyak canola sampai berwarna cokelat keemasan.\n" +
                    "2. Tambahkan mentimun potong dadu dan bumbu.\n" +
                    "3. Aduk hingga harum dan merata.\n" +
                    "4. Pindahkan ke piring dan sajikan dengan nasi, opsional.\n" ,
            recipePictures = "tumis_timun_dan_ayam",
            mealType = 1,
            cookTime = "7 Menit",
            prepTime = "3 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 121,
            name = "Tumis Kangkung",
            calories = 58.0,
            carbs = 6.41,
            fat = 3.09,
            protein = 2.29,
            ingredients = "1/2 gram cabai merah atau rawit\n" +
                    "10 gram bawang putih\n" +
                    "9 gram bawang merah\n" +
                    "3 ml minyak goreng\n" +
                    "60 gram kangkung\n" ,
            cookingSteps = "1. Tumis bawang merah dan bawang putih smpai harum dalam minyak goreng.\n" +
                    "2. Masukan cabai dan kangkung.\n" +
                    "3. Tumis hingga layu.\n" ,
            recipePictures = "tumis_kangkung",
            mealType = 1,
            cookTime = "8 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 122,
            name = "Tumis Telur Tahu",
            calories = 278.0,
            carbs = 15.09,
            fat = 14.74,
            protein = 26.23,
            ingredients = "1 besar telur\n" +
                    "50 gram kecambah\n" +
                    "10 gram kaldu jamur\n" +
                    "10 gram daun bawang\n" +
                    "50 gram bayam\n" +
                    "50 gram tempe\n" +
                    "1 sdt bawang putih cincang\n" +
                    "70 gram tahu\n" +
                    "1 sdm sambal goreng\n" +
                    "2 g royco ayam\n" ,
            cookingSteps = "1. Tumis baput, bawang putih cincang dan saus sambal lalu masukkan potongan tempe dan tumis hingga harum.\n" +
                    "2. Kocok telur dan tahu.\n" +
                    "3. Tambahkan ke tumisan, lalu aduk hingga telur dan tahu sedikit menggumpal. Tambahkan sedikit air.\n" +
                    "4. Tambahkan tauge, daun bawang dan bayam lalu tambahkan lebih banyak air jika perlu.\n" +
                    "5. Tambahkan bumbu kaldu jamur dan sedikit royco. Tumis hingga semuanya matang.\n" +
                    "6. Angkat dan sajikan.\n" ,
            recipePictures = "tumis_telur_tahu",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 123,
            name = "Ayam Saos Tiram",
            calories = 669.0,
            carbs = 29.58,
            fat = 26.24,
            protein = 76.66,
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
                    "10 g lengkuas\n" ,
            cookingSteps = "1. Potong semua bahan.\n" +
                    "2. Rebus air hingga mendidih lalu masak ayam. Sisihkan saat Anda selesai.\n" +
                    "3. Dalam wajan yang sudah dipanaskan, tumis bawang bombay lalu tambahkan cabai dan lengkuas. Aduk dan tambahkan sisa bumbu dan rempah-rempah.\n" +
                    "4. Masukkan ayam dan tambahkan sedikit air.\n" +
                    "5. Tambahkan daun bawang cincang halus. Aduk rata.\n" ,
            recipePictures = "ayam_saos_tiram",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 132,
            name = "Tempe Kecap",
            calories = 489.0,
            carbs = 56.79,
            fat = 23.68,
            protein = 22.38,
            ingredients = "2 siung bawang putih dimasak\n" +
                    "3 sdm cabai merah atau rawit\n" +
                    "1 sdt garam\n" +
                    "100 gram tempe\n" +
                    "2 kecil bawang merah\n" +
                    "1 sdm minyak goreng\n" +
                    "2 sdm kecap manis\n" ,
            cookingSteps = "1. Kupas bawang merah, bawang putih bawang putih dan cincang halus bersama cabai\n" +
                    "2. Panaskan minyak. Tumis bumbu sampai harum\n" +
                    "3. Masukan air, garam secukupnya, dan kecap manis\n" +
                    "4. Masukan tempe yang udah dipotong dadu\n" +
                    "5. Aduk-aduk hingga air susut\n" ,
            recipePictures = "tempe_kecap",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 136,
            name = "Sup Tahu Brokoli",
            calories = 158.0,
            carbs = 8.89,
            fat = 9.32,
            protein = 12.05,
            ingredients = "1 besar telur\n" +
                    "130 gram brokoli\n" +
                    "1 siung bawang putih\n" +
                    "1/2 sdt gula pasir\n" +
                    "5 gram garam laut\n" +
                    "175 gram tahu\n" +
                    "3 g lada putih bubuk\n" +
                    "5 ml minyak goreng\n" ,
            cookingSteps = "1. Cuci bersih brokoli dan pot dg tahu.\n" +
                    "2. Tumis bawang putih dg minyak lalu masukkan air.\n" +
                    "3. Masukkan brokoli rebus masukkan bumbu lalu masuk lg tahu.\n" +
                    "4. Terakhir kocok telur dan tuang sdkit ke dlm panci rebusan.\n" +
                    "5. Aduk rata, siap dimakan.\n" ,
            recipePictures = "sup_tahu_brokoli",
            mealType = 2,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 138,
            name = "Sup Tofu Telur",
            calories = 503.0,
            carbs = 31.92,
            fat = 38.59,
            protein = 18.12,
            ingredients = "1 sedang telur\n" +
                    "8 gram kaldu jamur\n" +
                    "8 gram cabai merah atau rawit\n" +
                    "1/4 sdt garam\n" +
                    "1 sdm saus tiram\n" +
                    "2 siung bawang putih\n" +
                    "3 kecil bawang merah\n" +
                    "2 sdm minyak goreng\n" +
                    "180 g egg tofu\n" ,
            cookingSteps = "1. Potong tahu sesuai selera, lalu cincang bawang putih dan bawang merah.\n" +
                    "2. Panaskan minyak goreng dengan teflon, masukan bawang putih masak hingga tercium wanginya.\n" +
                    "3. Lalu masukan bawang merah sambil diaduk agar tidak gosong.\n" +
                    "4. Tambahkan air gelas.\n" +
                    "5. Masukan cabai, garam, kaldu jamur dan saus tiram.\n" +
                    "6. Lalu masukan telur, orak arik terus.\n" +
                    "7. Kemudian masukan tofu, diamkan hingga mendidih atau sudah terlihat matang.\n" ,
            recipePictures = "sup_tofu_telur",
            mealType = 3,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 140,
            name = "Tempe Goreng",
            calories = 25.0,
            carbs = 1.3,
            fat = 1.35,
            protein = 2.34,
            ingredients = "1 sdt garam\n" +
                    "1 siung bawang putih\n" +
                    "100 gram tempe\n" ,
            cookingSteps = "1. Iris tempe tipis tipis.\n" +
                    "2. Beri bumbu bawang putih dan garam.\n" +
                    "3. Lumuri bumbu dan masukkan ke air fryer dengan suhu 200°C waktu 20 menit.\n" ,
            recipePictures = "tempe_goreng",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "5 Menit",
            portion = 8
        ),
Recipe(
            recipeId = 147,
            name = "Sup Telur",
            calories = 187.0,
            carbs = 10.99,
            fat = 10.95,
            protein = 12.24,
            ingredients = "1 besar telur\n" +
                    "1 mangkok, segar sawi dimasak (dari segar)\n" +
                    "25 gram kaldu jamur\n" +
                    "1 sdt garam\n" +
                    "25 g sosis ayam\n" +
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Kocok telur dan campurkan garam.\n" +
                    "2. Didihkan air dan jika air sudah mendidih masukkan telur kocok.\n" +
                    "3. Kemudian masukkan sosis dan sawi bersama kaldu dan merica.\n" ,
            recipePictures = "sup_telur",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 158,
            name = "Ayam Brokoli Tumis",
            calories = 347.0,
            carbs = 14.47,
            fat = 20.58,
            protein = 27.81,
            ingredients = "80 gram dada ayam\n" +
                    "1 mangkok, segar, potongan batang brokoli dimasak (dari segar)\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sdt garam\n" +
                    "1 siung bawang putih\n" +
                    "20 g saus tiram\n" +
                    "1 sdm minyak goreng\n" ,
            cookingSteps = "1. Potong ayam dadu, lalu goreng ayam sampai kering.\n" +
                    "2. Tumis bawang putih, brokoli, dan masuk ayam.\n" +
                    "3. Tambah air, saus tiram, dan bumbu.\n" +
                    "4. Aduk sampai rata.\n" +
                    "5. Sajikan.\n" ,
            recipePictures = "ayam_brokoli_tumis",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 162,
            name = "Sup Tomat",
            calories = 206.0,
            carbs = 11.35,
            fat = 10.18,
            protein = 20.61,
            ingredients = "100 gram tomat\n" +
                    "1 besar putih telur\n" +
                    "1 siung bawang putih\n" +
                    "1 gelas air\n" +
                    "200 gram tahu\n" +
                    "115 g royco kaldu rasa sapi\n" +
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Potong-potong tomat dan bawang putih lalu blender dengan air sampai halus.\n" +
                    "2. Masak sampai mendidih lalu masukkan tahu yang sudah dipotong dadu.\n" +
                    "3. Setelah kuah berkurang masukkan putih telur.\n" +
                    "4. Tambahkan royco dan lada bubuk, tes rasa hingga sesuai yang diinginkan.\n" +
                    "5. Setelah matang tambahkan daun bawang dam hidangkan.\n" ,
            recipePictures = "sup_tomat",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 163,
            name = "Sup Labu Telur",
            calories = 166.0,
            carbs = 14.08,
            fat = 7.74,
            protein = 8.24,
            ingredients = "2 sedang telur\n" +
                    "1 sdt garam\n" +
                    "2 siung bawang putih\n" +
                    "160 gram labu\n" +
                    "1 gelas air\n" +
                    "5 ml minyak goreng\n" +
                    "40 gram tahu\n" +
                    "15 g bihun jagung\n" +
                    "4 g bumbu penyedap rasa sapi\n" ,
            cookingSteps = "1. Rebus bihun hingga matang, sisihkan.\n" +
                    "2. Kupas dan bersihkan labu potong dadu, sisihkan.\n" +
                    "3. Tumis bawang putih, masukkan telur dan aduk hingga matang.\n" +
                    "4. Tambahkan air, masukkan lahu dan tahu.\n" ,
            recipePictures = "sup_labu_telur",
            mealType = 2,
            cookTime = "30 Menit",
            prepTime = "15 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 176,
            name = "Dada Ayam Paprika",
            calories = 272.0,
            carbs = 12.69,
            fat = 7.37,
            protein = 38.55,
            ingredients = "75 gram dada ayam\n" +
                    "50 gram udang\n" +
                    "1 sejumput lada hitam\n" +
                    "1 sejumput garam\n" +
                    "1 sdm saus tiram\n" +
                    "50 gram brokoli\n" +
                    "30 gram wortel\n" +
                    "40 gram paprika kuning manis\n" +
                    "150 ml air\n" +
                    "1 sdt bawang putih cincang\n" ,
            cookingSteps = "1. Masukan air tunggu sampai mendidih.\n" +
                    "2. Masukan bawang putih cincang.\n" +
                    "3. Masukan dada ayam, aduk sampai mendidih.\n" +
                    "4. Masukan sayuran dan bumbu lainya.\n" +
                    "5. Aduk sampai matang.\n" +
                    "6. Sajikan dengan taburan daun petersely.\n" ,
            recipePictures = "dada_ayam_paprika",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "15 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 182,
            name = "Poires Helena",
            calories = 388.0,
            carbs = 68.87,
            fat = 12.76,
            protein = 5.32,
            ingredients = "11 gram kayu manis\n" +
                    "3 sedang buah pir\n" +
                    "50 gram kacang almond\n" +
                    "2 1/2 gelas air\n" +
                    "90 gram gula pasir\n" +
                    "15 g saus manis cokelat pisang\n" +
                    "100 ml ice cream cokelat chip\n" ,
            cookingSteps = "1. Masukkan gula pasir dan kayu manis, tambahkan 1/2 gelas air.\n" +
                    "2. Aduk, kemudian tambahkan 2 gelas air dan rebus 3 buah pir.\n" +
                    "3. Tiriskan, plating buah pir kemudian tambahkan 2 scoops ice cream, tambahkan saus coklat dan beri taburan kacang almond.\n" ,
            recipePictures = "poires_helena",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 3
        ),
Recipe(
            recipeId = 183,
            name = "Bayam Jamur",
            calories = 83.0,
            carbs = 10.44,
            fat = 2.04,
            protein = 5.93,
            ingredients = "1 kecil telur\n" +
                    "15 gram bawang putih\n" +
                    "80 gram bayam\n" +
                    "12 gram bawang merah\n" +
                    "103 g jamur enoki\n" +
                    "60 ml non fat milk\n" ,
            cookingSteps = "1. Iris halus bawang merah dan putih.\n" +
                    "2. Tumis bawang merah dan putih sampai kecoklatan.\n" +
                    "3. Masukan bayam yang sudah dicuci, aduk sebentar, tambahkan 50ml air matang.\n" +
                    "4. Masukan telur.\n" +
                    "5. Aduk2 sebentar sampai telur tercampur dan bayam layu.\n" +
                    "6. Masukan jamur enoki yg sdh dicuci bersih.\n" +
                    "7. Tambahkan susu non fat.\n" +
                    "8. Diamkan sebentar kemudian angkat.\n" ,
            recipePictures = "bayam_jamur",
            mealType = 3,
            cookTime = "5 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 184,
            name = "Gyoza",
            calories = 49.0,
            carbs = 4.86,
            fat = 1.62,
            protein = 3.73,
            ingredients = "200 gram dada ayam\n" +
                    "20 kecil pangsit polos\n" +
                    "1 sdt minyak wijen\n" +
                    "1 sdm saus tiram\n" +
                    "100 gram kubis\n" +
                    "2 siung bawang putih\n" +
                    "1 mangkok daun bawang\n" +
                    "1 sejumput garam laut\n" +
                    "10 g tepung tapioka\n" ,
            cookingSteps = "1. Haluskan Dada ayam , tapioka , 1 butir telur , kubis , ke dalam chopper sampai halus jangan lupa di beri bumbu.\n" +
                    "2. Isi kulit pangsit dengan isian gyoza.\n" +
                    "3. Kukus di atas teflon selama 30 menit dengan sedikit air sambil ditutup.\n" +
                    "4. 10 anggota telah menambahkan resep ini ke buku masak mereka\n" ,
            recipePictures = "gyoza",
            mealType = 2,
            cookTime = "30 Menit",
            prepTime = "30 Menit",
            portion = 20
        ),
Recipe(
            recipeId = 189,
            name = "Bola Ubi Jalar",
            calories = 15.0,
            carbs = 2.86,
            fat = 0.35,
            protein = 0.29,
            ingredients = "58 gram nanas\n" +
                    "377 gram ubi jalar (manis)\n" +
                    "18 gram biji wijen kering utuh\n" +
                    "2 sdt fiber creme\n" ,
            cookingSteps = "1. Kukus ubi hingga matang.\n" +
                    "2. Hancurkan ubi, tambahkan fiber creme.\n" +
                    "3. Bentuk ubi dan isi dengan nanas.\n" +
                    "4. Lapisi dengan biji wijen.\n" ,
            recipePictures = "bola_ubi_jalar",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "15 Menit",
            portion = 32
        ),
Recipe(
            recipeId = 193,
            name = "Bakso dan Sup Telur",
            calories = 362.0,
            carbs = 27.84,
            fat = 14.38,
            protein = 30.02,
            ingredients = "1/2 slice soft silken tofu\n" +
                    "45 g bakso sapi\n" +
                    "250 ml air mineral\n" +
                    "50 g fish roll\n" +
                    "40 g telur ayam kampung\n" +
                    "48 g crab stick\n" ,
            cookingSteps = "1. Didihkan air.\n" +
                    "2. Setelah didih masukan telur.\n" +
                    "3. Lalu di aduk sebentar dan masukan semua bahan.\n" +
                    "4. Tambahkan sedikit penyedap,gula,bawang putih bubuk,dan lada hitam.\n" ,
            recipePictures = "bakso_dan_sup_telur",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "2 Menit",
            portion = 1
        ),
Recipe(
            recipeId = 196,
            name = "Martabak Tofu",
            calories = 36.0,
            carbs = 2.2,
            fat = 1.87,
            protein = 3.17,
            ingredients = "1 tsp low sodium, dry beef broth, bouillon or consomme,\n" +
                    "1 besar egg\n" +
                    "1 medium carrots\n" +
                    "1 tsp white pepper\n" +
                    "2 sejumput salt\n" +
                    "1/2 sdm oyster sauce\n" +
                    "2 siung garlic\n" +
                    "1 mangkok, dicincang scallions or spring onions\n" +
                    "3/4 block regular tofu (with calcium sulfate)\n" ,
            cookingSteps = "1. Hancurkan tahu, parut wortel dan potong daun bawang.\n" +
                    "2. Campurkan semua bahan dan telur aduk rata dan tambahkan bumbu-bumbu.\n" +
                    "3. Siapkan pan panas lalu kecilkan api dan panggang bertahap.\n" +
                    "4. 6 anggota telah menambahkan resep ini ke buku masak mereka\n" ,
            recipePictures = "martabak_tofu",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "5 Menit",
            portion = 12
        ),
Recipe(
            recipeId = 201,
            name = "Lumpia Pisang Aroma",
            calories = 329.0,
            carbs = 60.68,
            fat = 7.12,
            protein = 8.63,
            ingredients = "15 gram keju cheddar\n" +
                    "4 sedang pisang\n" +
                    "20 gram gula pasir\n" +
                    "1 mangkok saus cokelat\n" +
                    "16 sedang pangsit polos\n" ,
            cookingSteps = "1. Siapkan bahan dan lelehakan coklat.\n" +
                    "2. Masukkan pisang keju gula pasir ke kulit pangsit lalu gulung.\n" +
                    "3. Goreng.\n" +
                    "4. Tambahkan lelehan coklat dan keju diatas nya.\n" ,
            recipePictures = "lumpia_pisang_aroma",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 204,
            name = "Onde-onde Panggang",
            calories = 62.0,
            carbs = 8.52,
            fat = 2.92,
            protein = 1.63,
            ingredients = "1 sdm selai kacang\n" +
                    "5 sdm biji wijen kering utuh\n" +
                    "7,8 g stevia sweetener\n" +
                    "5 ml fiber creme\n" +
                    "40 g tepung beras\n" +
                    "60 g tepung tapioka\n" +
                    "170 gram ubi ungu rebus\n" ,
            cookingSteps = "1. Kukus ubi lalu haluskan, setelah halus Ini dengan fiber crime, selai kacang dan gula stevia. Aduk sampai rata.\n" +
                    "2. Campur tepung beras dan tapioka dengan air panas 4sdm, aduk sampai kalis diamkan 10 menit . Seharusnya tepung ketan putih karena gak punya pakai yg ada.\n" +
                    "3. Bagi menjadi 12 bagian tepungnya, masukan isian ubinya bulat\" dan gepengkan. Lalu panggang selama 1 jam di suhu 120°c.\n" +
                    "4. Onde-onde ubi panggang siap disajikan.\n" ,
            recipePictures = "onde-onde_panggang",
            mealType = 1,
            cookTime = "1 Jam",
            prepTime = "30 Menit",
            portion = 12
        ),
Recipe(
            recipeId = 206,
            name = "Sup Telur Jagung",
            calories = 216.0,
            carbs = 33.0,
            fat = 7.41,
            protein = 8.48,
            ingredients = "1 irisan tipis ayam\n" +
                    "1 besar telur rebus\n" +
                    "100 gram wortel dimasak\n" +
                    "100 gram bawang bombay dimasak\n" +
                    "1 sdt garam\n" +
                    "1 mangkok jagung manis kuning\n" +
                    "1 mangkok daun bawang\n" +
                    "1 sdt bawang putih cincang\n" +
                    "600 ml air mineral\n" ,
            cookingSteps = "1. Tumis bawang putih & bombay.\n" +
                    "2. Jika sudah harum masukan kurleb air.\n" +
                    "3. Masukan ayam, wortel, jagung , diamkan kurleb 20menit.\n" +
                    "4. Lalu kocok telur lalu masukan, setelah itu masukan jg daun bawang.\n" +
                    "5. Tambahkan garam dan merica setelah itu dicicip.\n" +
                    "6. Sudah biss dihidangkan.\n" ,
            recipePictures = "sup_telur_jagung",
            mealType = 2,
            cookTime = "30 Menit",
            prepTime = "25 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 208,
            name = "Perdekel Tahu Bawang",
            calories = 42.0,
            carbs = 1.22,
            fat = 3.44,
            protein = 2.4,
            ingredients = "1 besar telur\n" +
                    "10 gram bawang putih\n" +
                    "10 gram daun bawang\n" +
                    "15 gram bawang merah\n" +
                    "150 gram tahu\n" +
                    "1 sdm virgin coconut oil\n" ,
            cookingSteps = "1. Iris bawang-bawangan, kemudian panggang hingga harum.\n" +
                    "2. Hancurkan tahu, tambahkan bawang-bawangan.\n" +
                    "3. Masukan telur ke dalam adonan tahu.\n" +
                    "4. Tambahkan garam, merica, dan kaldu jamur.\n" +
                    "5. Panaskan 1sdm vco di teflon anti lengket.\n" +
                    "6. Mulai dry frying adonan, kurleb 1 sdm adonan setiap goreng perkedel.\n" +
                    "7. Tunggu hingga ke coklatan, dan angkat. Perkedel siap dinikmati.\n" ,
            recipePictures = "perdekel_tahu_bawang",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 8
        ),
Recipe(
            recipeId = 211,
            name = "Kare Capit Kepiting",
            calories = 205.0,
            carbs = 13.2,
            fat = 5.37,
            protein = 26.78,
            ingredients = "500 gram kepiting\n" +
                    "1/4 ikat ketumbar\n" +
                    "1 sdt bubuk kari\n" +
                    "20 gram cabai merah atau rawit\n" +
                    "3 siung bawang putih\n" +
                    "5 kecil bawang merah\n" +
                    "5,50 ml minyak goreng\n" +
                    "15 ml sun kara\n" ,
            cookingSteps = "1. Tumis bumbu halus hingga wangi.\n" +
                    "2. Masukkan air secukupnya.\n" +
                    "3. Masukkan kara.\n" +
                    "4. Masukkan kepiting, garam dan kaldu bubuk, tambahkan cabe rawit utuh.\n" +
                    "5. Tes rasa.\n" ,
            recipePictures = "kare_capit_kepiting",
            mealType = 1,
            cookTime = "20 Menit",
            prepTime = "20 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 214,
            name = "Ayam Tempe Penyet",
            calories = 458.0,
            carbs = 31.36,
            fat = 18.36,
            protein = 47.85,
            ingredients = "210 gram dada ayam\n" +
                    "30 gram cabai merah atau rawit\n" +
                    "1/2 sdt garam\n" +
                    "4 siung bawang putih\n" +
                    "138 gram tempe\n" +
                    "4 kecil bawang merah\n" +
                    "5 g terasi\n" ,
            cookingSteps = "1. Ulek semua bumbu atau blender ditambahkan air.\n" +
                    "2. Setelah halus masukan ke panci, masukan ayam dan tempe, dan air.\n" +
                    "3. Tambah garam. Dan biarkan air menyusut.\n" +
                    "4. Hidangkan..\n" ,
            recipePictures = "ayam_tempe_penyet",
            mealType = 2,
            cookTime = "30 Menit",
            prepTime = "5 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 223,
            name = "Sawi Gulung",
            calories = 44.0,
            carbs = 1.62,
            fat = 2.54,
            protein = 4.1,
            ingredients = "2 besar telur\n" +
                    "5 gram kaldu jamur\n" +
                    "200 gram tahu\n" +
                    "250 gram sawi hijau\n" +
                    "1/2 tbsp saus tiram\n" +
                    "Cara memasak\n" ,
            cookingSteps = "1. Rebus lembaran sawi sampai layu, kemudian rebus 1 buah telur, kemudian potong mnjadi 8 bagian.\n" +
                    "2. Hancurkan tahu.\n" +
                    "3. Masukkan telur mentah, saus tiram dan kaldu jamur.\n" +
                    "4. Ambil selembar sawi, masukan adonan tahu dan telur rebus, gulung seperti menggulung risoles.\n" +
                    "5. Lakukan sampai adonan tahu habis.\n" +
                    "6. Kukus kurang lebih 10 menit.\n" ,
            recipePictures = "sawi_gulung",
            mealType = 3,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 8
        ),
Recipe(
            recipeId = 225,
            name = "Dragon Fruit Jelly",
            calories = 132.0,
            carbs = 32.45,
            fat = 0.95,
            protein = 1.95,
            ingredients = "2 gelas air\n" +
                    "500 g buah naga\n" +
                    "3,75 g jelly powder anggur\n" ,
            cookingSteps = "1. Rebus nutrijell rasa apa pun dalam 2 gelas air. Rebus hingga mendidih .. Setelah masak, diamkan 3 menit, lalu tambahkan asam buah dan biarkan dingin.\n" +
                    "2. Belah buah naga menjadi dua, keluarkan isinya lalu potong dadu, usahakan kulit buah naga masih tersisa.\n" +
                    "3. Potong Nutrijell dingin menjadi kubus, lalu sajikan dengan irisan buah naga di atas kulit buah naga. Selamat menikmati.\n" ,
            recipePictures = "dragon_fruit_jelly",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 226,
            name = "Dimsum Diet",
            calories = 52.0,
            carbs = 5.9,
            fat = 1.53,
            protein = 3.67,
            ingredients = "100 gram dada ayam\n" +
                    "15 kecil pangsit polos\n" +
                    "1 besar putih telur\n" +
                    "1 sdt garam\n" +
                    "50 gram wortel\n" +
                    "3 siung bawang putih\n" +
                    "3 kecil bawang merah\n" +
                    "125 gram tahu\n" +
                    "230 g royco kaldu rasa sapi\n" +
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Perajang / haluskan bawang putih, bawang bombay, dada ayam, tahu, garam, merica, putih telur, dan kaldu.\n" +
                    "2. Parut semua wortel dengan parutan rujak / potong korek api tipis2.\n" +
                    "3. Isi kulit pangsit dengan isian ayam tahu yg sudah di haluskan.. Kemudian kukus sampai matang kurang lebih 15 menit . Siap di santap dan di nikmati.\n" ,
            recipePictures = "dimsum_diet",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "20 Menit",
            portion = 15
        ),
Recipe(
            recipeId = 239,
            name = "Klapertart",
            calories = 176.0,
            carbs = 21.01,
            fat = 9.37,
            protein = 3.29,
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
                    "60 g tepung pati jagung\n" ,
            cookingSteps = "1. Campur susu, air kelapa, maizena, vanila, garam, kuning telur, gula. Aduk hingga mengental.\n" +
                    "2. Tambah blue band, kelapa muda kismis.\n" +
                    "3. Letakan di 3/4 wadahnya.\n" +
                    "4. Kocok putih telur dengan gula pasir.\n" +
                    "5. Letakkan diatas adonan pertama.\n" +
                    "6. Panggang dengan suhu 170 derajat selama 20menit.\n" ,
            recipePictures = "klapertart",
            mealType = 2,
            cookTime = "20 Menit",
            prepTime = "10 Menit",
            portion = 12
        ),
Recipe(
            recipeId = 268,
            name = "Agar-agar Kukus Coklat",
            calories = 113.0,
            carbs = 16.01,
            fat = 4.36,
            protein = 2.42,
            ingredients = "1 besar telur (utuh)\n" +
                    "10 gram kacang sangrai kering (dengan garam)\n" +
                    "25 gram gula pasir\n" +
                    "7 g agar agar powder\n" +
                    "35 g white coffee less sugar\n" +
                    "9 g agarasa coklat\n" ,
            cookingSteps = "1. Kocok telur sampai mengembang.\n" +
                    "2. Tuang bubuk agarasa, agar2putih dan bubuk kopi. Aduk rata.\n" +
                    "3. Panaskan kukusan.\n" +
                    "4. Masukkan kedalam cetakan tambahkan kacang secukupnya.\n" +
                    "5. Kukus 10-15menit. Angkat.\n" ,
            recipePictures = "agar-agar_kukus_coklat",
            mealType = 1,
            cookTime = "10 Menit",
            prepTime = "5 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 270,
            name = "Sup Daging Tahu",
            calories = 269.0,
            carbs = 22.49,
            fat = 14.19,
            protein = 14.42,
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
                    "60 gram bawang bombay\n" ,
            cookingSteps = "1. Daging sapi sebelumnya sudah direbus terlebih dahulu.\n" +
                    "2. Halus lada, jintan, jahe, bawang putih dan bawang merah.\n" +
                    "3. Tumis bumbu halus, tambahkan bawang Bombay yang sudah di potong-potong, setelah itu tambahkan air biarkan sampai mendidih.\n" +
                    "4. Tambah semua bahan, test rasa.\n" +
                    "5. Masak sampai mendidih.\n" ,
            recipePictures = "sup_daging_tahu",
            mealType = 3,
            cookTime = "20 Menit",
            prepTime = "15 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 271,
            name = "Soto Ayam",
            calories = 121.0,
            carbs = 16.25,
            fat = 1.91,
            protein = 10.64,
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
                    "3 g lada putih bubuk\n" ,
            cookingSteps = "1. Haluskan bawmer, bawput, jahe, kemiri, kunyit dan geprek laos dan sereh.\n" +
                    "2. Tumis bumbu halus tadi bersama dgn laos dan sereh yg sudah di geprek dan tambah duan salam dan daun jeruk.\n" +
                    "3. Setelah matang, masukan air dan air sisa rebusan ayam.\n" +
                    "4. Rebus ayam , tauge, mie shirataki Secara terpisah.\n" +
                    "5. Setelah air mendidih , masukan bumbu spt garam, lada bubuk, royko dan kecap manis 1sdm Setelah di rasa pas, matikan api kompor.\n" +
                    "6. Siapkan ayam suir, tauge, kol di iris , jeruk nipis , dan mi shirataki nya.\n" +
                    "7. Kalo mau pake sambel, tinggal haluskan rawit dan juga air kuah sotonya.\n" +
                    "8. Selesai.\n" ,
            recipePictures = "soto_ayam",
            mealType = 2,
            cookTime = "10 Menit",
            prepTime = "25 Menit",
            portion = 6
        ),
Recipe(
            recipeId = 278,
            name = "Sup Bening",
            calories = 162.0,
            carbs = 22.7,
            fat = 5.52,
            protein = 5.86,
            ingredients = "1 besar boiled egg\n" +
                    "1/4 mangkok, dicincang broccoli\n" +
                    "1/2 sdt minced garlic\n" +
                    "1/4 scallops\n" +
                    "1/4 ons cabbage (with salt, drained, cooked, boiled)\n" +
                    "1/2 kecil cooked carrots\n" +
                    "1 medium boiled potato\n" ,
            cookingSteps = "1. Potong semua bahan.\n" +
                    "2. Rebus air hingga mendidih lalu masukkan bawang merah dan bawang putih.\n" +
                    "3. Setelah 3 menit masukkan kentang dan wortel.\n" +
                    "4. Setelah wortel dan kentang setengah matang masukkan brokoli dan kol.\n" +
                    "5. Taburkan sedikit garam. Menyajikan.\n" ,
            recipePictures = "sup_bening",
            mealType = 1,
            cookTime = "15 Menit",
            prepTime = "10 Menit",
            portion = 2
        ),
Recipe(
            recipeId = 288,
            name = "Puding Chia",
            calories = 76.0,
            carbs = 13.64,
            fat = 0.7,
            protein = 4.54,
            ingredients = "8 lengkeng\n" +
                    "50 gram mangga\n" +
                    "8 gram chia seed\n" +
                    "3,5 g agar agar powder\n" +
                    "500 ml skimmed milk (250ml)\n" +
                    "2 sdm brown sugar\n" +
                    "4 sedang stroberi\n" ,
            cookingSteps = "1. Membuat puding chia seed: campur chia seed dengan air atau susu lalu aduk rata dan simpan di lemari es. Diamkan 1 jam kemudian biji chia akan mengembang. Just adjust the water/milk according to the chia seeds.\n" +
                    "2. Sambil menunggu puding chia dingin, sesuaikan air/susu sesuai dengan biji chia. Tuang susu, gelatin, dan gula merah ke dalam panci.\n" +
                    "3. Masak dengan api kecil sambil diaduk2 sampai mendidih.\n" +
                    "4. Setelah mendidih tuang ketempat yg telah disediakan.\n" +
                    "5. Tunggu hingga puding set beri puding chia seed dan buah2an yang sudah dipotong2 lalu simpan dikulkas.\n" ,
            recipePictures = "puding_chia",
            mealType = 1,
            cookTime = "30 Menit",
            prepTime = "5 Menit",
            portion = 4
        ),
Recipe(
            recipeId = 305,
            name = "Protein Bar Kacang Tanah",
            calories = 72.0,
            carbs = 8.99,
            fat = 3.63,
            protein = 2.2,
            ingredients = "100 gram roasted unsalted peanuts\n" +
                    "25 gram raisins\n" +
                    "75 gram honey\n" +
                    "50 g instant oatmeal\n" ,
            cookingSteps = "1. Giling kacang goreng.\n" +
                    "2. Campurkan semua bahan dan kacau sehingga menjadi doh.\n" +
                    "3. Masukkan ke dalam cetakan/tutup dengan plastik cling dan potong menjadi batangan.\n" +
                    "4. Dinginkan di lemari es.\n" ,
            recipePictures = "protein_bar_kacang_tanah",
            mealType = 1,
            cookTime = "0 menit",
            prepTime = "15 Menit",
            portion = 15
        ),
)


   
    val weightEntries = listOf(
        WeightEntry(
            id = 1,
            weight = 80,
            date = Date(System.currentTimeMillis() + (86400000 * 7)),
            userId = 1,
        ),
        WeightEntry(
            id = 2,
            weight = 81,
            date = Date(System.currentTimeMillis() + (86400000 * 14)),
            userId = 1,
        ),
        WeightEntry(
            id = 3,
            weight = 78,
            date = Date(System.currentTimeMillis() + (86400000 * 21)),
            userId = 1,
        ),
        WeightEntry(
            id = 4,
            weight = 83,
            date = Date(System.currentTimeMillis() + (2419200000)),
            userId = 1,
        ),
    )

    val recommendations = listOf(
        Recommendation(
            recommendationId = 1,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 3,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 2,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 10,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 3,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 15,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 4,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 20,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 5,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 24,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 6,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 19,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 7,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 19,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 8,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 14,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 9,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 3,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 10,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 10,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 11,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 15,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 12,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 20,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 13,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 24,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 14,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 19,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 15,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 19,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 16,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 14,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 17,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 22,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 18,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 25,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 19,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 26,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 20,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 29,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 21,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 3,
            recipeId = 31,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 22,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 3,
            recipeId = 7,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 23,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 100,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 24,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 3,
            recipeId = 271,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 25,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 1000,
            userId = 1,
        ),
        Recommendation(
            recommendationId = 26,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 3,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 27,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 10,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 28,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 15,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 29,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 20,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 30,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 24,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 32,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 19,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 33,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 14,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 34,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 3,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 35,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 10,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 36,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 15,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 37,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 20,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 38,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 24,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 39,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 19,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 40,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 19,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 41,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 14,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 42,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 22,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 43,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 25,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 44,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 26,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 45,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 29,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 46,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 3,
            recipeId = 31,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 47,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 3,
            recipeId = 7,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 48,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 100,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 49,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 3,
            recipeId = 271,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 50,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 1000,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 50,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 305,
            userId = 2,
        ),
        Recommendation(
            recommendationId = 50,
            date = Converters().longToString(System.currentTimeMillis()),
            isSelected = 0,
            recipeId = 305,
            userId = 1,
        ),
    )
}
