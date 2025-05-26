package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.FuenteDatosFicheros;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb.MariaDB;

public enum FabricaFuenteDatos {

    FICHEROS {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosFicheros();
        }
    },

    MARIADB {
                public IFuenteDatos crear(){
            return new FuenteDatosFicheros();
        }
    },
    MONGODB {
        public IFuenteDatos crear(){
            return new FuenteDatosFicheros();
        }
    };


    public abstract IFuenteDatos crear();
}
