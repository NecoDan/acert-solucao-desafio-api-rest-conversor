create database conversor_temperaturas;
\c conversor_temperaturas

create schema if not exists conversor_service;
set schema 'conversor_service';

--////////////////////////////////////s/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
create sequence conversor_service.fd01_escala_termometrica_id_seq;

create table if not exists conversor_service.fd01_escala_termometrica
(
    id             bigint                      not null DEFAULT nextval('conversor_service.fd01_escala_termometrica_id_seq'),
    descricao      varchar(200)                null,
    ponto_fusao    decimal(19, 6) default 0    not null,
    ponto_ebulicao decimal(19, 6) default 0    not null,
    data           timestamp      default now(),
    ativo          boolean        default true null,
    primary key (id)
);

create unique index uq_fd01_escala_termometrica on conversor_service.fd01_escala_termometrica (id);
alter table conversor_service.fd01_escala_termometrica
    owner to postgres;

--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
create sequence conversor_service.fd02_historico_id_seq;

create table if not exists conversor_service.fd02_historico
(
    id                   bigint                       not null DEFAULT nextval('conversor_service.fd02_historico_id_seq'),
    id_escala_origem     bigint                       not null,
    id_escala_destino    bigint                       not null,
    valor_grau_origem    decimal(19, 6) default 0.0   not null,
    valor_grau_resultado decimal(19, 6) default 0.0   not null,
    data                 timestamp      default now() not null,
    ativo                boolean        default true  null,
    primary key (id)
);
create unique index uq_fd02_historico on conversor_service.fd02_historico (id);

alter table conversor_service.fd02_historico
    add constraint fd02_historico_id_escala_origem_fkey foreign key (id_escala_origem) references conversor_service.fd01_escala_termometrica (id);
alter table conversor_service.fd02_historico
    add constraint fd02_historico_id_escala_destino_fkey foreign key (id_escala_destino) references conversor_service.fd01_escala_termometrica (id);

alter table conversor_service.fd02_historico
    owner to postgres;

--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
--/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
--> INSERÇÕES DE REGISTROS DEFAULT NAS TABELAS

insert into conversor_service.fd01_escala_termometrica (descricao, ponto_fusao, ponto_ebulicao)
values ('Celsius', 0.0, 32.0);

insert into conversor_service.fd01_escala_termometrica (descricao, ponto_fusao, ponto_ebulicao)
values ('Fahrenheit', 32.0, 212.0);

insert into conversor_service.fd01_escala_termometrica (descricao, ponto_fusao, ponto_ebulicao)
values ('Kelvin', 273.0, 373.0);
