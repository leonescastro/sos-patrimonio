INSERT INTO public.marca (id, nome) VALUES (nextval('marca_seq'), 'Samsung');
INSERT INTO public.marca (id, nome) VALUES (nextval('marca_seq'), 'Philco');
INSERT INTO public.marca (id, nome) VALUES (nextval('marca_seq'), 'Positivo');
INSERT INTO public.marca (id, nome) VALUES (nextval('marca_seq'), 'AOC');

INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Monitor 20"', 1);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Monitor 20"', 3);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Monitor 28"', 1);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Monitor 28"', 2);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Monitor 28"', 4);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Notebook I5 8GB 17"', 1);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Notebook I5 8GB 17"', 2);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Notebook I5 8GB 17"', 3);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Notebook I5 16GB 20"', 1);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Notebook I5 16GB 20"', 2);
INSERT INTO public.patrimonio (id, nome, marca_id) VALUES (nextval('patrimonio_seq'), 'Notebook I5 16GB 20"', 3);