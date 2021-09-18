package com.example.woofnew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DogsDetailsPageOne extends AppCompatActivity {

    RecyclerView recyclerGroups;
    RecyclerView recyclerBreeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_details_page_one);

        recyclerGroups = findViewById(R.id.recycler_groups);
        recyclerBreeds = findViewById(R.id.recycler_breeds);

        setGroups();
        setBreeds(0);
    }

    @SuppressLint("NotifyDataSetChanged")
    private  void setGroups() {
        List<DogGroup> data = new ArrayList<>();

        DogGroup dogGroup1 = new DogGroup("Sporting",R.drawable.ic_labrador_retriever);
        DogGroup dogGroup2 = new DogGroup("Non-Sporting",R.drawable.ic_bulldog);
        DogGroup dogGroup3 = new DogGroup("Hound",R.drawable.ic_beagle);
        DogGroup dogGroup4 = new DogGroup("Terrier",R.drawable.ic_yorkshire_terrier);
        DogGroup dogGroup5 = new DogGroup("Toy",R.drawable.ic_pomeranian);
        DogGroup dogGroup6 = new DogGroup("Working",R.drawable.ic_rottweiler);
        DogGroup dogGroup7 = new DogGroup("Herding",R.drawable.ic_german_shepherd);

        data.add(dogGroup1);
        data.add(dogGroup2);
        data.add(dogGroup3);
        data.add(dogGroup4);
        data.add(dogGroup5);
        data.add(dogGroup6);
        data.add(dogGroup7);

        DogGroupAdapter dogGroupAdapter = new DogGroupAdapter(data, DogsDetailsPageOne.this, new DogGroupAdapter.OnGroupClick() {
            @Override
            public void onClick(int pos) {
                setBreeds(pos);
            }
        });

        recyclerGroups.setLayoutManager(new LinearLayoutManager(DogsDetailsPageOne.this, RecyclerView.HORIZONTAL, false));
        recyclerGroups.setAdapter(dogGroupAdapter);
        dogGroupAdapter.notifyDataSetChanged();
    }

    private void setBreeds(int pos){
        List<DogBreed> dogBreeds = new ArrayList<>();
        switch (pos) {
            case 0:
                DogBreed dogBreed1 = new DogBreed("Labrador Retriever", 4.5f,R.drawable.labrador_dp);
                DogBreed dogBreed2 = new DogBreed("Golden Retriever", 4f,R.drawable.golden_retriever_dp);
                DogBreed dogBreed3 = new DogBreed("English Pointer", 4f,R.drawable.english_pointer_dp);
                DogBreed dogBreed4 = new DogBreed("Clumber Spaniel", 4f,R.drawable.clumber_spaniel_dp);
                DogBreed dogBreed5 = new DogBreed("Irish Setter", 3f,R.drawable.irish_setter_dp);
                DogBreed dogBreed6 = new DogBreed("Weimaraner", 3.5f,R.drawable.weimaraner_dp);
                DogBreed dogBreed7 = new DogBreed("Gordon Setter", 4.5f,R.drawable.gordon_setter_dp);
                DogBreed dogBreed8 = new DogBreed("Vizsla", 3.5f,R.drawable.vizla_dp);
                DogBreed dogBreed9 = new DogBreed("Brittany", 4.5f,R.drawable.brittany_dp);
                DogBreed dogBreed10 = new DogBreed("Sussex Spaniel", 4.5f,R.drawable.sussex_spaniel_dp);

                dogBreeds.add(dogBreed1);
                dogBreeds.add(dogBreed2);
                dogBreeds.add(dogBreed3);
                dogBreeds.add(dogBreed4);
                dogBreeds.add(dogBreed5);
                dogBreeds.add(dogBreed6);
                dogBreeds.add(dogBreed7);
                dogBreeds.add(dogBreed8);
                dogBreeds.add(dogBreed9);
                dogBreeds.add(dogBreed10);
                break;

            case 1:
                DogBreed dogBreed11 = new DogBreed("American Bulldog", 4.5f,R.drawable.bulldog_dp);
                DogBreed dogBreed12 = new DogBreed("Dalmatian", 4f,R.drawable.dalmatian_dp);
                DogBreed dogBreed13 = new DogBreed("Chow Chow", 4f,R.drawable.chow_chow_dp);
                DogBreed dogBreed14 = new DogBreed("Lhasa Apso", 4f,R.drawable.lahsa_apso_dp);
                DogBreed dogBreed15 = new DogBreed("Bichon Frise", 3f,R.drawable.bichon_frise);
                DogBreed dogBreed16 = new DogBreed("Shar pei", 3.5f,R.drawable.shar_pei_dp);
                DogBreed dogBreed17 = new DogBreed("Shiba Inu", 4.5f,R.drawable.shiba_inu);
                DogBreed dogBreed18 = new DogBreed("Keeshond", 3.5f,R.drawable.keeshond_dp);
                DogBreed dogBreed19 = new DogBreed("Boston Terrier", 4.5f,R.drawable.boston_terrier_dp);
                DogBreed dogBreed20 = new DogBreed("French Bulldog", 4.5f,R.drawable.french_bulldog_dp);

                dogBreeds.add(dogBreed11);
                dogBreeds.add(dogBreed12);
                dogBreeds.add(dogBreed13);
                dogBreeds.add(dogBreed14);
                dogBreeds.add(dogBreed15);
                dogBreeds.add(dogBreed16);
                dogBreeds.add(dogBreed17);
                dogBreeds.add(dogBreed18);
                dogBreeds.add(dogBreed19);
                dogBreeds.add(dogBreed20);
                break;

            case 2:
                DogBreed dogBreed21 = new DogBreed("Beagle", 4.5f,R.drawable.beagle_dp);
                DogBreed dogBreed22 = new DogBreed("Rhodesian Ridgeback", 4f,R.drawable.rhodesian_ridgeback_dp);
                DogBreed dogBreed23 = new DogBreed("Bloodhound", 4f,R.drawable.bloodhound_dp);
                DogBreed dogBreed24 = new DogBreed("Dachshund", 4f,R.drawable.dachshund_dp);
                DogBreed dogBreed25 = new DogBreed("English Foxhound", 3f,R.drawable.english_foxhound);
                DogBreed dogBreed26 = new DogBreed("Finnish Spitz", 3.5f,R.drawable.finnish_spitz_dp);
                DogBreed dogBreed27 = new DogBreed("Redbone Coonhound", 4.5f,R.drawable.redbone_coonhound_dp);
                DogBreed dogBreed28 = new DogBreed("Irish Wolfhound", 3.5f,R.drawable.irish_wolfhound);
                DogBreed dogBreed29 = new DogBreed("Basenji", 4.5f,R.drawable.basenji_dp);
                DogBreed dogBreed30 = new DogBreed("Otterhound", 4.5f,R.drawable.otterhound_dp);

                dogBreeds.add(dogBreed21);
                dogBreeds.add(dogBreed22);
                dogBreeds.add(dogBreed23);
                dogBreeds.add(dogBreed24);
                dogBreeds.add(dogBreed25);
                dogBreeds.add(dogBreed26);
                dogBreeds.add(dogBreed27);
                dogBreeds.add(dogBreed28);
                dogBreeds.add(dogBreed29);
                dogBreeds.add(dogBreed30);
                break;

            case 3:
                DogBreed dogBreed31 = new DogBreed("Jack Russell Terrier", 4.5f,R.drawable.jack_russell_terrier_dp);
                DogBreed dogBreed32 = new DogBreed("Border Terrier", 4f,R.drawable.border_terrier_dp);
                DogBreed dogBreed33 = new DogBreed("Irish Terrier", 4f,R.drawable.irish_terrier_dp);
                DogBreed dogBreed34 = new DogBreed("Norwich Terrier", 4f,R.drawable.norwich_terrier_dp);
                DogBreed dogBreed35 = new DogBreed("Manchester Terrier", 3f,R.drawable.manchester_terrier_dp);
                DogBreed dogBreed36 = new DogBreed("Glen", 3.5f,R.drawable.glen_dp);
                DogBreed dogBreed37 = new DogBreed("Bedlington Terrier", 4.5f,R.drawable.bedlington_terrier_dp);
                DogBreed dogBreed38 = new DogBreed("Cairn Terrier", 3.5f,R.drawable.cairn_terrier_dp);
                DogBreed dogBreed39 = new DogBreed("Patterdale Terrier", 4.5f,R.drawable.patterdale_terrier_dp);
                DogBreed dogBreed40 = new DogBreed("Rat Terrier", 4.5f,R.drawable.rat_terrier_dp);

                dogBreeds.add(dogBreed31);
                dogBreeds.add(dogBreed32);
                dogBreeds.add(dogBreed33);
                dogBreeds.add(dogBreed34);
                dogBreeds.add(dogBreed35);
                dogBreeds.add(dogBreed36);
                dogBreeds.add(dogBreed37);
                dogBreeds.add(dogBreed38);
                dogBreeds.add(dogBreed39);
                dogBreeds.add(dogBreed40);
                break;

            case 4:
                DogBreed dogBreed41 = new DogBreed("Pomeranian", 4.5f,R.drawable.pomeranian_dp);
                DogBreed dogBreed42 = new DogBreed("Maltese dog", 4f,R.drawable.maltese_dog_dp);
                DogBreed dogBreed43 = new DogBreed("Papillon", 4f,R.drawable.papillon_dog_dp);
                DogBreed dogBreed44 = new DogBreed("Shih Tzu", 4f,R.drawable.shih_tzu_dp);
                DogBreed dogBreed45 = new DogBreed("Pekingese", 3f,R.drawable.pekingese_dp);
                DogBreed dogBreed46 = new DogBreed("Pug", 3.5f,R.drawable.pug_dp);
                DogBreed dogBreed47 = new DogBreed("Havanese", 4.5f,R.drawable.havanese_dp);
                DogBreed dogBreed48 = new DogBreed("Miniature Pinscher", 3.5f,R.drawable.miniature_pinscher_dp);
                DogBreed dogBreed49 = new DogBreed("Toy Poodle", 4.5f,R.drawable.toy_poodle_dp);
                DogBreed dogBreed50 = new DogBreed("Russkiy Toy", 4.5f,R.drawable.russkiy_toy_dp);

                dogBreeds.add(dogBreed41);
                dogBreeds.add(dogBreed42);
                dogBreeds.add(dogBreed43);
                dogBreeds.add(dogBreed44);
                dogBreeds.add(dogBreed45);
                dogBreeds.add(dogBreed46);
                dogBreeds.add(dogBreed47);
                dogBreeds.add(dogBreed48);
                dogBreeds.add(dogBreed49);
                dogBreeds.add(dogBreed50);
                break;

            case 5:
                DogBreed dogBreed51 = new DogBreed("Dobermann", 4.5f,R.drawable.dobermann_dp);
                DogBreed dogBreed52 = new DogBreed("Boxer", 4f,R.drawable.boxer_dp);
                DogBreed dogBreed53 = new DogBreed("Rottweiler", 4f,R.drawable.rottweiler_dp);
                DogBreed dogBreed54 = new DogBreed("Siberian Husky", 4f,R.drawable.siberian_husky_dp);
                DogBreed dogBreed55 = new DogBreed("Great Dane", 3f,R.drawable.great_dane_dp);
                DogBreed dogBreed56 = new DogBreed("Bullmastiff", 3.5f,R.drawable.bullmastiff_dp);
                DogBreed dogBreed57 = new DogBreed("St. Bernard", 4.5f,R.drawable.st_bernard_dp);
                DogBreed dogBreed58 = new DogBreed("German Pinscher", 3.5f,R.drawable.german_pinscher_dp);
                DogBreed dogBreed59 = new DogBreed("Tibetan Mastiff", 4.5f,R.drawable.tibetan_mastiff_dp);
                DogBreed dogBreed60 = new DogBreed("Giant Schnauzer", 4.5f,R.drawable.giant_schnauzer_dp);

                dogBreeds.add(dogBreed51);
                dogBreeds.add(dogBreed52);
                dogBreeds.add(dogBreed53);
                dogBreeds.add(dogBreed54);
                dogBreeds.add(dogBreed55);
                dogBreeds.add(dogBreed56);
                dogBreeds.add(dogBreed57);
                dogBreeds.add(dogBreed58);
                dogBreeds.add(dogBreed59);
                dogBreeds.add(dogBreed60);
                break;

            case 6:
                DogBreed dogBreed61 = new DogBreed("German Shepherd", 4.5f,R.drawable.german_shepherd_dp);
                DogBreed dogBreed62 = new DogBreed("Bearded Collie", 4f,R.drawable.bearded_collie_dp);
                DogBreed dogBreed63 = new DogBreed("Belgian Sheepdog", 4f,R.drawable.belgian_sheepdog_dp);
                DogBreed dogBreed64 = new DogBreed("Border Collie", 4f,R.drawable.border_collie_dp);
                DogBreed dogBreed65 = new DogBreed("Belgian Tervuren", 3f,R.drawable.belgian_tervuren_dp);
                DogBreed dogBreed66 = new DogBreed("Collie", 3.5f,R.drawable.collie_dp);
                DogBreed dogBreed67 = new DogBreed("Spanish Water Dog", 4.5f,R.drawable.spanish_water_dog_dp);
                DogBreed dogBreed68 = new DogBreed("Pumi", 3.5f,R.drawable.pumi_dp);
                DogBreed dogBreed69 = new DogBreed("Puli", 4.5f,R.drawable.puli_dp);
                DogBreed dogBreed70 = new DogBreed("Swedish Vallhund", 4.5f,R.drawable.swedish_vallhund_dp);

                dogBreeds.add(dogBreed61);
                dogBreeds.add(dogBreed62);
                dogBreeds.add(dogBreed63);
                dogBreeds.add(dogBreed64);
                dogBreeds.add(dogBreed65);
                dogBreeds.add(dogBreed66);
                dogBreeds.add(dogBreed67);
                dogBreeds.add(dogBreed68);
                dogBreeds.add(dogBreed69);
                dogBreeds.add(dogBreed70);
                break;

        }

        DogBreedAdapter dogBreedAdapter = new DogBreedAdapter(dogBreeds);
        recyclerBreeds.setLayoutManager(new LinearLayoutManager(DogsDetailsPageOne.this, RecyclerView.HORIZONTAL, false));
        recyclerBreeds.setAdapter(dogBreedAdapter);
    }
}