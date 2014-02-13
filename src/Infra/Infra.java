package Infra;

import Logic.Managers.ClienteManager;
import Logic.Managers.LeilaoManager;


public interface Infra {
    ClienteManager readCliList();
    LeilaoManager readLeiList();
    void saveCli(ClienteManager c);
    void saveLei(LeilaoManager l);
}
