import React, {useState} from 'react';
import '../../App.css';


function FileUploadPage(){
    const [selectedFile, setSelectedFile] = useState();
	const [isFilePicked, setIsFilePicked] = useState(false);

    const changeHandler = (event) => {
		setSelectedFile(event.target.files[0]);
		setIsFilePicked(true);
	};

    const clearHandler = (event) => {
		setSelectedFile(null);
		setIsFilePicked(false);
	};

	const handleSubmission = () => {
		const formData = new FormData();

		formData.append('File', selectedFile);

		fetch(
			'https://freeimage.host/api/1/upload?key=<YOUR_API_KEY>',
			{
				method: 'POST',
				body: formData,
			}
		)
			.then((response) => response.json())
			.then((result) => {
				console.log('Success:', result);
			})
			.catch((error) => {
				console.error('Error:', error);
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

                
                
                
                
            </div>
            </>
        )
    };
export default FileUploadPage;