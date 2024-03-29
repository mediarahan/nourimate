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
        val recipePictures: String
    )

    data class Recommendation(
        val recommendationId: Int,
        val date: Date,
        val isSelected: Boolean,
        val mealType: Int,
        val recipeId: Int
    )

    val recipes = listOf(
        Recipe(
            recipeId = 1,
            name = "Omelet Sayur",
            calories = 200.0,
            carbs = 8.0,
            fat = 15.0,
            protein = 12.0,
            ingredients = "2 telur\n" +
                    "½ cangkir tomat cincang\n" +
                    "¼ cangkir paprika cincang\n" +
                    "¼ cangkir bayam\n" +
                    "1 sendok teh minyak zaitun\n" +
                    "Garam dan lada hitam secukupnya",
            cookingSteps = "1. Kocok telur bersama-sama dalam wadah. Bumbui dengan garam dan lada hitam.\n" +
                    "2. Panaskan minyak zaitun dalam wajan dengan api sedang. Tambahkan tomat, paprika, dan bayam. Tumis hingga layu, sekitar 2 menit.\n" +
                    "3. Tuangkan campuran telur ke dalam wajan. Miringkan wajan untuk menyebarkan telur secara merata.\n" +
                    "4. Setelah pinggirannya mulai mengeras, lipat omelet menjadi dua bagian dengan lembut. Masak selama satu atau dua menit lagi, atau sampai kematangan yang diinginkan.\n" +
                    "5. Sajikan segera.",
            recipePictures = "drawable/vegetable_omelette"
        ),
        Recipe(
            recipeId = 2,
            name = "Tumis Ayam",
            calories = 350.0,
            carbs = 20.0,
            fat = 18.0,
            protein = 25.0,
            ingredients = "1 cup nasi\n" +
                    "200g dada ayam, potong dadu\n" +
                    "1/2 cup brokoli\n" +
                    "1/4 cup wortel, iris\n" +
                    "2 sdm kecap asin\n" +
                    "1 sdm minyak wijen\n" +
                    "Garam dan lada hitam secukupnya",
            cookingSteps = "Masak nasi sesuai petunjuk kemasan." +
                    "Dalam wajan, panaskan minyak wijen dengan api sedang." +
                    "Masukkan ayam dan masak sampai kecokelatan." +
                    "Tambahkan sayuran dan kecap asin, aduk sampai sayuran empuk. " +
                    "Sajikan di atas nasi.",
            recipePictures = "drawable/chicken_stir_fry"

        ),
        Recipe(
            recipeId = 3,
            name = "Overnight Oats",
            calories = 300.0,
            carbs = 25.0,
            fat = 12.0,
            protein = 20.0,
            ingredients = "1 cup oat\n1 cup susu almond\n1 pisang, iris tipis\n1/4 cup almond, cincang\n1 sendok makan madu\n1 sendok teh kayu manis",
            cookingSteps = "Dalam wadah, campurkan oat, susu almond, irisan pisang, almond cincang, madu, dan kayu manis.\n" +
                    "Aduk rata hingga semua bahan tercampur.\n" +
                    "Tutup wadah dan simpan dalam kulkas semalaman.\n" +
                    "Di pagi hari, keluarkan oat dan nikmati dalam keadaan dingin. Jika Anda lebih suka hangat, panaskan di microwave selama satu atau dua menit.",
            recipePictures = "drawable/overnight_oats"
        ),
        Recipe(
            recipeId = 4,
            name = "Penne Bolognese",
            calories = 400.0,
            carbs = 30.0,
            fat = 22.0,
            protein = 28.0,
            ingredients = "200g penne pasta\n" +
                    "1/2 cup saus marinara\n" +
                    "200g daging sapi giling\n" +
                    "1/4 cup bawang bombay, cincang\n" +
                    "1/4 cup paprika, cincang\n" +
                    "1/4 cup keju Parmesan parut",
            cookingSteps = "Masak pasta sesuai petunjuk kemasan.\n" +
                    "Panaskan wajan dan tumis daging sapi giling dengan bawang bombay dan paprika hingga daging kecokelatan.\n" +
                    "Tambahkan saus marinara dan pasta yang sudah matang, aduk hingga tercampur rata dan saus menjadi panas.\n" +
                    "Sajikan dengan taburan keju Parmesan parut.",
            recipePictures = "drawable/penne_bolognese"
        ),
        Recipe(
            recipeId = 5,
            name = "Roti Lapis Kalkun",
            calories = 250.0,
            carbs = 15.0,
            fat = 10.0,
            protein = 18.0,
            ingredients = "2 lembar roti gandum utuh\n" +
                    "100g dada kalkun\n" +
                    "1/4 buah alpukat, iris tipis\n" +
                    "1/4 cup selada\n" +
                    "1 sdm mayones\n" +
                    "1 sdt mustard\n" +
                    "Garam dan lada hitam secukupnya",
            cookingSteps = "Oleskan mayones dan mustard pada kedua lembar roti.\n" +
                    "Susun dada kalkun, alpukat, dan selada di atas salah satu lembar roti. Bumbui dengan garam dan lada hitam.\n" +
                    "Tutup dengan lembar roti lainnya. Potong menjadi dua bagian dan sajikan.",
            recipePictures = "drawable/turkey_avocado_sandwich"
        ),
        Recipe(
            recipeId = 6,
            name = "Roti Pisang Oat",
            calories = 193.0,
            carbs = 29.77,
            fat = 6.93,
            protein = 4.28,
            ingredients = "2 besar telur\n2 besar pisang\n1 tsp baking powder (sodium aluminum sulfate, double acting)\n6 sdm honey\n170 g quick cooking oatmeal\n54 g roasted almond\n100 g choco chips",
            cookingSteps = "Panaskan oven terlebih dahulu pada suhu 170°C.\nHancurkan pisang, campur dengan telur dan madu.\nGiling oatmeal dan campur dengan baking powder.\nAduk bahan basah dan kering hingga rata, tambahkan coklat keping tapi sisakan sedikit untuk topping.\nTuang ke dalam loyang dan taburi dengan sisa choco chips dan almond. Panggang selama 40 menit atau sampai matang.",
            recipePictures = "drawable/roti_pisang_oat"
        ),

        Recipe(
            recipeId = 7,
            name = "Donat",
            calories = 106.0,
            carbs = 19.3,
            fat = 1.73,
            protein = 3.7,
            ingredients = "3 gram ragi\n40 gram kuning telur\n30 gram gula pasir\n130 ml susu uht full cream\n15 g susu\n250 g tepung terigu cakra kembar",
            cookingSteps = "Campur semua bahan kering.\nTambahkan semua bahan basah dan uleni hingga halus.\nDiamkan hingga ukurannya dua kali lipat.\nBentuk adonan dan goreng hingga berwarna cokelat keemasan.",
            recipePictures = "drawable/donat"
        ),

        Recipe(
            recipeId = 8,
            name = "Muffin Oat Pisang",
            calories = 136.0,
            carbs = 23.62,
            fat = 2.98,
            protein = 4.02,
            ingredients = "1 sedang telur\n1 sdt ekstrak vanila\n154 gram pisang\n5 gram baking soda\n6 gram madu\n100 g quick cooking oatmeal\n50 g squeeze yoghurt",
            cookingSteps = "Masukan semua bahan pada blender.\nSetelah tercampur, bagi adonan ke dalam cetakan.\nPanggang dengan suhu 175°C selama 25 menit.",
            recipePictures = "drawable/muffin_oat_pisang"
        ),

        Recipe(
            recipeId = 9,
            name = "Banana Bread Oats",
            calories = 247.0,
            carbs = 32.96,
            fat = 8.53,
            protein = 8.84,
            ingredients = "2 besar telur\n1 sdt kayu manis\n115 g banana\n120 g instant oatmeal grain\n120 ml almond breeze vanilla\n10 g baking powder\n13 g 1/3 pb original\n20 g fineza chocolate button",
            cookingSteps = "Panaskan oven/airfryer dengan suhu 160°C selama 10 menit.\nSementara itu, campurkan semua bahan dalam blender, kecuali coklat kancing.\nBlender adonan hingga halus. Tuang ke dalam cangkir aluminium dan hiasi tombol cokelat di atasnya.\nPanggang adonan dengan suhu 160°C selama 15 menit.",
            recipePictures = "drawable/banana_bread_oats"
        ),

        Recipe(
            recipeId = 10,
            name = "Fuyunghai Sederhana",
            calories = 200.0,
            carbs = 15.0,
            fat = 10.0,
            protein = 15.0,
            ingredients = "2 butir telur\n100 gram daging ayam cincang\n1 batang daun bawang, iris halus\n1/2 bawang bombay, iris tipis\n1 sdm tepung maizena\n1 sdt kecap asin\n1/2 sdt garam\n1/4 sdt lada hitam\nMinyak goreng secukupnya",
            cookingSteps = "Campur telur, daging ayam cincang, daun bawang, bawang bombay, tepung maizena, kecap asin, garam, dan lada hitam. Aduk rata.\nPanaskan minyak goreng dalam wajan.\nTuang adonan fuyunghai ke dalam wajan, goreng hingga matang dan berwarna kecoklatan.\nAngkat dan sajikan dengan saus asam manis.",
            recipePictures = "drawable/fuyunghai_sederhana"
        ),

        Recipe(
            recipeId = 11,
            name = "Salad Mediterania",
            calories = 250.0,
            carbs = 15.0,
            fat = 15.0,
            protein = 15.0,
            ingredients = "100 gram selada romaine\n50 gram tomat ceri, dipotong dua\n50 gram mentimun, dipotong dadu\n50 gram paprika merah, dipotong dadu\n50 gram bawang bombay merah, diiris tipis\n50 gram zaitun hitam\n50 gram keju feta, potong dadu\n50 gram dressing salad Mediterania\n1 sdm oregano kering\n1/2 sdt garam\n1/4 sdt lada hitam",
            cookingSteps = "Campur semua bahan dalam mangkuk besar.\nAduk rata dengan dressing salad Mediterania.\nBumbui dengan oregano kering, garam, dan lada hitam.\nSajikan segera.",
            recipePictures = "drawable/salad_mediterania"
        ),

        Recipe(
            recipeId = 12,
            name = "Mie Shirataki Goreng",
            calories = 175.0,
            carbs = 19.04,
            fat = 5.92,
            protein = 9.13,
            ingredients = "1 sejumput salt\n1 buah utuh portabella mushrooms\n30 gram cherry tomatoes\n3 ml minyak goreng\n1 sdm kecap manis\n75 g mie kering shirataki\n8 g sambal bawang\n45 g dada ayam\n65 g telur omega",
            cookingSteps = "Panaskan wajan dengan minyak dan tumis irisan dada ayam dan jamur.\nTambahkan telur orak-arik sebelumnya, irisan tomat, dan bahan lainnya, masak hingga matang.\nPindahkan ke piring dan sajikan. Menikmati.",
            recipePictures = "drawable/mie_shirataki_goreng"
        ),

        Recipe(
            recipeId = 13,
            name = "Chili Con Carne",
            calories = 350.0,
            carbs = 20.0,
            fat = 20.0,
            protein = 25.0,
            ingredients = "200 gram daging sapi giling\n100 gram kacang merah, direbus\n1 bawang bombay, cincang halus\n2 siung bawang putih, cincang halus\n1 buah paprika merah, potong dadu\n1 buah paprika hijau, potong dadu\n1 sdm cabai bubuk\n1 sdt jintan bubuk\n1/2 sdt paprika bubuk\n1/2 sdt oregano kering\n1 kaleng tomat potong dadu\n400 ml air\n1 sdm saus tomat\nGaram dan lada hitam secukupnya",
            cookingSteps = "Panaskan minyak goreng dalam wajan.\nTumis bawang bombay dan bawang putih sampai harum.\nMasukkan daging sapi giling, masak sampai berubah warna.\nMasukkan paprika merah dan paprika hijau, masak sebentar.\nBumbui dengan cabai bubuk, jintan bubuk, paprika bubuk, oregano kering, garam, dan lada hitam. Aduk rata.\nMasukkan tomat potong dadu, air, dan saus tomat. Aduk rata.\nDidihkan, kemudian kecilkan api dan masak selama 30-40 menit hingga daging empuk dan saus mengental.\nSajikan Chili Con Carne dengan nasi putih atau roti tortilla.",
            recipePictures = "drawable/chili_con_carne"
        ),

        Recipe(
            recipeId = 14,
            name = "Gandum dengan Buah",
            calories = 251.0,
            carbs = 50.22,
            fat = 3.8,
            protein = 4.57,
            ingredients = "1 sdm madu\n1 gelas yogurt stroberi\n3 sdm gandum\n100 g buah naga\n100 g apel",
            cookingSteps = "Masak gandum dengan air sesuai kemasan.\nPindahkan ke mangkuk lalu tambahkan yogurt.\nIris buah dan tambahkan ke mangkuk. Gerimis dengan madu.",
            recipePictures = "drawable/gandum_dengan_buah"
        ),

        Recipe(
            recipeId = 15,
            name = "Saus Cokelat",
            calories = 78.0,
            carbs = 9.56,
            fat = 3.65,
            protein = 2.12,
            ingredients = "10 gram mentega\n250 ml air\n25 gram tepung terigu putih (semua keperluan)\n10 g serat pembuat krim\n25 g coklat bubuk",
            cookingSteps = "Panaskan air dalam panci tapi jangan sampai mendidih.\nTambahkan bahan kering sambil diaduk.\nLanjutkan mengaduk sampai semuanya larut.\nMatikan api lalu masukkan mentega. Campur dengan baik.",
            recipePictures = "drawable/saus_cokelat"
        ),
        Recipe(
            recipeId = 16,
            name = "Tumis Kangkung",
            calories = 46.0,
            carbs = 7.0,
            fat = 1.4,
            protein = 2.8,
            ingredients = "2 ikat kangkung, potong-potong\n1 sendok makan bawang putih cincang\n1/2 sendok teh cabai rawit iris (optional)\n1 sendok makan saus tiram\n1/2 sendok teh garam\n1 sendok makan minyak goreng",
            cookingSteps = "Panaskan minyak goreng dalam wajan.\nTumis bawang putih dan cabai rawit sampai harum.\nMasukkan kangkung, aduk rata.\nTambahkan saus tiram dan garam, aduk kembali.\nTumis sampai kangkung layu dan bumbu meresap.\nAngkat dan sajikan.",
            recipePictures = "drawable/tumis_kangkung"
        ),
        Recipe(
            recipeId = 17,
            name = "Borscht",
            calories = 210.0,
            carbs = 25.0,
            fat = 10.0,
            protein = 8.0,
            ingredients = "500 gram bit merah, dipotong dadu\n2 wortel, dipotong dadu\n1 bawang bombay, cincang\n2 siung bawang putih, cincang\n500 ml kaldu sayuran\n200 gram kubis, iris halus\n3 sendok makan cuka\nGaram secukupnya\nMerica hitam secukupnya\nSour cream (opsional) untuk penyajian",
            cookingSteps = "Panaskan minyak dalam panci besar, tumis bawang bombay dan bawang putih hingga harum.\nMasukkan bit, wortel, dan kubis, tumis hingga layu.\nTuangkan kaldu sayuran, masak hingga sayuran matang.\nTambahkan cuka, garam, dan merica hitam, aduk rata.\nSajikan dengan sour cream jika diinginkan.",
            recipePictures = "drawable/borscht"
        ),
        Recipe(
            recipeId = 18,
            name = "Kimchi",
            calories = 120.0,
            carbs = 10.0,
            fat = 5.0,
            protein = 3.0,
            ingredients = "100 gram sawi putih\n2 sendok makan garam\n1 sendok makan gula pasir\n2 sendok makan bubuk cabai\n3 siung bawang putih, cincang\n2 cm jahe, parut\n1 sendok makan kecap ikan",
            cookingSteps = "Potong-potong sawi putih, lalu rendam dengan garam selama 1-2 jam.\nBilas sawi putih dengan air bersih untuk menghilangkan garam berlebih.\nCampurkan gula pasir, bubuk cabai, bawang putih cincang, jahe parut, dan kecap ikan dalam mangkuk.\nTambahkan sawi putih yang telah direndam ke dalam campuran bumbu, aduk rata.\nSimpan kimchi dalam wadah kedap udara dan biarkan fermentasi selama setidaknya 24 jam di suhu ruangan.\nSetelah itu, simpan di dalam kulkas dan kimchi siap disajikan.",
            recipePictures = "drawable/kimchi"
        ),
        Recipe(
            recipeId = 19,
            name = "Sate Ayam Madura",
            calories = 312.0,
            carbs = 18.0,
            fat = 20.0,
            protein = 24.0,
            ingredients = "500 gram daging ayam, potong dadu\n100 ml bumbu kacang sate madura\n100 ml kecap manis\n100 ml air\n1 batang serai, geprek\n1 ruas lengkuas, geprek\n2 lembar daun salam\n1 lembar daun jeruk\n1/2 sendok teh garam\n1/4 sendok teh lada hitam\n10 tusuk sate",
            cookingSteps = "Campurkan daging ayam dengan bumbu kacang sate madura, kecap manis, air, serai, lengkuas, daun salam, daun jeruk, garam, dan lada hitam. Aduk rata dan marinasi selama minimal 30 menit.\nTusuk daging ayam yang telah dimarinasi ke dalam tusuk sate.\nBakar sate ayam di atas bara api hingga matang dan berwarna kecoklatan.\nSajikan sate ayam dengan lontong, irisan bawang merah, cabai rawit, dan acar.",
            recipePictures = "drawable/sate_ayam_madura"
        ),
        Recipe(
            recipeId = 20,
            name = "Es Cendol",
            calories = 220.0,
            carbs = 45.0,
            fat = 5.0,
            protein = 5.0,
            ingredients = "100 gram tepung beras\n100 gram tepung hunkwe\n1/2 sendok teh garam\n1 liter air\n1 lembar daun pandan\n100 gram gula merah, sisir halus\n100 ml santan cair\n100 ml santan kental\nEs serut secukupnya",
            cookingSteps = "Campurkan tepung beras, tepung hunkwe, garam, dan air. Aduk rata dan saring.\nMasukkan daun pandan ke dalam adonan cendol. Masak adonan cendol dengan api sedang sambil terus diaduk sampai adonan mengental dan transparan.\nTuang adonan cendol ke dalam air dingin. Biarkan cendol mendingin dan mengeras.\nSiapkan gelas saji. Masukkan es serut, cendol, gula merah, santan cair, dan santan kental. Sajikan es cendol selagi dingin.",
            recipePictures = "drawable/es_cendol"
        ),
        Recipe(
            recipeId = 21,
            name = "Rendang Padang",
            calories = 480.0,
            carbs = 20.0,
            fat = 38.0,
            protein = 22.0,
            ingredients = "1 kg daging sapi, potong dadu\n100 ml santan kental\n100 ml santan cair\n50 gram bumbu rendang instan\n1 batang serai, geprek\n1 ruas lengkuas, geprek\n2 lembar daun salam\n1 lembar daun jeruk\n1/2 sendok teh garam\n1/4 sendok teh lada hitam",
            cookingSteps = "Campurkan daging sapi dengan bumbu rendang instan, santan kental, santan cair, serai, lengkuas, daun salam, daun jeruk, garam, dan lada hitam. Aduk rata dan masak dengan api kecil selama 2-3 jam hingga daging empuk dan bumbu meresap.\nSajikan rendang dengan nasi putih dan sambal lado mudo.",
            recipePictures = "drawable/rendang_padang"
        ),
        Recipe(
            recipeId = 22,
            name = "Nasi Goreng Jawa",
            calories = 280.0,
            carbs = 38.0,
            fat = 12.0,
            protein = 10.0,
            ingredients = "1 piring nasi putih\n1 butir telur\n100 gram ayam, potong dadu\n50 gram udang, kupas dan bersihkan\n2 batang kol, iris tipis\n1 wortel, iris korek api\n1 batang daun bawang, iris\n1 sendok makan kecap manis\n1/2 sendok teh garam\n1/4 sendok teh lada hitam\n1 sendok makan minyak goreng",
            cookingSteps = "Panaskan minyak goreng dalam wajan.\nTumis ayam dan udang sampai matang.\nMasukkan kol, wortel, dan daun bawang, tumis sebentar.\nMasukkan nasi putih, aduk rata.\nBumbui dengan kecap manis, garam, dan lada hitam, aduk rata.\nMasak nasi goreng sampai matang.\nSajikan nasi goreng dengan acar, kerupuk, dan bawang goreng.",
            recipePictures = "drawable/nasi_goreng_jawa"
        ),
        Recipe(
            recipeId = 23,
            name = "Pepes Ikan Mas",
            calories = 250.0,
            carbs = 15.0,
            fat = 18.0,
            protein = 17.0,
            ingredients = "1 ekor ikan mas, bersihkan\n2 lembar daun pisang\n100 gram bumbu pepes ikan mas instan\n1 batang serai, geprek\n1 ruas lengkuas, geprek\n2 lembar daun salam\n1 lembar daun jeruk\n1/2 sendok teh garam\n1/4 sendok teh lada hitam",
            cookingSteps = "Campurkan bumbu pepes ikan mas instan dengan serai, lengkuas, daun salam, daun jeruk, garam, dan lada hitam. Aduk rata.\nLumuri ikan mas dengan bumbu pepes.\nBungkus ikan mas dengan daun pisang. Kukus pepes ikan mas selama 30-40 menit hingga matang.\nSajikan pepes ikan mas dengan nasi putih dan sambal terasi.",
            recipePictures = "drawable/pepes_ikan_mas"
        ),
        Recipe(
            recipeId = 24,
            name = "Capcay",
            calories = 220.0,
            carbs = 30.0,
            fat = 8.0,
            protein = 12.0,
            ingredients = "100 gram sawi hijau\n100 gram wortel\n100 gram kembang kol\n50 gram ayam\n1/2 bawang bombay\n1 sendok makan saus tiram\n1/2 sendok teh kecap manis\n1/4 sendok teh lada hitam\n1 sendok makan minyak goreng",
            cookingSteps = "Panaskan minyak goreng dalam wajan.\nTumis bawang putih dan bawang bombay sampai harum.\nMasukkan ayam dan udang, tumis sampai berubah warna.\nMasukkan wortel, buncis, dan kembang kol, aduk rata.\nTambahkan air secukupnya.\nBumbui dengan saus tiram, kecap manis, garam, dan lada hitam. Aduk rata.\nMasukkan sawi hijau terakhir. Masak capcay sampai matang dan bumbu meresap.\nSajikan capcay hangat dengan nasi putih.",
            recipePictures = "drawable/capcay"
        ),
        Recipe(
            recipeId = 25,
            name = "Bubur Ayam",
            calories = 300.0,
            carbs = 40.0,
            fat = 5.0,
            protein = 15.0,
            ingredients = "100 gram beras\n800 ml air\n1 ekor ayam, potong-potong\n1/2 sendok teh garam\n1/4 sendok teh lada hitam\nIrisan daun bawang secukupnya\nSoya kecap secukupnya\nKerupuk pangsit secukupnya",
            cookingSteps = "Cuci bersih beras. Masukkan beras dan air ke dalam panci. Masak dengan api sedang sambil diaduk sesekali hingga beras menjadi bubur.\nBumbui bubur dengan garam dan lada hitam. Aduk rata.\nRebus ayam sampai matang. Suwir-suwir daging ayam.\nSajikan bubur ayam dengan suwiran ayam, irisan daun bawang, kecap soya, dan kerupuk pangsit.",
            recipePictures = "drawable/bubur_ayam"
        ),
    )

    val recommendations = listOf(
        Recommendation(
            recommendationId = 1,
            date = Date(System.currentTimeMillis()),
            isSelected = false,
            mealType = 2,
            recipeId = 3
        ),
        Recommendation(
            recommendationId = 2,
            date = Date(System.currentTimeMillis() - 86400000),
            isSelected = false,
            mealType = 1,
            recipeId = 10
        ),
        Recommendation(
            recommendationId = 3,
            date = Date(System.currentTimeMillis() + 86400000),
            isSelected = false,
            mealType = 3,
            recipeId = 15
            ),
        Recommendation(
            recommendationId = 4,
            date = Date(System.currentTimeMillis() + 86400000 * 2),
            isSelected = true,
            mealType = 2,
            recipeId = 20
        ),
        Recommendation(
            recommendationId = 5,
            date = Date(System.currentTimeMillis() + 86400000 * 2),
            isSelected = true,
            mealType = 1,
            recipeId = 24
        )
    )
}
