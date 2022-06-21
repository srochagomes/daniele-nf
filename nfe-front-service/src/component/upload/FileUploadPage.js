import React, {useState} from 'react';
import '../../App.css';


function FileUploadPage(){
    const [selectedFile, setSelectedFile] = useState();
	const [isFilePicked, setIsFilePicked] = useState(false);
    const [statusFile, setStatusFile] = useState();

    const changeHandler = (event) => {
		setSelectedFile(event.target.files[0]);
		setIsFilePicked(true);
        setStatusFile(null);
	};

    const clearHandler = (event) => {
		clearHandlerButton(event);
        setStatusFile(null);
	};

    const clearHandlerButton = (event) => {
		setSelectedFile(null);
		setIsFilePicked(false);        
	};


	const handleSubmission = () => {
		const formData = new FormData();

		formData.append('file', selectedFile);

		fetch(
			'http://localhost:8083/nfes/import',
			{
				method: 'POST',             
                mode: 'cors',   
                headers: {
                'Accept': 'application/json',                               
                'Access-Control-Allow-Origin':'*'
                },
				body: formData
			}
		).then( (response) => {
            if (!response.ok)  
                return Promise.reject(new Error(response.status.toString())); //Write your own error msg here
            return response.json;
                
            })
			.then((result) => {
                setStatusFile('OK');				
                clearHandlerButton();
			})
			.catch((error) => {
				setStatusFile('FAIL');
                clearHandlerButton();
			});
	};
	

        return(
            <>
        <div className='left-align'>
                
                
                {isFilePicked ? (
                    <div>
                        <p>Arquivo: {selectedFile.name}</p>
                        
                    </div>
                ) : (
                    <p>Selecione um arquivo para enviar</p>                    
                )}

                {isFilePicked ? (
                    <div>
                        <div><button name="newfile" onClick={clearHandler} >Limpar</button></div>
                        <div><button name="enviar" onClick={handleSubmission}>Enviar</button></div>
                </div>
                    
                ) : (
                    <input type="file" name="file" onChange={changeHandler} ></input>
                )}
                {(statusFile === 'OK') ? <div>Envio com sucesso!</div>
                 : (statusFile === 'FAIL') ? <div>Não foi posssivel enviar o arquivo!</div>:<div></div>}


                
                
                
                
            </div>
            </>
        )
    };
export default FileUploadPage;