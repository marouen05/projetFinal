/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.esprit.tests;

import edu.esprit.DAO.ServiceDAO;
import edu.esprit.entities.Service;

/**
 *
 * @author Karray
 */
public class TestAddService {


    public static void main(String[] args) {
        Service service = new Service();
        ServiceDAO serviceDAO = new ServiceDAO();
        service.setLieux("Sfax");
        service.setType("pension");
        service.setInfos("bergé");
        service.setPrix(25);
        serviceDAO.insertService(service);
    }
}
