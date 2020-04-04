/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.abrams.soen387.repo.core;

/**
 *
 * @author Alex
 */
public class RepositoryException extends Exception {

    public RepositoryException() {
    }
    
    public RepositoryException(String message){
        super(message);
    }
}
