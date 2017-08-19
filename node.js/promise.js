var promise = require('promise');

var squareP = ( p ) =>  {
    return new Promise( ( resolve , reject ) => { 
        setTimeout( () => { 
            if ( p % 2 == 0 ) {
                resolve( p * p ) 
            }
            else {
                reject( p )
            }
        } , 500 )
    });
};

// calling squareP with 2 which is even 
squareP( 2 )
  .then( ( data ) => { console.log( data ) } )
  .catch( ( err ) => console.log(err) );

// calling squareP with 3 which is odd 
squareP( 3 )
  .then( ( data ) => { console.log( data ) } )
  .catch( ( err ) => { console.log( err) })
