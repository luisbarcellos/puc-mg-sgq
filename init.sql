
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

INSERT INTO public.produto (nome,tipo,descricao) VALUES
('Air Bag','Motorista','Modelo Astra 2004/2009')
,('Rádio Aiko','Central multimídia','Honda Civic 2008/2011')
,('Cinto de segurança','Traseiro esquero','Veículos da Chevrolet')
,('Vidro','Dianteiro','Parabrisa Gol G5')
;

INSERT INTO public.incidente (tipo,gravidade,descricao,data_inclusao) VALUES
('Vidro trincado','grave','Vidro trinca ao manuseio.','2020-03-16')
,('Air Bag estoura','Grave','Air Bag estoura ao embalar.','2020-03-16')
;

INSERT INTO public.incidente_produto (id_produto,id_incidente) VALUES
(4,1)
,(1,2)
;

INSERT INTO public.problema (tipo,gravidade,descricao,data_inclusao) VALUES
('Sensor de airbag muito sensível','gravíssimo','Sensor para acionar o airbag possui muita sensibilidade.','2020-03-16')
,('Vidro frágil','gravíssimo','Vidro frágil para suportar altas velocidades.','2020-03-16')
;

INSERT INTO public.risco_acidente (tipo,descricao) VALUES
('Recall vidro','Possível acidente aos passageiros.')
,('Recall airbag','Possível prejudicar motorista ao conduzir veículo.')
;

INSERT INTO public.problema_incidente (id_problema,id_incidente) VALUES
(1,2)
,(2,1)
;

INSERT INTO public.nao_conformidade (tipo,descricao,id_risco_acidente,id_problema) VALUES
('Veículo','Não apto para utilizar vidros fabricados.',1,2)
,('Veículo','Não seguro em acidentes',2,NULL)
;
