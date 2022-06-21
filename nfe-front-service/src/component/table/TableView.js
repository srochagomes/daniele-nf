import React from "react";

function TableView(){
        const data = [{  
            name: 'Ayaan',  
            age: 26  
            },{  
            name: 'Ahana',  
            age: 22  
            },{  
            name: 'Peter',  
            age: 40      
            },{  
            name: 'Virat',  
            age: 30  
            },{  
            name: 'Rohit',  
            age: 32  
            },{  
            name: 'Dhoni',  
            age: 37  
            }]  
        const columns = [{  
        Header: 'Name',  
        accessor: 'name'  
        },{  
        Header: 'Age',  
        accessor: 'age'  
        }];        
        
        


  return (
    <>
      <table id="notaFiscal">
      <thead>        
            <tr>
              {columns.map(column => (
                <th>
                  {column['Header']}
                </th>
              ))}
            </tr>
        
        </thead>
        {data.map(element => (
            <tr>
                <td>{element.name}</td>
                <td>{element.age}</td>                
            </tr>
        ))}
        
    </table>
    </>
  );
}  
  
export default TableView;  