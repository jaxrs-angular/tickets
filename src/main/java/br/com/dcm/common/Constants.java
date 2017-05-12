package br.com.dcm.common;

/**
 * Contantes do sistema.<br>
 * Relacao de constantes usadas no sistema
 * @author Daniel Cristaldo Martinez
 * @since 10/05/2017
 */
public abstract class Constants {
    /**
     * Constantes para banco de dados
     */
    public class db{
        /**
         * Contantes para consulta
         */
        public class querys{
            /**
             * consultas aos tickets
             */
            public class ticket{
                public static final String listUncompleted_id = "query1";
                public static final String listUncompleted_sql = "from Issue i where i.workComplete=null";
            }
        }
    }
}
