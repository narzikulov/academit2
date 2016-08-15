function solveEquation(){
    var aKoeff = document.getElementById("a-koeff").value;
    var bKoeff = document.getElementById("b-koeff").value;
    var cKoeff = document.getElementById("c-koeff").value;

    var discrimenant = Math.sqrt(bKoeff * bKoeff - 4 * aKoeff * cKoeff);
    var result="There is no solve for this equation";

    if (discrimenant > 0 ) {
        var x1 = (bKoeff + discrimenant) / (2 * aKoeff);
        var x2 = (-1 * bKoeff + discrimenant) / (2 * aKoeff);
        result = "x1 = ";
    }

    alert(result);
}


document.getElementById("solve").onclick = solveEquation;
