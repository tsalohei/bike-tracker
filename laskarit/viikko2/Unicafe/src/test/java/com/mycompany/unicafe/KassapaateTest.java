package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void alussaRahaaOikeaSumma(){
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void alussaEiYhtaanEdullistaLounastaMyyty(){
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void alussaEiYhtaanMaukastaLounastaMyyty(){
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
   
    @Test 
    public void kassaPaivittyyOikeinKunsyoEdullisestiJaKateismaksuRiittava(){
        kassapaate.syoEdullisesti(300);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinKunSyoEdullisestiJaKateismaksuRiittava(){
        assertEquals(60, kassapaate.syoEdullisesti(300));
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaaKunSyoEdullisestiJaKateismaksuRiittava(){
        kassapaate.syoEdullisesti(300);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kassaEiMuutuJosYrittaaOstaaEdullisenLounaanJaKateismaksuEiRiittava(){
        kassapaate.syoEdullisesti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahatPalautetaanOikeinJosYrittaaOstaaEdullisenLounaanJaKateismaksuEiRiittava(){
        assertEquals(200, kassapaate.syoEdullisesti(200));
    }
    
    @Test
    public void myytyjenEdullistenLounaidenMaarassaEiMuutostaJosYrittaaOstaaEdullisenLounaanJaKateismaksuEiRiittava(){
        kassapaate.syoEdullisesti(200);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test 
    public void kassaPaivittyyOikeinKunsyoMaukkaastiJaKateismaksuRiittava(){
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahaOikeinKunSyoMaukkaastiJaKateismaksuRiittava(){
        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaKunSyoMaukkaastiJaKateismaksuRiittava(){
        kassapaate.syoMaukkaasti(500);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaEiMuutuJosYrittaaOstaaMaukkaanLounaanJaKateismaksuEiRiittava(){
        kassapaate.syoMaukkaasti(300);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void vaihtorahatPalautetaanOikeinJosYrittaaOstaaMaukkaanLounaanJaKateismaksuEiRiittava(){
        assertEquals(200, kassapaate.syoMaukkaasti(200));
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaarassaEiMuutostaJosYrittaaOstaaMaukkaanLounaanJaKateismaksuEiRiittava(){
        kassapaate.syoMaukkaasti(300);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kunOstaaEdullisenLounaanJaMaksukortillaTarpeeksiRahaaPalautetaanTrue(){
        assertTrue(kassapaate.syoEdullisesti(kortti));   
    }
    
    @Test
    public void kunOstaaEdullisenLounaanJaMaksukortillaTarpeeksiRahaaVeloitetaanSummaKortilta(){
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
    }
    
    @Test
    public void kunOstaaEdullisenLounaanJaMaksukortillaTarpeeksiRahaaMyytyjenEdullistenLounaidenMaaraKasvaa(){
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josYrittaaOstaaEdullisenLounaanmuttaMaksukortillaEiTarpeeksiRahaaNiinKortinRahamaaraEiMuutu(){
        kortti.otaRahaa(900);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void josYrittaaOstaaEdullisenLounaanmuttaMaksukortillaEiTarpeeksiRahaaNiinPalautetaanFalse(){
        kortti.otaRahaa(900);
        assertFalse(kassapaate.syoEdullisesti(kortti));
    }
    
    @Test
    public void josYrittaaOstaaEdullisenLounaanmuttaMaksukortillaEiTarpeeksiRahaaNiinMyytyjenLounaidenMaaraEiMuutu(){
        kortti.otaRahaa(900);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kunOstaaMaukkaanLounaanJaMaksukortillaTarpeeksiRahaaPalautetaanTrue(){
        assertTrue(kassapaate.syoMaukkaasti(kortti));   
    }
    
    @Test
    public void kunOstaaMaukkaanLounaanJaMaksukortillaTarpeeksiRahaaVeloitetaanSummaKortilta(){
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }
    
    @Test
    public void kunOstaaMaukkaanLounaanJaMaksukortillaTarpeeksiRahaaMyytyjenMaukkaidenLounaidenMaaraKasvaa(){
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josYrittaaOstaaMaukkaanLounaanmuttaMaksukortillaEiTarpeeksiRahaaNiinKortinRahamaaraEiMuutu(){
        kortti.otaRahaa(900);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void josYrittaaOstaaMaukkaanLounaanmuttaMaksukortillaEiTarpeeksiRahaaNiinPalautetaanFalse(){
        kortti.otaRahaa(900);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josYrittaaOstaaMaukkaanLounaanmuttaMaksukortillaEiTarpeeksiRahaaNiinMyytyjenLounaidenMaaraEiMuutu(){
        kortti.otaRahaa(900);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortilleRahaaLadattaessaKortinSaldoKasvaa(){
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void kortilleRahaaLadattaessaKassanRahamaaraKasvaa(){
        kassapaate.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void josKortilleYrittaaLadataNegatiivisenSummanKortinSaldoEiMuutu(){
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void josKortilleYrittaaLadataNegatiivisenSummanKassanRahamaaraEiMuutu(){
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
