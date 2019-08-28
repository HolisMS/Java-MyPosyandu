package satella.app.posyanduku.adapter;

import java.util.ArrayList;

import satella.app.posyanduku.models.Sehat;

public class SehatAdapter {
    public static String[][] data = new String[][] {
            {"Butter","Nggak selamanya butter itu buruk untuk anak. Makanan ini bisa menjadi makanan yang kaya lemak untuk anak. Tapi, nggak berarti Bunda bisa menambahkan butter di setiap makanan ya. Berikan butter secukupnya saja, jangan berlebihan.","https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/NCI_butter.jpg/800px-NCI_butter.jpg"},
            {"Selai kacang","Untuk menyiapkan bekal yang praktis, Bunda bisa mengoleskan selai kacang ke atas roti. Selain mudah, selai kacang juga bermanfaat lho untuk anak. Makanan ini kaya lemak tak jenuh, dan protein yang bisa membantu menaikkan berat badan anak.","https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/PeanutButter.jpg/800px-PeanutButter.jpg"},
            {"Susu","Susu adalah sumber protein, karbohidrat, dan nutrisi lainnya yang baik untuk anak. Berikan anak setidaknya 2 gelas susu per hari. Bentuknya bisa macam-macam. Misalnya, milkshake, sereal, atau smoothies. Bunda juga bisa menambahkan krim yang terbuat dari susu segar di atas salad. Selain enak, sehat kan?","https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Milk_glass.jpg/532px-Milk_glass.jpg"},
            {"Telur","Dalam 100 gram telur terdapat 13 gram protein, vitamin A, dan B12 yang baik untuk pertumbuhan anak. Bunda bisa menambahkan telur ke dalam menu makan sehari-hari, dengan cara yang bervariasi.","https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Coturnix_coturnix_eggs.jpg/800px-Coturnix_coturnix_eggs.jpg"},
            {"Pisang","Buah yang satu ini bisa menjadi sumber energi yang baik untuk tubuh. Satu buah pisang mengandung 105 kalori lho. Tambahkan pisang ke dalam makanan kesukaan anak seperti milkshake, puding, atau mencampurnya dengan cokelat.","https://upload.wikimedia.org/wikipedia/id/thumb/1/18/Pisang_mas_super.jpg/450px-Pisang_mas_super.jpg"},
            {"Alpukat","Kalau anak Bunda terlalu kurus, coba deh berikan dia alpukat. Alpukat mengandung lemak dan kalori yang baik untuk pertumbuhan anak. Buah ini juga menjadi sumber lemak yang lebih sehat, dibandingkan dengan makanan lainnya.","data:image/jpeg;base64,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"},
            {"Ayam","Protein sangat penting untuk membangun otot-otot tubuh. Makanan yang mengandung protein seperti ayam, penting bagi pertumbuhan anak. Selain harganya yang lebih murah dibandingkan jenis daging lain, ayam juga memiliki rasa yang lezat, Bun.","https://upload.wikimedia.org/wikipedia/commons/a/a5/Roast_chicken.jpg"},
            {"Kentang","Kentang mengandung asam amino seperti arginin dan glutamin, yang bagus untuk menaikkan berat badan.","https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Various_types_of_potatoes_for_sale.jpg/800px-Various_types_of_potatoes_for_sale.jpg"},
            {"Daging merah","Daging merah menjadi sumber zat besi dan lemak, yang dibutuhkan anak dalam masa pertumbuhan. Usahakan untuk memilih daging sapi dan domba yang segar ya! Kalau bisa, hindari memasak daging olahan yang diawetkan. Sebab, daging seperti itu biasanya mengandung bahan yang berbahaya bagi kesehatan.","https://upload.wikimedia.org/wikipedia/commons/2/2e/Roast_beef.jpg"},
            {"Buah dan sayur","Buah dan sayur menjadi makanan yang wajib ada dalam menu sehari-hari. Buah-buahan seperti mangga, pepaya, nanas, dan sebagainya menjadi sumber gula alami. Ini akan membantu memberikan energi dan menaikkan berat badan.","https://hellosehat.com/wp-content/uploads/377277385-hello-sehat-1024x648-1024x648.jpg?x36678"}
    };

    public static ArrayList<Sehat> getListData(){
        ArrayList<Sehat> list = new ArrayList<>();
        for (String[] aData : data){
            Sehat sehat = new Sehat();
            sehat.setNama(aData[0]);
            sehat.setDesc(aData[1]);
            sehat.setPhoto(aData[2]);

            list.add(sehat);
        }
        return list;
    }
}
