CREATE TABLE tbl_skills (
	skill_cd_id uuid NOT NULL,
	skill_tx_description varchar(150) NOT NULL,
	skill_tx_image varchar(300) NOT NULL,
	skill_tx_skill_name varchar(255) NOT NULL,
	CONSTRAINT tbl_skills_pkey PRIMARY KEY (skill_cd_id),
	CONSTRAINT tbl_skills_skill_tx_skill_name_key UNIQUE (skill_tx_skill_name)

);

CREATE TABLE tbl_users (
    user_cd_id uuid NOT NULL,
    user_cd_access_type int2 NOT NULL,
    user_tx_password varchar(255) NOT NULL,
    user_tx_username varchar(255) NOT NULL,
    CONSTRAINT tbl_users_pkey PRIMARY KEY (user_cd_id),
    CONSTRAINT tbl_users_user_int_access_type_check CHECK (((user_int_access_type >= 0) AND (user_int_access_type <= 1))),
    CONSTRAINT tbl_users_user_tx_username_key UNIQUE (user_tx_username)
);

CREATE TABLE tbl_user_skill (
	user_skill_int_level int4 NOT NULL,
	skill_cd_id uuid NOT NULL,
	user_cd_id uuid NOT NULL,
	user_skill_cd_id uuid NOT NULL,
	CONSTRAINT tbl_user_skill_pkey PRIMARY KEY (user_skill_cd_id)
);
