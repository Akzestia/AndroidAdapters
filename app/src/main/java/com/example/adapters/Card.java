package com.example.adapters;
import java.util.Random;
public class Card {
    public String number;
    public int ccv;
    public  String expDate;
    public String type;
    public double balance;
    public Card() {
        Random random = new Random();
        this.number = String.format("%04d", random.nextInt(10000)) + String.format("%04d", random.nextInt(10000)) + String.format("%04d", random.nextInt(10000)) + String.format("%04d", random.nextInt(10000));
        this.ccv = random.nextInt(900) + 100; // generates a random 3 digit number
        this.expDate = String.format("%02d", random.nextInt(12) + 1) + "/" + (random.nextInt(5) + 2022); // generates a random date between 01/2022 and 12/2026
        this.type = random.nextBoolean() ? "Visa" : "MasterCard"; // randomly selects between Visa and MasterCard
        this.balance = 1000 + (10000 - 1000) * random.nextDouble();
    }
}
