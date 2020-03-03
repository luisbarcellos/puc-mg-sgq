
CREATE TABLE public.incidente (
	id_incidente serial NOT NULL,
	tipo varchar NOT NULL,
	gravidade varchar NOT NULL,
	descricao varchar NOT NULL,
	data_inclusao date NOT NULL,
	CONSTRAINT incidente_pk PRIMARY KEY (id_incidente)
);

CREATE TABLE public.problema (
	id_problema serial NOT NULL,
	tipo varchar NOT NULL,
	gravidade varchar NOT NULL,
	descricao varchar NOT NULL,
	data_inclusao date NOT NULL,
	CONSTRAINT problema_pk PRIMARY KEY (id_problema)
);

CREATE TABLE public.risco_acidente (
	id_risco_acidente serial NOT NULL,
	tipo varchar NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT risco_acidente_pk PRIMARY KEY (id_risco_acidente)
);

CREATE TABLE public.produto (
	id_produto serial NOT NULL,
	nome varchar NOT NULL,
	tipo varchar NOT NULL,
	descricao varchar NOT NULL,
	CONSTRAINT produto_pk PRIMARY KEY (id_produto)
);

CREATE TABLE public.nao_conformidade (
	id_nao_conformidade serial NOT NULL,
	tipo varchar NOT NULL,
	descricao varchar NOT NULL,
	id_risco_acidente int4 NULL,
	id_problema int4 NULL,
	CONSTRAINT nao_conformidade_pk PRIMARY KEY (id_nao_conformidade),
	CONSTRAINT nao_conformidade_fk FOREIGN KEY (id_risco_acidente) REFERENCES risco_acidente(id_risco_acidente),
	CONSTRAINT nao_conformidade_problema_fk FOREIGN KEY (id_problema) REFERENCES problema(id_problema)
);

CREATE TABLE public.problema_incidente (
	id_problema int4 NOT NULL,
	id_incidente int4 NOT NULL,
	CONSTRAINT problema_incidente_composte_pk PRIMARY KEY (id_problema, id_incidente),
	CONSTRAINT incidente_fk FOREIGN KEY (id_incidente) REFERENCES incidente(id_incidente),
	CONSTRAINT problema_fk FOREIGN KEY (id_problema) REFERENCES problema(id_problema)
);

CREATE TABLE public.incidente_produto (
	id_produto int4 NOT NULL,
	id_incidente int4 NOT NULL,
	CONSTRAINT incidente_produto_composte_pk PRIMARY KEY (id_produto, id_incidente),
	CONSTRAINT incidente_fk FOREIGN KEY (id_incidente) REFERENCES incidente(id_incidente),
	CONSTRAINT produto_fk FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);
