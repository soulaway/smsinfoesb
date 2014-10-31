CREATE TABLE sms_info.dbo.SMS (
id bigint IDENTITY,
  endDateTime datetime2 NULL,
  failReason nvarchar(35) NULL,
  incomingId nvarchar(32) NULL,
  isStateChanged bit NOT NULL,
  phoneNumber nvarchar(13) NULL,
  senderGroupId nvarchar(20) NULL,
  senderId nvarchar(32) NULL,
  senderName nvarchar(20) NULL,
  state int NULL,
  systemName nvarchar(20) NULL,
  text nvarchar(255) NULL,
  PRIMARY KEY (id)
) ON [PRIMARY]
GO