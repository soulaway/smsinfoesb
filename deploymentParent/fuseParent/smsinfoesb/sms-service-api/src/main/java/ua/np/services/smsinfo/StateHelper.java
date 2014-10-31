package ua.np.services.smsinfo;
/*
 *     GSMU states API
		0	– ще не відправлено;
		1	– відправлено;
		2	– успішно відправлено (тобто отримано звіт про прийняття повідомлення від СМСЦ);
		3	– відправка відхилена.;
		4	– повідомлення  доставлене;
		5	- повідомлення недоставлене.

		У випадку коли повідомлення знаходиться у статусі «3» чи «5». Тег REASON буде містить пояснення причини статуса повідомлення. Він може принймати наступні значення:
		-	«Internal error: Timeout»;
		-	«Internal error: Invalid message»;
		-	«Internal error: Unknown»;
		-	«External error: Enroute»;
		-	«External error: Expired»;
		-	«External error: Deleted»;
		-	«External error: Undeliverable»;
		-	«External error: Rejected»;
		-	«External error: Unknown»;
*/

public enum StateHelper {
	STATE_PENDING{
		public int value() { return -1;}
	},
	STATE_SENDING{
		public int value() { return 0;}
	},
	STATE_SENDED_TO_SMSC{
		public int value() { return 1;}
	},
	STATE_SMSC_SUCCESS_RESPONDED{
		public int value() { return 2;}
	},
	STATE_SEND_REJECTED{
		public int value() { return 3;}
	},
	STATE_DELIVERED{
		public int value() { return 4;}
	},
	STATE_NOT_DELIVERED{
		public int value() { return 5;}
	};
	
	public abstract int value();
}
