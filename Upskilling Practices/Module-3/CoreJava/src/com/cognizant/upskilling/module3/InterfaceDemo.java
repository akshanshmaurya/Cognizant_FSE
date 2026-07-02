package com.cognizant.upskilling.module3;

interface Playable {
    void play();
}

class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Strumming the guitar chords.");
    }
}

class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Playing a classical melody on the piano keys.");
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        Playable instrument1 = new Guitar();
        Playable instrument2 = new Piano();
        
        instrument1.play();
        instrument2.play();
    }
}
