import React, {useState, useEffect} from 'react';


function TableView(){
        const [data, setData] = useState();
        const [timeIncrease, setTimeIncrease] = useState();
        
        const columns = [{  
            Header: 'id'
          },{  
            Header: 'Arquivo'
          },{  
            Header: 'Numero'
          },{  
            Header: 'Nome Emitente'
          },{  
            Header: 'Nome Destinatário'
          },{  
            Header: 'Data Envio'
          },{  
            Header: 'Valor'
          },{  
            Header: 'Status'
          }];        
        
        const handleNfeList = () => {
      
          fetch(
            'http://localhost:8083/nfes',
            {
              method: 'GET',             
                      mode: 'cors',   
                      headers: {
                      'Accept': 'application/json',                               
                      'Access-Control-Allow-Origin':'*'
                      }
            }
          ).then( (response) => {
                  if (!response.ok)  
                      return Promise.reject(new Error(response.status.toString())); //Write your own error msg here
                  return response.json();
                      
                  })
            .then((result) => {
              setData(result);
            })
            .catch((error) => {
              setData([]);
            });
        };      

        useEffect(()=>{          
          const intervalId = setInterval(() => {
            setTimeIncrease(timeIncrease + 1);
            handleNfeList();
          }, 10000);
          return () => clearInterval(intervalId);
        },[timeIncrease]);

        

  return (
    <>
      <table id="notaFiscal">
      <thead>        
            <tr>
              {columns.map((column,indiceColumn) => (
                <th key={indiceColumn}>
                  {column['Header']}
                </th>
              ))}
            </tr>
        
        </thead>
        <tbody>
        {data && data.map(element => (
            <tr key={element.id}>

                <td>{element.id}</td>
                <td>{element.fileNameOriginal}</td>
                <td>{element.numero}</td>
                <td>{element.nomeEmitente}</td>
                <td>{element.nomeDestinatario}</td>
                <td>{element.dhRegistro}</td>
                <td>{element.valorNotaFiscal}</td>
                <td>{element.status}</td>
            </tr>
        ))}
        </tbody>

    </table>
    </>
  );
}  
  
export default TableView;  