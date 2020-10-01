--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: climbingsite; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public.climbingsite (
    id integer NOT NULL,
    name character varying(150) NOT NULL,
    region character varying NOT NULL,
    climbing_type character varying(100),
    rock_type character varying(100),
    height character varying(100),
    nb_routes integer NOT NULL,
    quotation_min character varying NOT NULL,
    quotation_max character varying NOT NULL,
    info character varying,
    is_certified boolean NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.climbingsite OWNER TO nicoe;

--
-- Name: climbingsite_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.climbingsite_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.climbingsite_id_seq OWNER TO nicoe;

--
-- Name: climbingsite_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.climbingsite_id_seq OWNED BY public.climbingsite.id;


--
-- Name: comment; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    comment_text character varying(6000) NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    update_date timestamp without time zone,
    climbingsite_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.comment OWNER TO nicoe;

--
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.comment_id_seq OWNER TO nicoe;

--
-- Name: comment_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.comment_id_seq OWNED BY public.comment.id;


--
-- Name: guidebook; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public.guidebook (
    id integer NOT NULL,
    name character varying(400) NOT NULL,
    description character varying NOT NULL,
    region character varying NOT NULL,
    release_date date NOT NULL,
    is_booked boolean NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.guidebook OWNER TO nicoe;

--
-- Name: guidebook_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.guidebook_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.guidebook_id_seq OWNER TO nicoe;

--
-- Name: guidebook_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.guidebook_id_seq OWNED BY public.guidebook.id;


--
-- Name: guidebook_reservation; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public.guidebook_reservation (
    id integer NOT NULL,
    reservation_status character varying NOT NULL,
    guidebook_id integer NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.guidebook_reservation OWNER TO nicoe;

--
-- Name: guidebook_reservation_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.guidebook_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.guidebook_reservation_id_seq OWNER TO nicoe;

--
-- Name: guidebook_reservation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.guidebook_reservation_id_seq OWNED BY public.guidebook_reservation.id;


--
-- Name: photo; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public.photo (
    id integer NOT NULL,
    name character varying NOT NULL,
    url character varying NOT NULL,
    climbingsite_id integer NOT NULL
);


ALTER TABLE public.photo OWNER TO nicoe;

--
-- Name: photo_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.photo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.photo_id_seq OWNER TO nicoe;

--
-- Name: photo_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.photo_id_seq OWNED BY public.photo.id;


--
-- Name: route; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public.route (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    quotation character varying NOT NULL,
    height character varying(100) NOT NULL,
    sector_id integer NOT NULL
);


ALTER TABLE public.route OWNER TO nicoe;

--
-- Name: route_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.route_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.route_id_seq OWNER TO nicoe;

--
-- Name: route_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.route_id_seq OWNED BY public.route.id;


--
-- Name: sector; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public.sector (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying NOT NULL,
    climbingsite_id integer NOT NULL
);


ALTER TABLE public.sector OWNER TO nicoe;

--
-- Name: sector_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.sector_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sector_id_seq OWNER TO nicoe;

--
-- Name: sector_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.sector_id_seq OWNED BY public.sector.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: nicoe
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    gender character varying NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    username character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    address character varying(200) NOT NULL,
    address2 character varying(200),
    zip character varying(100) NOT NULL,
    city character varying(100) NOT NULL,
    role character varying NOT NULL
);


ALTER TABLE public."user" OWNER TO nicoe;

--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: nicoe
--

CREATE SEQUENCE public.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO nicoe;

--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: nicoe
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- Name: climbingsite id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.climbingsite ALTER COLUMN id SET DEFAULT nextval('public.climbingsite_id_seq'::regclass);


--
-- Name: comment id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.comment ALTER COLUMN id SET DEFAULT nextval('public.comment_id_seq'::regclass);


--
-- Name: guidebook id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.guidebook ALTER COLUMN id SET DEFAULT nextval('public.guidebook_id_seq'::regclass);


--
-- Name: guidebook_reservation id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.guidebook_reservation ALTER COLUMN id SET DEFAULT nextval('public.guidebook_reservation_id_seq'::regclass);


--
-- Name: photo id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.photo ALTER COLUMN id SET DEFAULT nextval('public.photo_id_seq'::regclass);


--
-- Name: route id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.route ALTER COLUMN id SET DEFAULT nextval('public.route_id_seq'::regclass);


--
-- Name: sector id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.sector ALTER COLUMN id SET DEFAULT nextval('public.sector_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- Data for Name: climbingsite; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public.climbingsite (id, name, region, climbing_type, rock_type, height, nb_routes, quotation_min, quotation_max, info, is_certified, user_id) FROM stdin;
1	Falaises Buoux	Provence-Alpes-Côte-d’Azur	Couenne	Calcaire	de 15m à 80m	500	4b	8c+	Buoux coule des jours heureux comme temple du plaisir de la grimpe avec environ 500 longueurs d’escalade pour sexto et septogradistes.	t	1
2	Claret	Occitanie	Couenne	Calcaire	de 15m à 35m	100	5c	8c	À 30 km au nord de Montpellier, Claret est une falaise mythique du sud de la France. On y trouve de jolies dalles, et la majorité des voies se situent entre 6b+ et 7b+.	t	1
4	Pen Hir	Bretagne	Couenne	Grès	de 10m à 70m	130	3	6a	Pen-Hir se trouve à l’extrémité de la Bretagne, au bout de la presqu’île de Crozon, au-dessus des flots de la mer d’Iroise… De très nombreuses voies sportives ou en terrain d’aventure remontent les dalles, les fissures et les dièdres de ces remparts de roc. Tous les secteurs, nombreux, offrent des voies abordables. Ainsi, tout le monde, même sans avoir un gros niveau, peut s’offrir les sensations grisantes d’une voie à Pen-Hir et de sa mémorable descente en rappel vers le grand bleu.	f	2
3	Ablon	Auvergne-Rhône-Alpes	Couenne	Calcaire	de 15m à 80m	400	4b	8b	Ablon, c’est la falaise d’alpages, nichée à l’écart, dans un recoin sauvage et préservé en plein cœur du massif des Bornes. Sur ses fantastiques dalles grises très sculptées, où le rocher est beau partout, l’escalade présente une variété de styles avec prédominance de grandes envolées en 6b, 6c ou 7a, tout en continuité. On doit plus des deux tiers de ces fabuleuses lignes à Robert Durieux, équipeur motivé et amoureux des lieux depuis au moins trente ans. \r\nAblon, c’est le site majeur de Haute Savoie pour le cadre, la qualité des lignes et du rocher.	t	1
\.


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public.comment (id, comment_text, creation_date, update_date, climbingsite_id, user_id) FROM stdin;
1	Magnifique !	2020-09-30 16:01:55.595	\N	1	1
2	Vues splendides ! 	2020-10-01 14:32:16.048	\N	4	2
\.


--
-- Data for Name: guidebook; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public.guidebook (id, name, description, region, release_date, is_booked, user_id) FROM stdin;
1	49 falaises Robert Durieux	Le topo 49 falaises autour d'Annecy par Robert Durieux offre une multitude d'options d'escalade dans des lieux uniques pour tout les niveaux. Près de 1800 Longueurs du 3a au 9a aux environs du lacs d'Annecy.	Auvergne-Rhône-Alpes	2017-03-21	t	1
2	Buoux	Topo sur les falaises buoux	Provence-Alpes-Côte-d’Azur	2015-02-12	f	2
3	Escalade à Pen-Hir, CD FFME 29, édition 2015.	Topo sur l'escalade à Pen-Hir	Bretagne	2015-02-28	f	2
4	Claret, P.Rouzo, 2011.	Topo falaises de Claret.	Occitanie	2011-04-03	f	1
\.


--
-- Data for Name: guidebook_reservation; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public.guidebook_reservation (id, reservation_status, guidebook_id, user_id) FROM stdin;
1	Acceptée	1	2
2	En attente	3	4
\.


--
-- Data for Name: photo; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public.photo (id, name, url, climbingsite_id) FROM stdin;
1	Falaises Buoux	/resources/img/buoux.jpg	1
2	Claret	/resources/img/claret.jpg	2
3	Ablon	/resources/img/ablon.jpg	3
4	Pen Hir	/resources/img/pen-hir.jpg	4
\.


--
-- Data for Name: route; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public.route (id, name, quotation, height, sector_id) FROM stdin;
1	Orpierre	6a	à noter	1
2	voie niveau 1	3	15 mètres	3
3	voie niveau 2	4a+	20 mètres	3
4	Voie niveau 4	5a	60 mètres	2
5	Voie niveau 4	5a	30 mètres	4
6	Voie niveau 7	7a+	40 mètres	4
7	voie de niveau 7	7a	50 mètres	5
8	voie de niveau 8	8c	80 mètres	5
9	L1	6a	60 mètres	1
10	L2	6b	85 mètres	1
11	L3	7a+	95 mètres	1
\.


--
-- Data for Name: sector; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public.sector (id, name, description, climbingsite_id) FROM stdin;
2	La grande falaise	Falaise juste en dessous de la croix de lorraine regroupant plusieurs voies de 2 à 3 longueurs.	4
3	Dalle des débutants	Secteur regroupant des voies de couennes faciles.	4
4	Carvanicole	Situé juste à droite du secteur des toits, il offre de très nombreuses voies en 7a-7b.	2
5	Moulinette	Pour les plus expérimentés. De nombreuse voie de 7a à 8c.	3
1	La gougousse	Départ droit au-dessus de l'inscription "le roi de la jungle" (cette dernière part à droite).\r\n\r\n	1
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: nicoe
--

COPY public."user" (id, gender, first_name, last_name, username, email, password, address, address2, zip, city, role) FROM stdin;
1	M	Nicolas	Evangelista	Nicoe	hooka33@gmail.com	$2a$10$8plntBIV69VPTOOps5sQau5Jz0NxFt9xfpEkpubB0ZReNCH55saLq	5 rue des colombes		33310	Lormont	Admin
3	M	Arnaud	Belfort	ArnaudB	arnaud.belfort@gmail.com	$2a$10$RVTNuPrTkUO7Uejb8MHLlOT2dwtUekTJUSwZ8QjAH4Uie/1thi7Yq	12 avenue saint laurent		44300	Nantes	User
2	M	David	Silva	Dav33	david.evangelista01@gmail.com	$2a$10$Xs6IhCG3VFMoIwTMOWfPVOzX.HcIui4L6cDU8MJ8R6Bs5L7WYnfsC	32 rue des chalets		33150	Cenon	Member
4	M	Patrick	Dupond	Pepito	patrick.dupond@gmail.com	$2a$10$ivIiZk3kemlNbqZUS68tB.2IGzHKMwvHRfC4X9vfeFZtML.5PyO6q	12 rue Belpied		69400	Arnas	User
\.


--
-- Name: climbingsite_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.climbingsite_id_seq', 5, true);


--
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.comment_id_seq', 2, true);


--
-- Name: guidebook_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.guidebook_id_seq', 4, true);


--
-- Name: guidebook_reservation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.guidebook_reservation_id_seq', 2, true);


--
-- Name: photo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.photo_id_seq', 4, true);


--
-- Name: route_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.route_id_seq', 11, true);


--
-- Name: sector_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.sector_id_seq', 5, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: nicoe
--

SELECT pg_catalog.setval('public.user_id_seq', 4, true);


--
-- Name: climbingsite climbingsite_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.climbingsite
    ADD CONSTRAINT climbingsite_pk PRIMARY KEY (id);


--
-- Name: comment comment_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pk PRIMARY KEY (id);


--
-- Name: guidebook guidebook_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.guidebook
    ADD CONSTRAINT guidebook_pk PRIMARY KEY (id);


--
-- Name: guidebook_reservation guidebook_reservation_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.guidebook_reservation
    ADD CONSTRAINT guidebook_reservation_pk PRIMARY KEY (id);


--
-- Name: photo photo_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT photo_pk PRIMARY KEY (id);


--
-- Name: route route_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT route_pk PRIMARY KEY (id);


--
-- Name: sector sector_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.sector
    ADD CONSTRAINT sector_pk PRIMARY KEY (id);


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY (id);


--
-- Name: user_idx; Type: INDEX; Schema: public; Owner: nicoe
--

CREATE UNIQUE INDEX user_idx ON public."user" USING btree (username, email);


--
-- Name: comment climbingsite_comment_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT climbingsite_comment_fk FOREIGN KEY (climbingsite_id) REFERENCES public.climbingsite(id);


--
-- Name: photo climbingsite_photo_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.photo
    ADD CONSTRAINT climbingsite_photo_fk FOREIGN KEY (climbingsite_id) REFERENCES public.climbingsite(id);


--
-- Name: sector climbingsite_sector_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.sector
    ADD CONSTRAINT climbingsite_sector_fk FOREIGN KEY (climbingsite_id) REFERENCES public.climbingsite(id);


--
-- Name: guidebook_reservation guidebook_guidebook_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.guidebook_reservation
    ADD CONSTRAINT guidebook_guidebook_reservation_fk FOREIGN KEY (guidebook_id) REFERENCES public.guidebook(id);


--
-- Name: route sector_route_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.route
    ADD CONSTRAINT sector_route_fk FOREIGN KEY (sector_id) REFERENCES public.sector(id);


--
-- Name: climbingsite user_climbingsite_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.climbingsite
    ADD CONSTRAINT user_climbingsite_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: comment user_comment_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT user_comment_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: guidebook user_guidebook_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.guidebook
    ADD CONSTRAINT user_guidebook_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- Name: guidebook_reservation user_guidebook_reservation_fk; Type: FK CONSTRAINT; Schema: public; Owner: nicoe
--

ALTER TABLE ONLY public.guidebook_reservation
    ADD CONSTRAINT user_guidebook_reservation_fk FOREIGN KEY (user_id) REFERENCES public."user"(id);


--
-- PostgreSQL database dump complete
--

