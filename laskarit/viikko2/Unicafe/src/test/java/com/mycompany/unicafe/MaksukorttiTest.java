package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000); //1000 senttiä = 10 euroa 
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOIkein(){
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    
    @Test 
    public void rahanLataaminenKasvattaaSaldoaOikein(){
        kortti.lataaRahaa(200);
        assertEquals("saldo: 12.0", kortti.toString());
    }
    
    
    @Test
    public void saldoVaheneeJosRahaaTarpeeksi(){
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi(){
        kortti.otaRahaa(1500);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    
    @Test
    public void rahanOttaminenPalauttaaTrueJosTarpeeksiRahaa(){
        assertTrue(kortti.otaRahaa(500));
    }
    
    
    @Test
    public void rahanOttaminenPalauttaaFalseJosRahaaEiTarpeeksi(){
        assertFalse(kortti.otaRahaa(1500));
    }
    
    @Test
    public void saldoPalautetaanOikein(){
        assertEquals(1000, kortti.saldo());
    }
}
