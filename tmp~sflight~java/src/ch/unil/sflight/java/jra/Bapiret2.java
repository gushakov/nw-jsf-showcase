package ch.unil.sflight.java.jra;

import java.io.Serializable;

/*
BAPIRET2
TYPE	Types	BAPI_MTYPE	CHAR	1	0	Type msg : S succès, E erreur, W avert., I info, A abandon
ID	Types	SYMSGID	CHAR	20	0	Classe de messages
NUMBER	Types	SYMSGNO	NUMC	3	0	Numéro du message
MESSAGE	Types	BAPI_MSG	CHAR	220	0	Texte du message
LOG_NO	Types	BALOGNR	CHAR	20	0	Journal des applications : numéro de journal
LOG_MSG_NO	Types	BALMNR	NUMC	6	0	Protocole d'application : numéro courant interne du message
MESSAGE_V1	Types	SYMSGV	CHAR	50	0	Variable de message
MESSAGE_V2	Types	SYMSGV	CHAR	50	0	Variable de message
MESSAGE_V3	Types	SYMSGV	CHAR	50	0	Variable de message
MESSAGE_V4	Types	SYMSGV	CHAR	50	0	Variable de message
PARAMETER	Types	BAPI_PARAM	CHAR	32	0	Nom de paramètre
ROW	Types	BAPI_LINE	INT4	10	0	Ligne dans paramètre
FIELD	Types	BAPI_FLD	CHAR	30	0	Zone dans paramètre
SYSTEM	Types	BAPILOGSYS	CHAR	10	0	Système (système logique) d'origine du message
 */


public class Bapiret2 implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
