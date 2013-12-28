/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azathoth
 */
public enum CollisionCategory {
//    PLAYER((byte)0000000000000001), WORLD((byte)0000000000000010);
//
//    private CollisionCategory(byte b) {
//        this.b=b;
//    }
//    private byte b;
    PLAYER(0x0001), WORLD(0x0002 );
    
        private CollisionCategory(int b) {
        this.b=b;
    }
        public int getCategory() {
        return this.b;
    }
    private int b;
}
