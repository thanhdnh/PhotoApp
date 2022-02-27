package thanhdnh.ueh.edu.photoapp;

import java.util.ArrayList;

public class PhotoData {
    public static ArrayList<Photo> generatePhotoData() {
        ArrayList<Photo> photos = new ArrayList<>();
        photos.add(new Photo(0, "https://images.unsplash.com/photo-1550258987-190a2d41a8ba", "Pineapple", "The pineapple[2][3] (Ananas comosus) is a tropical plant with an edible fruit and the most economically significant plant in the family Bromeliaceae.[4] The pineapple is indigenous to South America, where it has been cultivated for many centuries. The introduction of the pineapple to Europe in the 17th century made it a significant cultural icon of luxury. Since the 1820s, pineapple has been commercially grown in greenhouses and many tropical plantations. Further, it is the third most important tropical fruit in world production. In the 20th century, Hawaii was a dominant producer of pineapples, especially for the US; however, by 2016, Costa Rica, Brazil, and the Philippines accounted for nearly one-third of the world's production of pineapples.[5]\n" +
                "\n" +
                "Pineapples grow as a small shrub; the individual flowers of the unpollinated plant fuse to form a multiple fruit. The plant is normally propagated from the offset produced at the top of the fruit,[2][6] or from a side shoot, and typically mature within a year.[6][7]"));
        photos.add(new Photo(1, "https://images.unsplash.com/photo-1528821128474-27f963b062bf", "Cherry", "A cherry is the fruit of many plants of the genus Prunus, and is a fleshy drupe (stone fruit).\n" +
                "\n" +
                "Commercial cherries are obtained from cultivars of several species, such as the sweet Prunus avium and the sour Prunus cerasus. The name 'cherry' also refers to the cherry tree and its wood, and is sometimes applied to almonds and visually similar flowering trees in the genus Prunus, as in \"ornamental cherry\" or \"cherry blossom\". Wild cherry may refer to any of the cherry species growing outside cultivation, although Prunus avium is often referred to specifically by the name \"wild cherry\" in the British Isles."));
        photos.add(new Photo(2, "https://images.unsplash.com/photo-1552841847-0e031d33a283", "Orange", ""));
        photos.add(new Photo(3, "https://images.unsplash.com/photo-1519162808019-7de1683fa2ad", "Avocado", ""));
        photos.add(new Photo(4, "https://images.unsplash.com/photo-1560155069-ad79768f2666", "Strawberry", ""));
        photos.add(new Photo(5, "https://images.unsplash.com/photo-1600423115367-87ea7661688f", "Apple", ""));
        photos.add(new Photo(6, "https://images.unsplash.com/photo-1530730459653-e0edd4c6072e", "Pomegranate", ""));
        photos.add(new Photo(7, "https://images.unsplash.com/photo-1517282009859-f000ec3b26fe", "Papaya", ""));
        photos.add(new Photo(8, "https://images.unsplash.com/photo-1601876819102-99560f772713", "Pear", ""));
        photos.add(new Photo(9, "https://images.unsplash.com/photo-1600046438510-46961a84f4b2", "Blueberry", ""));
        return photos;
    }

    public static Photo getPhotoFromId(int id) {
        ArrayList<Photo> phs = generatePhotoData();
        for (int i = 0; i < phs.size(); i++)
            if (phs.get(i).getId() == id)
                return phs.get(i);
        return null;
    }
}
