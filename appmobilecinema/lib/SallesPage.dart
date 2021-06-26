import 'package:appmobilecinema/GlobalVariables.dart';
import 'package:appmobilecinema/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class SallesPage extends StatefulWidget {
  dynamic cinema;
  SallesPage(this.cinema);
  @override
  _SallesPageState createState() => _SallesPageState();
}

class _SallesPageState extends State<SallesPage> {
  List<dynamic> listSalles;
  List<dynamic> listProjections;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Salles de ${widget.cinema['name']}'),),
      body: Center(
        child: Center(
          child: (this.listSalles==null?CircularProgressIndicator():
          ListView.builder(
            itemCount: this.listSalles==null?0:this.listSalles.length,
              itemBuilder: (context,index){
              return  Card(
                color: Colors.white,
                child: Column(
                  children: <Widget>[
                    Container(
                      width: double.infinity,
                      child: Padding(
                        padding: const EdgeInsets.all(8.0),
                        child: RaisedButton(
                          color: Colors.orange,
                          child: Text(this.listSalles[index]['name'],style: TextStyle(color: Colors.white),),
                          onPressed: (){
                            loadProjction(this.listSalles[index]);
                          },
                        ),
                      ),
                    ),
                    if(this.listSalles[index]['projections']!=null)
                    Padding(
                      padding: const EdgeInsets.all(8.0),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget>[
                          Image.network(GlobalData.host+"/imageFilme/${this.listSalles[index]['currentprojection']['filme']['id']}",width: 150),
                         Column(
                           children: <Widget>[
                             ...(this.listSalles[index]['projections'] as List<dynamic>).map((projection){
                               return RaisedButton(
                                 color: (this.listSalles[index]['currentprojection']['id']==projection['id'])?Colors.deepOrange:Colors.green,
                                 child: Text("${projection['seance']['heurdebut']} -  ( ${projection['filme']['duree']} ) Prix :  ${projection['prix'] }"
                                   ,style: TextStyle(color: Colors.white,fontSize: 12),),
                               onPressed: (){
                                   loadTickets(projection,this.listSalles[index]);
                               },
                               );
                             })
                           ],
                         )
                        ],
                      ),
                    ),

                   if( this.listSalles[index]['currentprojection']!=null &&
                        this.listSalles[index]['currentprojection']['listTickets']!=null &&
                        this.listSalles[index]['currentprojection']['listTickets'].length>0
                     )
                     Column(children: <Widget>[
                       Row(
                         children: <Widget>[
                           Text("Nombre de place :${this.listSalles[index]['currentprojection']['nombrePlaceDisponible']}")
                         ],
                       ),
                       Container(
                         padding: EdgeInsets.fromLTRB(6, 2, 6, 3),
                         child: TextField(
                           decoration: InputDecoration(hintText: 'Votre Nom')),
                       ),
                       Container(
                         padding: EdgeInsets.fromLTRB(6, 2, 6, 3),
                         child: TextField(
                             decoration: InputDecoration(hintText: 'Code Payment')),
                       ),
                       Container(
                         padding: EdgeInsets.fromLTRB(6, 2, 6, 3),
                         child: TextField(
                             decoration: InputDecoration(hintText: 'Nombre de Ticket ')),
                       ),
                       Container(
                         width: double.infinity,
                         child: RaisedButton(
                           color: Colors.deepOrange,
                           child: Text("Résérver Les Places",style: TextStyle(color: Colors.white),),
                           onPressed: (){

                           },
                         ),
                       ),
                       Wrap(
                         children: <Widget>[
                           ...(this.listSalles[index]['currentprojection']['listTickets']).map((ticket){
                             if(ticket['reserve']==false)
                               return Container(
                                   width: 50,
                                   padding: EdgeInsets.all(2),
                                   child: RaisedButton(

                                     color: Colors.green,
                                     child: Text("${ticket['place']['numero']}",style: TextStyle(color:Colors.white,fontSize: 12 ),),
                                     onPressed: (){
                                       //reserve();
                                     },
                                   )
                               );
                             else return Container();
                           })
                         ],
                       )
                     ],),


                  ],
                )
              );
              })
          ),
        ),
      ),
    );
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    loadSalles();
  }

  void loadSalles() {
    String url = this.widget.cinema["_links"]["salles"]["href"];
    http.get(url).then((resp){
   setState(() {
     this.listSalles=json.decode(resp.body)["_embedded"]["salles"];
   });
    }).catchError((err){
      print(err);
    })
    ;
  }

  void loadProjction(salle) {
    String url=salle['_links']['projections']['href'].toString()
    .replaceAll("{?projection}", "?projection=p1");

    http.get(url)
    .then((resp){
      setState(() {
        //this.listProjections=json.decode(resp.body)["_embedded"]["projections"]["filme"];
      salle['projections']=json.decode(resp.body)['_embedded']['projections'];
      salle['currentprojection']=salle['projections'][0];
      salle['currentprojection']['listTickets']=[];
      });
    }).catchError((err){
      print(err);
    });

  }

  void loadTickets(projection,salle) {
  String url=projection['_links']['tickets']['href'].toString()
      .replaceAll("{?projection}", "?projection=p2");
   http.get(url)
  .then((res){
    setState(() {
      projection['listTickets']=json.decode(res.body)['_embedded']['tickets'];
      salle['currentprojection']=projection;
      projection["nombrePlaceDisponible"]=nombrePlaceDisponible(projection);
    });
   }).catchError((err){
     print(err);
   });
  }
 nombrePlaceDisponible(projection){
    int nombre=0;
    for (int i=0;i<projection['tickets'].length;i++){
       if(projection['tickets'][i]['reserve']== false)
         ++nombre;
    }
    return nombre;
}

}
