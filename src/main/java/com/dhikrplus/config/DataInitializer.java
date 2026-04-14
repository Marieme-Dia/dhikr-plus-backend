package com.dhikrplus.config;

import com.dhikrplus.entity.*;
import com.dhikrplus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired DhikrRepository dhikrRepository;
    @Autowired AudioRepository audioRepository;
    @Autowired LessonRepository lessonRepository;

    @Override
    public void run(String... args) {
        seedDhikrs();
        seedAudios();
        seedLessons();
    }

    private void seedDhikrs() {
        if (dhikrRepository.count() > 0) return;
        dhikrRepository.save(Dhikr.builder().name("Subhan Allah").arabicText("سُبْحَانَ اللهِ")
            .transliteration("Subḥāna llāh").meaning("Gloire à Allah").defaultTarget(33)
            .reference("Sahih Muslim").build());
        dhikrRepository.save(Dhikr.builder().name("Alhamdulillah").arabicText("الحَمْدُ لِلهِ")
            .transliteration("Al-ḥamdu lillāh").meaning("Louange à Allah").defaultTarget(33)
            .reference("Sahih Muslim").build());
        dhikrRepository.save(Dhikr.builder().name("Allahu Akbar").arabicText("اللهُ أَكْبَرُ")
            .transliteration("Allāhu akbar").meaning("Allah est le Plus Grand").defaultTarget(34)
            .reference("Sahih Muslim").build());
        dhikrRepository.save(Dhikr.builder().name("La ilaha illa Allah").arabicText("لَا إِلَهَ إِلَّا اللهُ")
            .transliteration("Lā ilāha illā llāh").meaning("Pas de divinité sauf Allah").defaultTarget(100)
            .reference("Sahih Bukhari").build());
        dhikrRepository.save(Dhikr.builder().name("Astaghfirullah").arabicText("أَسْتَغْفِرُ اللهَ")
            .transliteration("Astaġfiru llāh").meaning("Je demande pardon à Allah").defaultTarget(100)
            .reference("Sahih Bukhari").build());
        dhikrRepository.save(Dhikr.builder().name("Salawat").arabicText("صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ")
            .transliteration("Ṣallā llāhu ʿalayhi wa-sallam").meaning("Bénédictions sur le Prophète ﷺ")
            .defaultTarget(100).reference("Hadith").build());
    }

    private void seedAudios() {
        if (audioRepository.count() > 0) return;
        audioRepository.save(Audio.builder().title("Sourate Al-Fatiha").arabicTitle("سُورَةُ الفَاتِحَة")
            .category("coran").reciter("Sheikh Sudais").duration("1:23").icon("📖").surahNumber(1)
            .url("https://cdn.islamic.network/quran/audio/128/ar.alafasy/1.mp3").build());
        audioRepository.save(Audio.builder().title("Sourate Al-Ikhlas").arabicTitle("سُورَةُ الإِخْلَاص")
            .category("coran").reciter("Sheikh Mishary").duration("0:42").icon("📖").surahNumber(112)
            .url("https://cdn.islamic.network/quran/audio/128/ar.alafasy/112.mp3").build());
        audioRepository.save(Audio.builder().title("Ayat Al-Kursi").arabicTitle("آيَةُ الكُرْسِي")
            .category("coran").reciter("Sheikh Abdurrahman").duration("1:05").icon("📖").surahNumber(2)
            .url("https://cdn.islamic.network/quran/audio/128/ar.alafasy/2.mp3").build());
        audioRepository.save(Audio.builder().title("Invocation du matin").arabicTitle("دُعَاءُ الصَّبَاح")
            .category("dua").reciter("Collection Adhkar").duration("3:20").icon("🌅")
            .url("").build());
        audioRepository.save(Audio.builder().title("Invocation du soir").arabicTitle("دُعَاءُ المَسَاء")
            .category("dua").reciter("Collection Adhkar").duration("3:45").icon("🌙")
            .url("").build());
        audioRepository.save(Audio.builder().title("Dhikr — Tasbiha").arabicTitle("تَسْبِيح")
            .category("dhikr").reciter("Compilation").duration("5:00").icon("📿")
            .url("").build());
    }

    private void seedLessons() {
        if (lessonRepository.count() > 0) return;
        // Sourates
        lessonRepository.save(Lesson.builder().title("Al-Fatiha").arabicTitle("سُورَةُ الفَاتِحَة")
            .lessonType("sourate").level("debutant").orderIndex(1).icon("📖")
            .content("7 versets — La sourate d'ouverture").build());
        lessonRepository.save(Lesson.builder().title("Al-Ikhlas").arabicTitle("سُورَةُ الإِخْلَاص")
            .lessonType("sourate").level("debutant").orderIndex(2).icon("📖")
            .content("4 versets — La pureté").build());
        lessonRepository.save(Lesson.builder().title("Al-Falaq").arabicTitle("سُورَةُ الفَلَق")
            .lessonType("sourate").level("debutant").orderIndex(3).icon("📖")
            .content("5 versets — L'aube").build());
        lessonRepository.save(Lesson.builder().title("An-Nas").arabicTitle("سُورَةُ النَّاس")
            .lessonType("sourate").level("debutant").orderIndex(4).icon("📖")
            .content("6 versets — Les hommes").build());
        lessonRepository.save(Lesson.builder().title("Al-Kawthar").arabicTitle("سُورَةُ الكَوْثَر")
            .lessonType("sourate").level("debutant").orderIndex(5).icon("📖")
            .content("3 versets — L'abondance").build());
        lessonRepository.save(Lesson.builder().title("Al-Asr").arabicTitle("سُورَةُ العَصْر")
            .lessonType("sourate").level("debutant").orderIndex(6).icon("📖")
            .content("3 versets — Le temps").build());
        // Alphabet
        String[] letters = {"أ","ب","ت","ث","ج","ح","خ","د","ذ","ر","ز","س","ش","ص","ض","ط","ظ","ع","غ","ف","ق","ك","ل","م","ن","و","ه","ي"};
        String[] names  = {"Alif","Ba","Ta","Tha","Jim","Ha","Kha","Dal","Dhal","Ra","Zay","Sin","Shin","Sad","Dad","Ta","Dha","Ain","Ghayn","Fa","Qaf","Kaf","Lam","Mim","Nun","Waw","Ha","Ya"};
        for (int i = 0; i < letters.length; i++) {
            lessonRepository.save(Lesson.builder().title(names[i]).arabicTitle(letters[i])
                .lessonType("alphabet").level("debutant").orderIndex(i + 1).icon("حرف").content("").build());
        }
    }
}
