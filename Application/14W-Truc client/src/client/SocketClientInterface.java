/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author TrucHuynh
 */
public interface SocketClientInterface {    
    boolean openConnection();
    void handleSession();
    void closeSession();
}
