ALTER TABLE orchestrator.orc_service MODIFY COLUMN api_body JSON NULL;
ALTER TABLE orchestrator.orc_service MODIFY COLUMN params JSON NULL;
commit;

ALTER TABLE orchestrator.orc_service MODIFY COLUMN api_body JSON NULL;
ALTER TABLE orchestrator.orc_service MODIFY COLUMN params JSON NULL;
commit;



INSERT INTO orc_process
(process_id, description, enabled, long_description, process_name, `type`)
VALUES(1, 'Matriculas', 1, 'Matriculas', 'EDU_MATRICULAS', 'B');


INSERT INTO orc_service
(service_id, api_body, api_name, api_path, description, enabled, endpoint, long_description, mandatory, `method`, ord, params, `type`, process_id)
VALUES(1, NULL, 'Consulta obligación', '/api/v2/credito/obligaciones/?tipoId=4&numeroId=1023600200', 'Consulta obligación', 1, NULL, 'Consulta obligación', 1, 'GET', 3, NULL, 'APIGEE', 1);



INSERT INTO orc_service
(service_id, api_body, api_name, api_path, description, enabled, endpoint, long_description, mandatory, `method`, ord, params, `type`, process_id)
VALUES(2, 
'[{"informacionPersonal":{"correoElectronico":"(informacionPersonal.correoElectronico)","clase":"1","documento":{"numero":"(informacionPersonal.documento.numero)","tipo":"1"},"celular":"3015600418","emisor":{"documento":{"numero":"8600073361"}}},"personaNatural":{"fechaNacimiento":"1982-04-15","nacionalidad":"14","direccion":{"ciudad":{"codigo":"150"},"departamento":{"codigo":"5"},"pais":{"codigo":"14"}},"nombre":{"primero":"(informacionPersonal.nombre)","primerApellido":"(informacionPersonal.apellido)","segundoApellido":"(informacionPersonal.segundoApellido)"},"documento":{"expedicion":{"fecha":"2000-06-13","direccion":{"ciudad":{"codigo":"150"},"departamento":{"codigo":"5"},"pais":{"codigo":"14"}}}}},"grupoPersona":[{"direccion":{"direccionCompleta":"Cra 88 A # 21 75"},"telefono":"4665803"}],"mensajeRespuesta":""}]'
, 'Crear girador', '/api/v1/credito/pagares/giradores', 'Crear girador', 1,
NULL, 
'Crear girador', 1, 'POST', 1, '{"informacionPersonal":{"correoElectronico":"oscar@microservicios.co","nombre":"Oscar Ivan","apellido":"Estrada","segundoApellido":"Gonzalez","documento":{"numero":"93061374","tipo":"1"}}}', 'APIGEE', 1);


INSERT INTO orc_service
(service_id, api_body, api_name, api_path, description, enabled, endpoint, long_description, mandatory, `method`, ord, params, `type`, process_id)
VALUES(3, NULL, 'Consulta afiliado', '/api/v2/afiliacion/validador?tipoId=CO1C&numeroId=1049795883', 'Consulta afiliado', 1, NULL, 'Consulta afiliado', 1, 'GET', 20, NULL, 'APIGEE', 1);


INSERT INTO orc_service
(service_id, api_body, api_name, api_path, description, enabled, endpoint, long_description, mandatory, `method`, ord, params, `type`, process_id)
VALUES(4, NULL, 'Token enrollment-manager', '/v1.0/login'
, 'Token enrollment-manager', 1, 'https://app-edu-enrollment-manager-api-develop.azurewebsites.net', 'Token enrollment-manager', 1, 'POST', 5, NULL, 'REST', 1);
