CREATE TYPE MESSAGE_TYPE AS ENUM ('OFFER');

CREATE TABLE messages
(
    id      UUID                     NOT NULL PRIMARY KEY,
    ad_id   UUID                     NOT NULL REFERENCES ads (id),
    type    MESSAGE_TYPE             NOT NULL,
    content TEXT                     NOT NULL,
    sent_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW()
);
