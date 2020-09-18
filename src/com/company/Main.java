package com.company;

import com.googlecode.lanterna.SGR;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;


import java.io.IOException;


public class Main {


    public static void main(String[] args) {


        Terminal terminal = null;


        try {

            terminal = new DefaultTerminalFactory().createTerminal();
            TextGraphics textGraphics = terminal.newTextGraphics();
            textGraphics.putString(0, 0, "Press Y/N... ", SGR.BOLD);
            KeyStroke keyStroke = terminal.readInput();
            Thread t = new Thread(new MouseAction());


            while (keyStroke.getKeyType() != KeyType.Escape) {

                terminal.clearScreen();
                terminal.setBackgroundColor(TextColor.ANSI.BLACK);
                textGraphics.putString(0, 0, "Press Y/N... ", SGR.BOLD);
                t.suspend();

                if (keyStroke.getKeyType().name() == "Character") {
                    if (keyStroke.getCharacter().toString().toLowerCase().equals("y")) {

                        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);
                        textGraphics.putString(0, 1, "Mouse ON", SGR.BLINK, SGR.BOLD);
                        t = new Thread(new MouseAction());
                        t.start();


                    }

                    if (keyStroke.getCharacter().toString().toLowerCase().equals("n")) {
                        textGraphics.setBackgroundColor(TextColor.ANSI.YELLOW);
                        textGraphics.putString(0, 1, "Mouse OFF", SGR.BOLD);

                    }
                }


                terminal.flush();
                keyStroke = terminal.readInput();
            }

            t.suspend();
            t.stop();
            terminal.flush();
            terminal.clearScreen();
            textGraphics.putString(0, 0, "Goodbye", SGR.BOLD);
            terminal.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
